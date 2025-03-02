package com.cloudpic.cloudpicture.common;

import com.cloudpic.cloudpicture.exception.ErrorCode;

/**
 * @CLass: ResultUtils
 * @BelongProject:CloudPicture
 * @Author: gaf_Song
 * @Date:2025/3/1
 * @Version: 1.0
 * @Description:
 */
public class ResultUtils {

	/**
	 * 成功
	 *
	 * @param data 数据
	 * @param <T>  数据类型
	 * @return 响应
	 */
	public static <T> BaseResponse<T> success(T data) {
		return new BaseResponse<>(0, data, "ok");
	}

	/**
	 * 失败
	 *
	 * @param errorCode 错误码
	 * @return 响应
	 */
	public static BaseResponse<?> error(ErrorCode errorCode) {
		return new BaseResponse<>(errorCode);
	}

	/**
	 * 失败
	 *
	 * @param code    错误码
	 * @param message 错误信息
	 * @return 响应
	 */
	public static BaseResponse<?> error(int code, String message) {
		return new BaseResponse<>(code, null, message);
	}

	/**
	 * 失败
	 *
	 * @param errorCode 错误码
	 * @return 响应
	 */
	public static BaseResponse<?> error(ErrorCode errorCode, String message) {
		return new BaseResponse<>(errorCode.getCode(), null, message);
	}
}
