package com.imooc.pan.server.modules.share.mapper;

import com.imooc.pan.server.modules.share.entity.RPanShare;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.pan.server.modules.share.vo.RPanShareUrlListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author weileipeng
* @description 针对表【r_pan_share(用户分享表)】的数据库操作Mapper
* @createDate 2024-04-06 18:04:12
* @Entity com.imooc.pan.server.modules.share.entity.RPanShare
*/
public interface RPanShareMapper extends BaseMapper<RPanShare> {

    /**
     * 查询用户的分享列表
     *
     * @param userId
     * @return
     */
    List<RPanShareUrlListVO> selectShareVOListByUserId(@Param("userId") Long userId);

}




