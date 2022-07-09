package com.kakaopaysec.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kakaopaysec.vo.AccountDetailVo;
import com.kakaopaysec.vo.AccountInfoVo;
import com.kakaopaysec.vo.UserVo;

@Mapper
public interface AccountMapper {
	int insertAccount(AccountInfoVo account);
	List<AccountInfoVo> getAccountList(AccountInfoVo account);
	String getNewAccountNo();
}