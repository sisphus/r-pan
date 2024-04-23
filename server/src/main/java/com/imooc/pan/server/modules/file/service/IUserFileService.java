package com.imooc.pan.server.modules.file.service;

import com.imooc.pan.server.modules.file.context.*;
import com.imooc.pan.server.modules.file.entity.RPanUserFile;
import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.pan.server.modules.file.vo.*;

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

    UploadedChunksVO getUploadedChunks(QueryUploadedChunksContext context);

    void mergeFile(FileChunkMergeContext context);

    void download(FileDownloadContext context);

    void preview(FilePreviewContext context);

    List<FolderTreeNodeVO> getFolderTree(QueryFolderTreeContext context);

    void transfer(TransferFileContext context);

    void copy(CopyFileContext context);

    List<FileSearchResultVO> search(FileSearchContext context);

    List<BreadcrumbVO> getBreadcrumbs(QueryBreadcrumbsContext context);

    List<RPanUserFile> findAllFileRecords(List<RPanUserFile> records);

    /**
     * 递归查询所有的子文件信息
     *
     * @param fileIdList
     * @return
     */
    List<RPanUserFile> findAllFileRecordsByFileIdList(List<Long> fileIdList);

    /**
     * 实体转换
     *
     * @param records
     * @return
     */
    List<RPanUserFileVO> transferVOList(List<RPanUserFile> records);

    /**
     * 文件下载 不校验用户是否是否是上传用户
     *
     * @param context
     */
    void downloadWithoutCheckUser(FileDownloadContext context);
}
