package com.imooc.pan.server.modules.user.controller;

import com.imooc.pan.core.response.R;
import com.imooc.pan.server.modules.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
 * 该类是用户模块的控制类实体
 */
@RestController
@RequestMapping("user")
@Api(tags = "用户模块")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @ApiOperation(
            value = "用户注册接口",
            notes = "该接口提供了用户注册的功能，实现了冥等性注册的逻辑，可以放心多并发调用",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @PostMapping("register")
    public R register(){
        return null;
    }
}
