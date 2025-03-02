package com.cloudpic.cloudpicture.model.dto.user;

import com.cloudpic.cloudpicture.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @CLass: UserQueryRequest
 * @BelongProject:CloudPicture
 * @Author: gaf_Song
 * @Date:2025/3/2
 * @Version: 1.0
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {

	/**
	 * id
	 */
	private Long id;

	/**
	 * 用户昵称
	 */
	private String userName;

	/**
	 * 账号
	 */
	private String userAccount;

	/**
	 * 简介
	 */
	private String userProfile;

	/**
	 * 用户角色：user/admin/ban
	 */
	private String userRole;

	private static final long serialVersionUID = 1L;
}
