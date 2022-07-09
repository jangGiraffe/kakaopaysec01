package com.kakaopaysec.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kakaopaysec.vo.AccountDetailVo;

@Mapper
public interface AccountDetailMapper {
	int insertAccountDetail(AccountDetailVo request);
	List<AccountDetailVo> getAccountDetailList(String accountNo);
	List<AccountDetailVo> getAccountBalance(String userId);
	List<AccountDetailVo> getAvgBalancebyAge();
	String getTotalAnuualBalance(String date);
	List<HashMap<String,String>> getTotalBalanceOfUser(String startDate,String endDate);
}