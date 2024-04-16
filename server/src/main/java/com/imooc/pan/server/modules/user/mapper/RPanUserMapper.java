package com.imooc.pan.server.modules.user.mapper;

import com.imooc.pan.server.modules.user.entity.RPanUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;

/**
* @author weileipeng
* @description 针对表【r_pan_user(用户信息表)】的数据库操作Mapper
* @createDate 2024-04-06 17:54:49
* @Entity com.imooc.pan.server.modules.user.entity.RPanUser
*/
public interface RPanUserMapper extends BaseMapper<RPanUser> {

    /**
     * 通过用户名称查询用户设置的密保问题
     * 在xml中写查询语句
     * @param username
     * @return
     */
    String selectQuestionByUsername(@Param("username") String username);
}




