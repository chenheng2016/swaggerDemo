package com.swagger.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chenheng
 * @time 2020/4/3 13:06
 */
@Data
@ApiModel(description = "用户信息")
public class User {

    @ApiModelProperty
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    private String email;
}
