package com.cloudpic.cloudpicture.model.dto.file;

import lombok.Data;

/**
 * @CLass: UploadPictureResult
 * @BelongProject:CloudPicture
 * @Author: gaf_Song
 * @Date:2025/3/2
 * @Version: 1.0
 * @Description:
 */
@Data
public class UploadPictureResult {

	/**
	 * 图片地址
	 */
	private String url;

	/**
	 * 图片名称
	 */
	private String picName;

	/**
	 * 文件体积
	 */
	private Long picSize;

	/**
	 * 图片宽度
	 */
	private int picWidth;

	/**
	 * 图片高度
	 */
	private int picHeight;

	/**
	 * 图片宽高比
	 */
	private Double picScale;

	/**
	 * 图片格式
	 */
	private String picFormat;

}
