package com.imooc.pan.server.modules.user.service;

import com.imooc.pan.server.modules.user.context.*;
import com.imooc.pan.server.modules.user.entity.RPanUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.pan.server.modules.user.vo.UserInfoVO;

/**
* @author weileipeng
* @description 针对表【r_pan_user(用户信息表)】的数据库操作Service
* @createDate 2024-04-06 17:54:49
*/
public interface IUserService extends IService<RPanUser> {
    /*
     用户注册业务
     */

    Long register(UserRegisterContext userRegisterContext);

    /**
     * 用户登录业务
     * @param userLoginContext
     * @return
     */
    String login(UserLoginContext userLoginContext);

    void exit(Long aLong);

    String checkUsername(CheckUsernameContext checkUsernameContext);

    String checkAnswer(CheckAnswerContext checkAnswerContext);

    void resetPassword(ResetPasswordContext resetPasswordContext);

    void changePassword(ChangePasswordContext changePasswordContext);

    UserInfoVO info(Long aLong);
}
