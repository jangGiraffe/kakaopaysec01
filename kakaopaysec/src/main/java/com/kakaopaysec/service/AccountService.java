package com.kakaopaysec.service;

import com.google.gson.JsonObject;
import com.kakaopaysec.common.vo.JSONApiResponse;
import com.kakaopaysec.vo.AccountDetailVo;
import com.kakaopaysec.vo.AccountInfoVo;
import com.kakaopaysec.vo.UserVo;


public interface AccountService {

	JSONApiResponse addAccount(AccountInfoVo request, JSONApiResponse result);

	JSONApiResponse getAccountList(AccountInfoVo request, JSONApiResponse result);
	
	JSONApiResponse addAccountDetail(AccountDetailVo request, JSONApiResponse result);
	
	JSONApiResponse getAccountDetailList(AccountDetailVo accountDetailVo, JSONApiResponse result);

	JSONApiResponse getAccountBalance(UserVo userVo, JSONApiResponse jsonApiResponse);

	JSONApiResponse getAvgBalancebyAge(JSONApiResponse jsonApiResponse);

	JSONApiResponse getTotalAnuualBalance(AccountDetailVo request, JSONApiResponse jsonApiResponse);

	JSONApiResponse getTotalBalanceOfUser(JsonObject request, JSONApiResponse jsonApiResponse);
}
