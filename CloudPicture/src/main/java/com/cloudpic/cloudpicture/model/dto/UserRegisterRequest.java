package com.cloudpic.cloudpicture.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @CLass: UserRegisterRequest
 * @BelongProject:CloudPicture
 * @Author: gaf_Song
 * @Date:2025/3/2
 * @Version: 1.0
 * @Description:
 */
@Data
public class UserRegisterRequest implements Serializable {

	private static final long serialVersionUID = 3191241716373120793L;

	/**
	 * 账号
	 */
	private String userAccount;

	/**
	 * 密码
	 */
	private String userPassword;

	/**
	 * 确认密码
	 */
	private String checkPassword;
}
