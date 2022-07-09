package com.kakaopaysec.common.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kakaopaysec.common.SecurityService;
import com.kakaopaysec.common.exception.ApiException;
import com.kakaopaysec.common.exception.ExceptionEnum;
import com.kakaopaysec.common.vo.JSONApiResponse;

import io.jsonwebtoken.ExpiredJwtException;

@Component
@Aspect
public class RequestCheckAspect<E> {
	@Autowired
	SecurityService securityService;
	
	@Pointcut("within(com.kakaopaysec.controller..*)")
	public void onRequest() {}
	 
	@Around("com.kakaopaysec.common.aop.RequestCheckAspect.onRequest()")
	public Object checkToken(ProceedingJoinPoint pjp) throws Throwable {
		JSONApiResponse result = new JSONApiResponse();
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			String rqkey = request.getHeader("key");
			String checkKey = securityService.getSubject(request.getHeader("token"));
			
			if(!rqkey.equals(checkKey)) {
				throw new Exception();
			}
		}catch(ExpiredJwtException e) {
			throw new ApiException(ExceptionEnum.TOKEN_01);
		}catch(Exception e) {
			throw new ApiException(ExceptionEnum.TOKEN_02);
		}
		
		return pjp.proceed(pjp.getArgs());
	  }
}
