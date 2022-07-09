package com.kakaopaysec.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVo {
	@ApiModelProperty(example="freeText", value ="사용자 Id")
	private String userId;
	@ApiModelProperty(example="freeText", value ="사용자 이름")
	private String userName;
	@ApiModelProperty(example="yyyy-MM-dd", value ="가입일")
	private String regDate;
	@ApiModelProperty(example="99", value ="나이")
	private int userAge;
	
}
