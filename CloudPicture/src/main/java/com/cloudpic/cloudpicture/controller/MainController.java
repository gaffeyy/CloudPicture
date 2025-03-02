package com.cloudpic.cloudpicture.controller;

import com.cloudpic.cloudpicture.common.BaseResponse;
import com.cloudpic.cloudpicture.common.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CLass: MainController
 * @BelongProject:CloudPicture
 * @Author: gaf_Song
 * @Date:2025/3/1
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/")
public class MainController {

	/**
	 * TestController
	 */
	@GetMapping("/test")
	public BaseResponse<String> test(){
		return ResultUtils.success("TestAccess");
	}
}
