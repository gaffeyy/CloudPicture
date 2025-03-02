package com.cloudpic.cloudpicture.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloudpic.cloudpicture.model.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloudpic.cloudpicture.model.User;
import com.cloudpic.cloudpicture.model.dto.picture.PictureQueryRequest;
import com.cloudpic.cloudpicture.model.dto.picture.PictureUploadRequest;
import com.cloudpic.cloudpicture.model.vo.PictureVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
* @author gaffey
* @description 针对表【picture(图片)】的数据库操作Service
* @createDate 2025-03-02 17:56:04
*/
public interface PictureService extends IService<Picture> {
	/**
	 * 上传图片
	 *
	 * @param multipartFile
	 * @param pictureUploadRequest
	 * @param loginUser
	 * @return
	 */
	PictureVO uploadPicture(MultipartFile multipartFile,
							PictureUploadRequest pictureUploadRequest,
							User loginUser);
	public QueryWrapper<Picture> getQueryWrapper(PictureQueryRequest pictureQueryRequest);

	public PictureVO getPictureVO(Picture picture, HttpServletRequest request);
	public Page<PictureVO> getPictureVOPage(Page<Picture> picturePage, HttpServletRequest request);
	public void validPicture(Picture picture);
}
