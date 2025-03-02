package com.cloudpic.cloudpicture.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @CLass: UserAddRequest
 * @BelongProject:CloudPicture
 * @Author: gaf_Song
 * @Date:2025/3/2
 * @Version: 1.0
 * @Description:
 */
@Data
public class UserAddRequest implements Serializable {

	/**
	 * 用户昵称
	 */
	private String userName;

	/**
	 * 账号
	 */
	private String userAccount;

	/**
	 * 用户头像
	 */
	private String userAvatar;

	/**
	 * 用户简介
	 */
	private String userProfile;

	/**
	 * 用户角色: user, admin
	 */
	private String userRole;

	private static final long serialVersionUID = 1L;
}
