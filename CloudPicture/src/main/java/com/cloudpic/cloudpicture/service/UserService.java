package com.cloudpic.cloudpicture.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cloudpic.cloudpicture.exception.BusinessException;
import com.cloudpic.cloudpicture.exception.ErrorCode;
import com.cloudpic.cloudpicture.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloudpic.cloudpicture.model.dto.user.UserQueryRequest;
import com.cloudpic.cloudpicture.model.vo.LoginUserVO;
import com.cloudpic.cloudpicture.model.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author gaffey
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2025-03-02 15:33:20
*/
public interface UserService extends IService<User> {
	/**
	 * 用户注册
	 *
	 * @param userAccount   用户账户
	 * @param userPassword  用户密码
	 * @param checkPassword 校验密码
	 * @return 新用户 id
	 */
	long userRegister(String userAccount, String userPassword, String checkPassword);

	/**
	 * 混淆密码
	 * @param userPassword
	 * @return
	 */
	public String getEncryptPassword(String userPassword);

	/**
	 * 用户登录
	 *
	 * @param userAccount  用户账户
	 * @param userPassword 用户密码
	 * @param request
	 * @return 脱敏后的用户信息
	 */
	LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

	/**
	 * 获取当前登录用户
	 *
	 * @param request
	 * @return
	 */
	User getLoginUser(HttpServletRequest request);

	/**
	 * 获取脱敏的已登录用户信息
	 *
	 * @return
	 */
	LoginUserVO getLoginUserVO(User user);

	/**
	 * 用户注销
	 *
	 * @param request
	 * @return
	 */
	boolean userLogout(HttpServletRequest request);
	public UserVO getUserVO(User user);

	public List<UserVO> getUserVOList(List<User> userList);

	public QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);

	/**
	 * 是否为管理员
	 *
	 * @param user
	 * @return
	 */
	boolean isAdmin(User user);

}
