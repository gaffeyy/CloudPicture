package com.cloudpic.cloudpicture.model.dto.picture;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @CLass: PictureEditRequest
 * @BelongProject:CloudPicture
 * @Author: gaf_Song
 * @Date:2025/3/2
 * @Version: 1.0
 * @Description:
 */
@Data
public class PictureEditRequest implements Serializable {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 图片名称
	 */
	private String name;

	/**
	 * 简介
	 */
	private String introduction;

	/**
	 * 分类
	 */
	private String category;

	/**
	 * 标签
	 */
	private List<String> tags;

	private static final long serialVersionUID = 1L;
}
