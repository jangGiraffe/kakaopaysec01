package com.kakaopaysec.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountDetailVo {
	@ApiModelProperty(example="1000-10", value ="계좌번호")
	private String accountNo;
	@ApiModelProperty(example="Y/N", value="입출금여부")
	private String status;
	@ApiModelProperty(example="yyyy-MM-dd", value="입금일")
	private String date;
	@ApiModelProperty(example="10000", value="입금액")
	private String amount;
	
}
