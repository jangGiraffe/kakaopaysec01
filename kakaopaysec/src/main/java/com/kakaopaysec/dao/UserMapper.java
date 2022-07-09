package com.kakaopaysec.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kakaopaysec.vo.UserVo;

@Mapper
public interface UserMapper {
	int insertUser(UserVo user);
	int getUser(String userId);
	List<UserVo> getUserList();
}
