package com.kakaopaysec.common.vo;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonTypeName(value="Result")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use=JsonTypeInfo.Id.NAME)
public class JSONApiResponse {
	@ApiModelProperty(example="0000", value ="응답 코드")
	private String code = "0000";
	@ApiModelProperty(example="success", value ="응답 메시지")
	private String mesagge = "success";
	@ApiModelProperty(value ="응답 데이터")
	private Object data;
	
	public JSONApiResponse (Object data) {
		this.data=data;
	}
	
	public void setRetrun(String code,String message) {
		this.code = code;
		this.mesagge = message;
	}
}
