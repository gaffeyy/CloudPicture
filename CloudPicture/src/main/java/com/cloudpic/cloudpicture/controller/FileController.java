package com.cloudpic.cloudpicture.controller;

import com.cloudpic.cloudpicture.annotation.AuthCheck;
import com.cloudpic.cloudpicture.common.BaseResponse;
import com.cloudpic.cloudpicture.common.ResultUtils;
import com.cloudpic.cloudpicture.constant.UserConstant;
import com.cloudpic.cloudpicture.exception.BusinessException;
import com.cloudpic.cloudpicture.exception.ErrorCode;
import com.cloudpic.cloudpicture.manage.CosManager;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.COSObjectInputStream;
import com.qcloud.cos.utils.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @CLass: FileController
 * @BelongProject:CloudPicture
 * @Author: gaf_Song
 * @Date:2025/3/2
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

	@Resource
	CosManager cosManager;

	/**
	 * 测试文件上传
	 *
	 * @param multipartFile
	 * @return
	 */
	@AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
	@PostMapping("/test/upload")
	public BaseResponse<String> testUploadFile(@RequestPart("file") MultipartFile multipartFile) {
		// 文件目录
		String filename = multipartFile.getOriginalFilename();
		String filepath = String.format("/test/%s", filename);
		File file = null;
		try {
			// 上传文件
			file = File.createTempFile(filepath, null);
			multipartFile.transferTo(file);
			cosManager.putObject(filepath, file);
			// 返回可访问地址
			return ResultUtils.success(filepath);
		} catch (Exception e) {
			log.error("file upload error, filepath = " + filepath, e);
			throw new BusinessException(ErrorCode.SYSTEM_ERROR, "上传失败");
		} finally {
			if (file != null) {
				// 删除临时文件
				boolean delete = file.delete();
				if (!delete) {
					log.error("file delete error, filepath = {}", filepath);
				}
			}
		}
	}

	/**
	 * 测试文件下载
	 *
	 * @param filepath 文件路径
	 * @param response 响应对象
	 */
	@AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
	@GetMapping("/test/download/")
	public void testDownloadFile(String filepath, HttpServletResponse response) throws IOException {
		COSObjectInputStream cosObjectInput = null;
		try {
			COSObject cosObject = cosManager.getObject(filepath);
			cosObjectInput = cosObject.getObjectContent();
			// 处理下载到的流
			byte[] bytes = IOUtils.toByteArray(cosObjectInput);
			// 设置响应头
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + filepath);
			// 写入响应
			response.getOutputStream().write(bytes);
			response.getOutputStream().flush();
		} catch (Exception e) {
			log.error("file download error, filepath = " + filepath, e);
			throw new BusinessException(ErrorCode.SYSTEM_ERROR, "下载失败");
		} finally {
			if (cosObjectInput != null) {
				cosObjectInput.close();
			}
		}
	}


}
