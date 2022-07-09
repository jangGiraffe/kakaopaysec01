package com.kakaopaysec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.kakaopaysec.common.constants;
import com.kakaopaysec.common.exception.ApiException;
import com.kakaopaysec.common.exception.ExceptionEnum;
import com.kakaopaysec.common.util.Util;
import com.kakaopaysec.common.vo.JSONApiResponse;
import com.kakaopaysec.dao.AccountDetailMapper;
import com.kakaopaysec.dao.AccountMapper;
import com.kakaopaysec.dao.UserMapper;
import com.kakaopaysec.vo.AccountDetailVo;
import com.kakaopaysec.vo.AccountInfoVo;
import com.kakaopaysec.vo.UserVo;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountMapper accountDao;
	
	@Autowired
	AccountDetailMapper accountDetailDao;

	@Autowired
	UserMapper userDao;
	
	@Override
	public JSONApiResponse addAccount(AccountInfoVo accountVo, JSONApiResponse result) {
		String userId = accountVo.getUserId();
		if(userDao.getUser(userId)!=0) {
			accountVo.setAccountNo(makeAccountNo(accountDao.getNewAccountNo()));
			try {
				result.setData(accountDao.insertAccount(accountVo));
			}catch(Exception e) {
				throw new ApiException(ExceptionEnum.DB_01);
			}
		}else {
			throw new ApiException(ExceptionEnum.DATA_01);
		}
		
		return result;
	}

	@Override
	public JSONApiResponse getAccountList(AccountInfoVo accountVo, JSONApiResponse result) {
		if("".equals(accountVo.getUserId())) {
			throw new ApiException(ExceptionEnum.DATA_01);
		}
		
		result.setData(accountDao.getAccountList(accountVo));
		return result;
	}

	@Override
	public JSONApiResponse addAccountDetail(AccountDetailVo request, JSONApiResponse result) {
		
		if(!checkAccountDetail(request,result).getCode().equals("0000")) return result;
		request.setDate(Util.getCurrentDate("yyyy-MM-dd"));
		try {
			result.setData(accountDetailDao.insertAccountDetail(request));
		}catch(Exception e) {
			throw new ApiException(ExceptionEnum.DB_01);
		}
		
		
		return result;
	}

	@Override
	public JSONApiResponse getAccountDetailList(AccountDetailVo accountDetailVo, JSONApiResponse result) {
		String accountNo = accountDetailVo.getAccountNo();
		if(null == accountNo || accountNo.isBlank()) throw new ApiException(ExceptionEnum.DATA_01);
		
		AccountInfoVo tmp = new AccountInfoVo();
		tmp.setAccountNo(accountNo);
		List<AccountInfoVo> tmpList = accountDao.getAccountList(tmp);
		if(tmpList.size()==0) throw new ApiException(ExceptionEnum.DATA_01);
		
		result.setData(accountDetailDao.getAccountDetailList(accountNo));
		
		return result;
	}

	@Override
	public JSONApiResponse getAccountBalance(UserVo userVo, JSONApiResponse result) {
		
		if(null==userVo || null == userVo.getUserId() || userVo.getUserId().isBlank()) 
			throw new ApiException(ExceptionEnum.DATA_01);
		
		result.setData(accountDetailDao.getAccountBalance(userVo.getUserId()));
		
		return result;
	}

	@Override
	public JSONApiResponse getAvgBalancebyAge(JSONApiResponse result) {
		result.setData(accountDetailDao.getAvgBalancebyAge());
		return result;
	}

	@Override
	public JSONApiResponse getTotalAnuualBalance(AccountDetailVo request, JSONApiResponse result) {
		result.setData(accountDetailDao.getTotalAnuualBalance(request.getDate()));
		return result;
	}

	@Override
	public JSONApiResponse getTotalBalanceOfUser(JsonObject request, JSONApiResponse result) {
		String startDate = Util.checkDateFormat(request.get("startDate").getAsString());
		String endDate = Util.checkDateFormat(request.get("endDate").getAsString());
		
		if(startDate.equals("error") || endDate.equals("error")) {
			throw new ApiException(ExceptionEnum.DATA_02_1);
		}else {
			if(Integer.parseInt(startDate.replace("-", "")) > Integer.parseInt(endDate.replace("-", ""))) {
				throw new ApiException(ExceptionEnum.DATA_02_2);
			}
		}
		
		result.setData(accountDetailDao.getTotalBalanceOfUser(startDate,endDate));
		
		return result;
	}
	
	
	public String makeAccountNo(String accountNo) {
		return accountNo.substring(0,4)+"-"+accountNo.substring(4,6);
	}

	private JSONApiResponse checkAccountDetail(AccountDetailVo accountDetailVo, JSONApiResponse result) {
		
		if(null==accountDetailVo) {
			throw new ApiException(ExceptionEnum.DATA_01);
		}
		
		if(null==accountDetailVo.getAccountNo() || accountDetailVo.getAccountNo().isBlank()) {
			throw new ApiException(ExceptionEnum.DATA_01);
		}else {
			AccountInfoVo tmp = new AccountInfoVo();
			tmp.setAccountNo(accountDetailVo.getAccountNo());
			List<AccountInfoVo> tmpList = accountDao.getAccountList(tmp);
			if(tmpList.size()==0) {
				throw new ApiException(ExceptionEnum.DATA_01);
			}
			
		}
		
		if(null==accountDetailVo.getStatus() || accountDetailVo.getStatus().isBlank()) {
			throw new ApiException(ExceptionEnum.DATA_01);
		}else {
			String tmp = accountDetailVo.getStatus();
			if(!("Y".equals(tmp) || "N".equals(tmp))) {
				throw new ApiException(ExceptionEnum.DATA_02);
			}
		}
		
		if(null==accountDetailVo.getAmount() || accountDetailVo.getAmount().isBlank()) {
			throw new ApiException(ExceptionEnum.DATA_01);
		}else if(!Util.isNumber(accountDetailVo.getAmount())) {
			throw new ApiException(ExceptionEnum.DATA_02);
		}
		
		return result;
		
	}
}
