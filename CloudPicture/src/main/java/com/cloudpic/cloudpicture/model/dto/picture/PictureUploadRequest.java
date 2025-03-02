package com.cloudpic.cloudpicture.model.dto.picture;

import lombok.Data;

import java.io.Serializable;

/**
 * @CLass: PictureUploadRequest
 * @BelongProject:CloudPicture
 * @Author: gaf_Song
 * @Date:2025/3/2
 * @Version: 1.0
 * @Description:
 */
@Data
public class PictureUploadRequest implements Serializable {

	/**
	 * 图片 id（用于修改）
	 */
	private Long id;

	private static final long serialVersionUID = 1L;
}
