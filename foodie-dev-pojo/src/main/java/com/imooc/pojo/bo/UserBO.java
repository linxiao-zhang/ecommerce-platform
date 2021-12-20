package com.imooc.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@ApiModel(value="用户对象BO", description = "从客户端由用户传入的数据")
@Data
public class UserBO {
    @ApiModelProperty(value="用户名", name="username",example="username",required = true)
    private String username;
    @ApiModelProperty(value="密码",name="password",example="123456",required = true)
    private String password;
    @ApiModelProperty(value="确认密码",name="confirmPassword",example="123456")
    private String confirmPassword;
}
