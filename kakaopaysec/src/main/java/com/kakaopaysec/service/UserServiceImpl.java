package com.kakaopaysec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kakaopaysec.common.constants;
import com.kakaopaysec.common.exception.ApiException;
import com.kakaopaysec.common.exception.ExceptionEnum;
import com.kakaopaysec.common.util.Util;
import com.kakaopaysec.common.vo.JSONApiResponse;
import com.kakaopaysec.dao.UserMapper;
import com.kakaopaysec.vo.UserVo;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userDao;
	
	@Override
	public JSONApiResponse addUser(UserVo userVo, JSONApiResponse result) {
		
		
		if(!checkParam(userVo,result).getCode().equals("0000")) return result;
		
		userVo.setRegDate(Util.getCurrentDate("yyyy-MM-dd"));
		try{
			result.setData(userDao.insertUser(userVo));
		}catch(Exception e) {
			throw new ApiException(ExceptionEnum.DB_01);
		}

		return result;
	}

	@Override
	public JSONApiResponse getUserList(JSONApiResponse result) {
		result.setData(userDao.getUserList());
		return result;
	}
	
	private JSONApiResponse checkParam(UserVo userVo, JSONApiResponse result) {
		
		if(null==userVo) {
			throw new ApiException(ExceptionEnum.DATA_01);
		}
		
		if(null==userVo.getUserId() || userVo.getUserId().isBlank() ) {
			throw new ApiException(ExceptionEnum.DATA_01);
		}
		
		if(null==userVo.getUserName() || userVo.getUserName().isBlank() ) {
			throw new ApiException(ExceptionEnum.DATA_01);
		}
		
		if(userVo.getUserAge()==0) {
			throw new ApiException(ExceptionEnum.DATA_01);
		}
		
		return result;
	}

}
