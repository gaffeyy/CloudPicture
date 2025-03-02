package com.cloudpic.cloudpicture.model;

import lombok.Data;

import java.util.List;

/**
 * @CLass: PictureTagCategory
 * @BelongProject:CloudPicture
 * @Author: gaf_Song
 * @Date:2025/3/2
 * @Version: 1.0
 * @Description:
 */
@Data
public class PictureTagCategory {
	List<String> tagList;
	String Category;

	public void setTagList(List<String> tagList) {
	}

	public void setCategoryList(List<String> categoryList) {
	}
}
