package com.example.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author dongkw
 * @Date 2021/5/7、4:15 下午
 **/
@Data
public class Pledge extends Instruction {
    @ApiModelProperty(value = "回购期限， 1 -  360 天", required = true, position = 7)
    private String term;
    @ApiModelProperty(value = "回购利率，（0.0001） 后端保留四位小数 ， 0.01%前端显示两位", required = true, position = 7)
    private String interestRate;
}
