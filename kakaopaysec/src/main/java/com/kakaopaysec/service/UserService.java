package com.kakaopaysec.service;

import com.kakaopaysec.common.vo.JSONApiResponse;
import com.kakaopaysec.vo.UserVo;


public interface UserService {

	JSONApiResponse addUser(UserVo request, JSONApiResponse result);

	JSONApiResponse getUserList(JSONApiResponse jsonApiResponse);

}
