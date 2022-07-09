package com.kakaopaysec.common.exception;
import org.springframework.http.HttpStatus;

import com.kakaopaysec.common.constants;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ExceptionEnum {
	RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST,"E001"),
	ACCESS_DENICED_EXCEPTION(HttpStatus.UNAUTHORIZED,"E002"),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"E003","Internal Server Error"),
	
	DATA_01(HttpStatus.INTERNAL_SERVER_ERROR,constants.ERROR_EMPTY_DATA,"데이터 미존재"),
	DATA_02(HttpStatus.INTERNAL_SERVER_ERROR,constants.ERROR_FORMAT_DATA,"데이터 포멧 오류"),
	DATA_02_1(HttpStatus.INTERNAL_SERVER_ERROR,constants.ERROR_FORMAT_DATA,"데이터 포멧 오류 - ex)yyyy-MM-dd"),
	DATA_02_2(HttpStatus.INTERNAL_SERVER_ERROR,constants.ERROR_FORMAT_DATA,"데이터 포멧 오류 - 시작일이 종료일보다 큽니다."),
	
	DB_01(HttpStatus.INTERNAL_SERVER_ERROR,constants.ERROR_PROCESSING_DB,"DB처리 실패"),
	
	TOKEN_01(HttpStatus.UNAUTHORIZED,"S001","토큰 만료"),
	TOKEN_02(HttpStatus.UNAUTHORIZED,"S002","토큰 불일치");
	
	private final HttpStatus status;
	private final String code;
	private String message;
	
	ExceptionEnum(HttpStatus status,String code){
		this.status = status;
		this.code = code;
	}
	
	ExceptionEnum(HttpStatus status,String code,String message){
		this.status = status;
		this.code = code;
		this.message = message;
	}
	

}
