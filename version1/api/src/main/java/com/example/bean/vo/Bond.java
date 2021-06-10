package com.example.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author dongkw
 * @Date 2021/5/7、4:13 下午
 **/
@Data
public class Bond extends Instruction {
    @ApiModelProperty(value = "债券代码", required = true, position = 5)
    private String bondCode;
    @ApiModelProperty(value = "债券名称", required = true, position = 6)
    private String bondName;
}
