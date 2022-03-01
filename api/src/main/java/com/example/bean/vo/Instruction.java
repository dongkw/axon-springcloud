package com.example.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author dongkw
 * @Date 2021/5/7、4:12 下午
 **/
@Data
public class Instruction {
    @ApiModelProperty(value = "指令编号", required = true, position = 1)
    public String instructionID;
    @ApiModelProperty(value = "指令价格", required = true, position = 8)
    private String price;

    @ApiModelProperty(value = "指令总量", required = true, position = 9)
    private long volume = 0L;

    @ApiModelProperty(value = "指令金额", required = true, position = 10)
    private String amount;

    @ApiModelProperty(value = "交易日期", required = true, position = 16)
    private String tradeDate;

}
