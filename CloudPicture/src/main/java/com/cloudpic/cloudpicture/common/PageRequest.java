package com.cloudpic.cloudpicture.common;

import lombok.Data;

/**
 * @CLass: PageRequest
 * @BelongProject:CloudPicture
 * @Author: gaf_Song
 * @Date:2025/3/1
 * @Version: 1.0
 * @Description:
 */
@Data
public class PageRequest {

	/**
	 * 当前页号
	 */
	private int current = 1;

	/**
	 * 页面大小
	 */
	private int pageSize = 10;

	/**
	 * 排序字段
	 */
	private String sortField;

	/**
	 * 排序顺序（默认降序）
	 */
	private String sortOrder = "descend";
}

