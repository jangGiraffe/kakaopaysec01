package com.kakaopaysec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kakaopaysec.common.util.Util;
import com.kakaopaysec.common.vo.JSONApiResponse;
import com.kakaopaysec.service.UserService;
import com.kakaopaysec.vo.UserVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;

@Api(tags= {"유저 API"})
@RestController
@RequestMapping("/user")
public class UserRestController {
	private static final String DEFAULT_PARENT_PATH ="/rest";
	
	@Autowired
	private UserService userService;
	
	private Gson gson= new Gson();
	
	@ApiOperation(value = "사용자 추가", notes="사용자를 추가하는 API입니다.")
	@ApiResponses({
		@ApiResponse(code =9999, message ="오류")
	})
	@ResponseBody
	@ApiModelProperty(example="test")
	@PostMapping(DEFAULT_PARENT_PATH+"/addUser.json")
	public  JSONApiResponse addUser(
			@RequestBody
			@ApiParam(value ="<b>헤더 : <br>{Content-Type : application/json, key : 토큰을 발급한 key , token : 발급받은 token}<br>" + 
					"바디 : {<br>" + 
					"'userId' : '1234',<br>" + 
					"'userName' : 'tom',<br>" + 
					"'userAge' : '20'<br>" + 
					"}</b>")
            UserVo userVo) 
			throws Exception{
//		JsonObject requestBodyJson = Util.convertStringToJsonObj(requestBody);
//		UserVo userVo = gson.fromJson(requestBodyJson.get("userVo"), UserVo.class); 
		
		return userService.addUser(userVo,new JSONApiResponse());
	}
	
	@GetMapping(DEFAULT_PARENT_PATH+"/userList.json")
	@ApiOperation(value = "유저 리스트 조회", notes="유저 리스트를 조회하는 API입니다.")
	@ResponseBody
	public  JSONApiResponse userList() throws Exception{
		return userService.getUserList(new JSONApiResponse());
	}
}
