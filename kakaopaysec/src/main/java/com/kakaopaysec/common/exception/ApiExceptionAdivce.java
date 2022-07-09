package com.kakaopaysec.common.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionAdivce {
	@ExceptionHandler({ApiException.class})
	public ResponseEntity<ApiExceptionEntity> exceptionHandler(HttpServletRequest request,final ApiException e){
		return ResponseEntity
				.status(e.getError().getStatus())
				.body(ApiExceptionEntity.builder()
					.errorCode(e.getError().getCode())
					.errorMessage(e.getError().getMessage())
					.build());
	}
}
