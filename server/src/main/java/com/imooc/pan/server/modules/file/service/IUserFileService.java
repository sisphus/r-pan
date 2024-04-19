package com.imooc.pan.server.modules.file.service;

import com.imooc.pan.server.modules.file.context.*;
import com.imooc.pan.server.modules.file.entity.RPanUserFile;
import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.pan.server.modules.file.vo.FileChunkUploadVO;
import com.imooc.pan.server.modules.file.vo.RPanUserFileVO;

import java.util.List;

/**
* @author weileipeng
* @description 针对表【r_pan_user_file(用户文件信息表)】的数据库操作Service
* @createDate 2024-04-06 18:02:17
*/
public interface IUserFileService extends IService<RPanUserFile> {
    /**
     * 创建文件夹信息
     *
     * @param createFolderContext
     * @return
     */
    Long createFolder(CreateFolderContext createFolderContext);

    RPanUserFile getUserRootFile(Long userId);

    List<RPanUserFileVO> getFileList(QueryFileListContext context);

    void updateFilename(UpdateFilenameContext context);

    void deleteFile(DeleteFileContext context);

    boolean secUpload(SecUploadFileContext context);

    void upload(FileUploadContext context);

    FileChunkUploadVO chunkUpload(FileChunkUploadContext context);
}
