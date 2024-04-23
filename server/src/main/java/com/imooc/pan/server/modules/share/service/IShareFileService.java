package com.imooc.pan.server.modules.share.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.pan.server.modules.share.context.SaveShareFilesContext;
import com.imooc.pan.server.modules.share.entity.RPanShareFile;

/**
 * @author weileipeng
 * @description 针对表【r_pan_share_file(用户分享文件表)】的数据库操作Service
 * @createDate 2024-04-06 18:04:12
 */
public interface IShareFileService extends IService<RPanShareFile> {

    /**
     * 保存分享的文件的对应关系
     *
     * @param context
     */
    void saveShareFiles(SaveShareFilesContext context);

}
