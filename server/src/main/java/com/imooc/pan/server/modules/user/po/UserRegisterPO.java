package com.imooc.pan.server.modules.user.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/*
 * 注册用户参数实体对象
 */
@Data
@ApiModel(value = "用户注册参数")
public class UserRegisterPO implements Serializable {
    private static final long serialVersionUID = -5521427813609988931L;

    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[0-9A-Za-z]{6,16}$", message = "Please enter a username containing 6-16 characters, using only letters and numbers.")
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    @Length(min = 8, max = 16, message = "Please enter a password that is 8-16 characters long.")
    private String password;

    @ApiModelProperty(value = "密码问题", required = true)
    @NotBlank(message = "密保问题不能为空")
    @Length(max = 100, message = "The security question must not exceed 100 characters.")
    private String question;

    @ApiModelProperty(value = "密码答案", required = true)
    @NotBlank(message = "密保答案不能为空")
    @Length(max = 100, message = "The security answer must not exceed 100 characters.")
    private String answer;
}
