package com.cloudpic.cloudpicture.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloudpic.cloudpicture.annotation.AuthCheck;
import com.cloudpic.cloudpicture.common.BaseResponse;
import com.cloudpic.cloudpicture.common.ResultUtils;
import com.cloudpic.cloudpicture.constant.UserConstant;
import com.cloudpic.cloudpicture.exception.BusinessException;
import com.cloudpic.cloudpicture.exception.ErrorCode;
import com.cloudpic.cloudpicture.exception.ThrowUtils;
import com.cloudpic.cloudpicture.model.User;
import com.cloudpic.cloudpicture.model.dto.UserLoginRequest;
import com.cloudpic.cloudpicture.model.dto.UserRegisterRequest;
import com.cloudpic.cloudpicture.model.dto.user.DeleteRequest;
import com.cloudpic.cloudpicture.model.dto.user.UserAddRequest;
import com.cloudpic.cloudpicture.model.dto.user.UserQueryRequest;
import com.cloudpic.cloudpicture.model.dto.user.UserUpdateRequest;
import com.cloudpic.cloudpicture.model.vo.LoginUserVO;
import com.cloudpic.cloudpicture.model.vo.UserVO;
import com.cloudpic.cloudpicture.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @CLass: UserController
 * @BelongProject:CloudPicture
 * @Author: gaf_Song
 * @Date:2025/3/2
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;

	/**
	 * 用户注册
	 */
	@PostMapping("/register")
	public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
		ThrowUtils.throwIf(userRegisterRequest == null, ErrorCode.PARAMS_ERROR);
		String userAccount = userRegisterRequest.getUserAccount();
		String userPassword = userRegisterRequest.getUserPassword();
		String checkPassword = userRegisterRequest.getCheckPassword();
		long result = userService.userRegister(userAccount, userPassword, checkPassword);
		return ResultUtils.success(result);
	}

	/**
	 * 用户登录
	 */
	@PostMapping("/login")
	public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
		ThrowUtils.throwIf(userLoginRequest == null, ErrorCode.PARAMS_ERROR);
		String userAccount = userLoginRequest.getUserAccount();
		String userPassword = userLoginRequest.getUserPassword();
		LoginUserVO loginUserVO = userService.userLogin(userAccount, userPassword, request);
		return ResultUtils.success(loginUserVO);
	}

	/**
	 * 获取当前用户
	 */
	@GetMapping("/get/login")
	public BaseResponse<LoginUserVO> getLoginUser(HttpServletRequest request) {
		User user = userService.getLoginUser(request);
		return ResultUtils.success(userService.getLoginUserVO(user));
	}

	@PostMapping("/logout")
	public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
		ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
		boolean result = userService.userLogout(request);
		return ResultUtils.success(result);
	}

	/**
	 * 创建用户
	 */
	@PostMapping("/add")
	@AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
	public BaseResponse<Long> addUser(@RequestBody UserAddRequest userAddRequest) {
		ThrowUtils.throwIf(userAddRequest == null, ErrorCode.PARAMS_ERROR);
		User user = new User();
		BeanUtils.copyProperties(userAddRequest, user);
		// 默认密码 12345678
		final String DEFAULT_PASSWORD = "12345678";
		String encryptPassword = userService.getEncryptPassword(DEFAULT_PASSWORD);
		user.setUserPassword(encryptPassword);
		boolean result = userService.save(user);
		ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
		return ResultUtils.success(user.getId());
	}

	/**
	 * 根据 id 获取用户（仅管理员）
	 */
	@GetMapping("/get")
	@AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
	public BaseResponse<User> getUserById(long id) {
		ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
		User user = userService.getById(id);
		ThrowUtils.throwIf(user == null, ErrorCode.NOT_FOUND_ERROR);
		return ResultUtils.success(user);
	}

	/**
	 * 根据 id 获取包装类
	 */
	@GetMapping("/get/vo")
	public BaseResponse<UserVO> getUserVOById(long id) {
		BaseResponse<User> response = getUserById(id);
		User user = response.getData();
		return ResultUtils.success(userService.getUserVO(user));
	}

	/**
	 * 删除用户
	 */
	@PostMapping("/delete")
	@AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
	public BaseResponse<Boolean> deleteUser(@RequestBody DeleteRequest deleteRequest) {
		if (deleteRequest == null || deleteRequest.getId() <= 0) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		boolean b = userService.removeById(deleteRequest.getId());
		return ResultUtils.success(b);
	}

	/**
	 * 更新用户
	 */
	@PostMapping("/update")
	@AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
	public BaseResponse<Boolean> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
		if (userUpdateRequest == null || userUpdateRequest.getId() == null) {
			throw new BusinessException(ErrorCode.PARAMS_ERROR);
		}
		User user = new User();
		BeanUtils.copyProperties(userUpdateRequest, user);
		boolean result = userService.updateById(user);
		ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
		return ResultUtils.success(true);
	}

	/**
	 * 分页获取用户封装列表（仅管理员）
	 *
	 * @param userQueryRequest 查询请求参数
	 */
	@PostMapping("/list/page/vo")
	@AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
	public BaseResponse<Page<UserVO>> listUserVOByPage(@RequestBody UserQueryRequest userQueryRequest) {
		ThrowUtils.throwIf(userQueryRequest == null, ErrorCode.PARAMS_ERROR);
		long current = userQueryRequest.getCurrent();
		long pageSize = userQueryRequest.getPageSize();
		Page<User> userPage = userService.page(new Page<>(current, pageSize),
				userService.getQueryWrapper(userQueryRequest));
		Page<UserVO> userVOPage = new Page<>(current, pageSize, userPage.getTotal());
		List<UserVO> userVOList = userService.getUserVOList(userPage.getRecords());
		userVOPage.setRecords(userVOList);
		return ResultUtils.success(userVOPage);
	}


}

