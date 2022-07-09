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
import com.kakaopaysec.service.AccountService;
import com.kakaopaysec.vo.AccountDetailVo;
import com.kakaopaysec.vo.AccountInfoVo;
import com.kakaopaysec.vo.UserVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags= {"계좌 API"})
@RestController
@RequestMapping("/account")
public class AccountRestController {
	private static final String DEFAULT_PARENT_PATH ="/rest";
	
	@Autowired
	private AccountService accountService;
	
	
//	@ApiImplicitParams({
//		@ApiImplicitParam(name ="userId",value="사용자 아이디")
//		,@ApiImplicitParam(name ="userAge",value="사용자 나이")
//		,@ApiImplicitParam(name ="userName",value="사용자 이름")
//	})
	
	
//	JSONApiResponse addAccount(AccountInfoVo request, JSONApiResponse result);
//
//	JSONApiResponse getAccountList(String userId, JSONApiResponse result);
//	
//	JSONApiResponse addAccountDetail(AccountDetailVo request, JSONApiResponse result);
//	
//	JSONApiResponse getAccountDetailList(String AccountNo, JSONApiResponse result);
	@ApiOperation(value = "계좌 추가")
	@PostMapping(DEFAULT_PARENT_PATH+"/addAccount.json")
	@ResponseBody
	public  JSONApiResponse addAccount(@RequestBody 
			@ApiParam(value ="<b>헤더 : <br>{Content-Type : application/json, key : 토큰을 발급한 key , token : 발급받은 token}<br>" + 
					"바디 : {<br>" + 
					"'userId' : '1234',<br>" +  
					"}</b>")
			AccountInfoVo request) throws Exception{
		return accountService.addAccount(request,new JSONApiResponse());
	}
	
	@ApiOperation(value = "계좌 조회")
	@GetMapping(DEFAULT_PARENT_PATH+"/getAccountList.json")
	@ResponseBody
	public  JSONApiResponse getAccountList(@RequestBody
			@ApiParam(value ="<b>헤더 : <br>{Content-Type : application/json, key : 토큰을 발급한 key , token : 발급받은 token}<br>" + 
					"바디 : {<br>" + 
					"'userId' : '1234',<br>" +  
					"}</b>")
			AccountInfoVo request) throws Exception{
		return accountService.getAccountList(request,new JSONApiResponse());
	}
	
	@ApiOperation(value = "계좌내역 추가")
	@PostMapping(DEFAULT_PARENT_PATH+"/addAccountDetail.json")
	@ResponseBody
	public  JSONApiResponse addAccountDetail(@RequestBody
			@ApiParam(value ="<b>헤더 : <br>{Content-Type : application/json, key : 토큰을 발급한 key , token : 발급받은 token}<br>" + 
					"바디 : {<br>" + 
					"{<br>" + 
					"            'accountNo' : '1000-52',<br>" + 
					"            'status' : 'Y',<br>" + 
					"            'amount' : '1002000'<br>" +   
					"}</b>")
			AccountDetailVo request) throws Exception{
		return accountService.addAccountDetail(request,new JSONApiResponse());
	}
	
	@ApiOperation(value = "계좌내역 목록")
	@GetMapping(DEFAULT_PARENT_PATH+"/getAccountDetailList.json")
	@ResponseBody
	public  JSONApiResponse getAccountDetailList(@RequestBody
			@ApiParam(value ="<b>헤더 : <br>{Content-Type : application/json, key : 토큰을 발급한 key , token : 발급받은 token}<br>" + 
					"바디 : {<br>" + 
					"'accountNo' : '1000-52'<br>" +   
					"}</b>")
			AccountDetailVo request) throws Exception{
		return accountService.getAccountDetailList(request,new JSONApiResponse());
	}
	
	@ApiOperation(value = "계좌별 예치금 출력")
	@GetMapping(DEFAULT_PARENT_PATH+"/getAccountBalance.json")
	@ResponseBody
	public  JSONApiResponse getAccountBalance(@RequestBody
			@ApiParam(value ="<b>헤더 : <br>{Content-Type : application/json, key : 토큰을 발급한 key , token : 발급받은 token}<br>" + 
					"바디 : {<br>" + 
					"'userId' : '1234'<br>" +   
					"}</b>")
			UserVo userVo) throws Exception{
		//IN 사용자ID
		//OUT LIST형태[계좌번호,잔고]
		return accountService.getAccountBalance(userVo,new JSONApiResponse());
	}
	
	@ApiOperation(value = "나이별 평균 예치금 출력")
	@GetMapping(DEFAULT_PARENT_PATH+"/getAvgBalancebyAge.json")
	@ResponseBody
	public  JSONApiResponse getAvgBalancebyAge() throws Exception{
		//IN 없음
		//OUT LIST형태[연령대(10,20,30 등등), 예치금]
		return accountService.getAvgBalancebyAge(new JSONApiResponse());
	}
	
	@ApiOperation(value = "입력받은 연도의 예치금 총액 출력")
	@GetMapping(DEFAULT_PARENT_PATH+"/getTotalAnuualBalance.json")
	@ResponseBody
	public  JSONApiResponse getTotalAnuualBalance(@RequestBody
			@ApiParam(value ="<b>헤더 : <br>{Content-Type : application/json, key : 토큰을 발급한 key , token : 발급받은 token}<br>" + 
					"바디 : {<br>" + 
					"'date':'2021'<br>"+
					"}</b>")
			AccountDetailVo request) throws Exception{
		//IN 년도
		//OUT 예치금 총액
		return accountService.getTotalAnuualBalance(request,new JSONApiResponse());
	}
	
	@ApiOperation(value = "입력받은 기간에 돈을 많이 예치한 사용자 순 출력")
	@GetMapping(DEFAULT_PARENT_PATH+"/getTotalBalanceOfUser.json")
	@ResponseBody
	public  JSONApiResponse getTotalBalanceOfUser(@RequestBody
			@ApiParam(value ="<b>헤더 : <br>{Content-Type : application/json, key : 토큰을 발급한 key , token : 발급받은 token}<br>" + 
					"바디 : {<br>" + 
					"    'startDate' : '2021-02-01',<br>" + 
					"    'endDate' : '2022-08-31'<br>"+
					"}</b>")
			String request) throws Exception{
		//IN 시작일,종료일
		//OUT List 형태[사용자ID, 이름, 예치금]
		return accountService.getTotalBalanceOfUser(Util.convertStringToJsonObj(request),new JSONApiResponse());
	}
}
