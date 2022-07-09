package com.kakaopaysec.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountInfoVo {
	@ApiModelProperty(example="freeText", value ="사용자 Id")
	private String userId;
	@ApiModelProperty(example="1000-01", value ="계좌번호")
	private String accountNo;
}
