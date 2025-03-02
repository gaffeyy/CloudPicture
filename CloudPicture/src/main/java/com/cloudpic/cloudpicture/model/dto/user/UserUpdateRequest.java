package com.cloudpic.cloudpicture.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @CLass: UserUpdateRequest
 * @BelongProject:CloudPicture
 * @Author: gaf_Song
 * @Date:2025/3/2
 * @Version: 1.0
 * @Description:
 */
@Data
public class UserUpdateRequest implements Serializable {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 用户昵称
	 */
	private String userName;

	/**
	 * 用户头像
	 */
	private String userAvatar;

	/**
	 * 简介
	 */
	private String userProfile;

	/**
	 * 用户角色：user/admin
	 */
	private String userRole;

	private static final long serialVersionUID = 1L;
}
