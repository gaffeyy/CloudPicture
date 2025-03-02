package com.cloudpic.cloudpicture.common;

import com.cloudpic.cloudpicture.exception.ErrorCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @CLass: BaseResponse
 * @BelongProject:CloudPicture
 * @Author: gaf_Song
 * @Date:2025/3/1
 * @Version: 1.0
 * @Description:
 */
@Data
public class BaseResponse<T> implements Serializable {

	private int code;

	private T data;

	private String message;

	public BaseResponse(int code, T data, String message) {
		this.code = code;
		this.data = data;
		this.message = message;
	}

	public BaseResponse(int code, T data) {
		this(code, data, "");
	}

	public BaseResponse(ErrorCode errorCode) {
		this(errorCode.getCode(), null, errorCode.getMessage());
	}
}

