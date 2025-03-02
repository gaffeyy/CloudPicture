package com.cloudpic.cloudpicture.exception;

import lombok.Getter;

/**
 * @CLass: BusinessException
 * @BelongProject:CloudPicture
 * @Author: gaf_Song
 * @Date:2025/3/1
 * @Version: 1.0
 * @Description:
 */
@Getter
public class BusinessException extends RuntimeException{
	/**
	 * 错误码
	 */
	private final int code;

	public BusinessException(int code, String message) {
		super(message);
		this.code = code;
	}

	public BusinessException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.code = errorCode.getCode();
	}

	public BusinessException(ErrorCode errorCode, String message) {
		super(message);
		this.code = errorCode.getCode();
	}


}
