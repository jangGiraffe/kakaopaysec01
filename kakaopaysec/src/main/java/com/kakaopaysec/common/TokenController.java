package com.kakaopaysec.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.kakaopaysec.common.exception.ApiException;
import com.kakaopaysec.common.exception.ExceptionEnum;
import com.kakaopaysec.common.util.Util;
import com.kakaopaysec.common.vo.JSONApiResponse;

import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags= {"토큰 API"})
@RestController
@RequestMapping("/token")
public class TokenController {
	
	@Autowired
	SecurityService securityService;
	
	@ApiOperation(value = "토큰 생성")
	@ResponseBody
	@PostMapping("/getToken.json")
	public JSONApiResponse generateToken(@RequestBody 
			@ApiParam
			(value =
			"<b>헤더 : {Content-Type : application/json}\r\n" + 
			"바디 : {'key' : '임의의 텍스트'}</b>") 
			String requestBody){
		JSONApiResponse result = new JSONApiResponse();
		try {
		JsonObject requestBodyJson = Util.convertStringToJsonObj(requestBody);
		String token = securityService.createToken(requestBodyJson.get("key").getAsString());
		result.setData(token);
		}catch(Exception e) {
			throw new ApiException(ExceptionEnum.INTERNAL_SERVER_ERROR);
		}
		return result;		
	}
	
	@ApiOperation(value = "토큰 유효성 체크")
	@ResponseBody
	@PostMapping("/checkToken.json")
	public JSONApiResponse checkToken(@RequestBody
			@ApiParam
			(value =
			"<b>헤더 : {Content-Type : application/json}\r\n" + 
			"바디 : {'key' : '임의의 텍스트', 'token':'생성한토큰'}</b>") 
			String requestBody){
		JSONApiResponse result = new JSONApiResponse();
		try {
			JsonObject requestBodyJson = Util.convertStringToJsonObj(requestBody);
			String rqkey = requestBodyJson.get("key").getAsString();
			String checkKey = securityService.getSubject(requestBodyJson.get("token").getAsString());
			
			if(!rqkey.equals(checkKey)) {
				throw new ApiException(ExceptionEnum.TOKEN_02);
			}
		}catch(ExpiredJwtException e) {
			throw new ApiException(ExceptionEnum.TOKEN_01);
		}
		
		return result;
	}
}
