package com.imooc.pan.server.modules.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.imooc.pan.core.exception.RPanBusinessException;
import com.imooc.pan.core.utils.FileUtils;
import com.imooc.pan.core.utils.IdUtil;
import com.imooc.pan.server.common.event.log.ErrorLogEvent;
import com.imooc.pan.server.modules.file.context.FileSaveContext;
import com.imooc.pan.server.modules.file.entity.RPanFile;
import com.imooc.pan.server.modules.file.service.IFileService;
import com.imooc.pan.server.modules.file.mapper.RPanFileMapper;
import com.imooc.pan.storage.engine.core.StorageEngine;
import com.imooc.pan.storage.engine.core.context.DeleteFileContext;
import com.imooc.pan.storage.engine.core.context.StoreFileContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

/**
* @author weileipeng
* @description 针对表【r_pan_file(物理文件信息表)】的数据库操作Service实现
* @createDate 2024-04-06 18:02:17
*/
@Service
public class FileServiceImpl extends ServiceImpl<RPanFileMapper, RPanFile>
    implements IFileService, ApplicationContextAware {

    @Autowired
    private StorageEngine storageEngine;



    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

//    @Autowired
//    @Qualifier(value = "defaultStreamProducer")
//    private IStreamProducer producer;
//
//    @Autowired
//    private IFileChunkService iFileChunkService;

//    /**
//     * 根据条件查询用户的实际文件列表
//     *
//     * @param context
//     * @return
//     */
//    @Override
//    public List<RPanFile> getFileList(QueryRealFileListContext context) {
//        Long userId = context.getUserId();
//        String identifier = context.getIdentifier();
//        LambdaQueryWrapper<RPanFile> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(Objects.nonNull(userId), RPanFile::getCreateUser, userId);
//        queryWrapper.eq(StringUtils.isNotBlank(identifier), RPanFile::getIdentifier, identifier);
//        return list(queryWrapper);
//    }

    /**
     * 上传单文件并保存实体记录
     * <p>
     * 1、上传单文件
     * 2、保存实体记录
     *
     * @param context
     */
    @Override
    public void saveFile(FileSaveContext context) {
        storeMultipartFile(context);
        RPanFile record = doSaveFile(context.getFilename(),
                context.getRealPath(),
                context.getTotalSize(),
                context.getIdentifier(),
                context.getUserId());
        context.setRecord(record);
    }

    /************************************************private************************************************/

    /**
     * 上传单文件
     * 该方法委托文件存储引擎实现
     *
     * @param context
     */
    private void storeMultipartFile(FileSaveContext context) {
        try {
            StoreFileContext storeFileContext = new StoreFileContext();
            storeFileContext.setInputStream(context.getFile().getInputStream());
            storeFileContext.setFilename(context.getFilename());
            storeFileContext.setTotalSize(context.getTotalSize());
            storageEngine.store(storeFileContext);
            context.setRealPath(storeFileContext.getRealPath());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RPanBusinessException("文件上传失败");
        }
    }

    /**
     * 保存实体文件记录
     *
     * @param filename
     * @param realPath
     * @param totalSize
     * @param identifier
     * @param userId
     * @return
     */
    private RPanFile doSaveFile(String filename, String realPath, Long totalSize, String identifier, Long userId) {
        RPanFile record = assembleRPanFile(filename, realPath, totalSize, identifier, userId);
        if (!save(record)) {
            try {
                DeleteFileContext deleteFileContext = new DeleteFileContext();
                deleteFileContext.setRealFilePathList(Lists.newArrayList(realPath));
                storageEngine.delete(deleteFileContext);
            } catch (IOException e) {
                e.printStackTrace();
                ErrorLogEvent errorLogEvent = new ErrorLogEvent(this,"文件物理删除失败，请执行手动删除！文件路径: " + realPath,userId);
              //  producer.sendMessage(PanChannels.ERROR_LOG_OUTPUT, errorLogEvent);
                applicationContext.publishEvent(errorLogEvent);
            }
        }
        return record;
    }

    /**
     * 拼装文件实体对象
     *
     * @param filename
     * @param realPath
     * @param totalSize
     * @param identifier
     * @param userId
     * @return
     */
    private RPanFile assembleRPanFile(String filename, String realPath, Long totalSize, String identifier, Long userId) {
        RPanFile record = new RPanFile();

        record.setFileId(IdUtil.get());
        record.setFilename(filename);
        record.setRealPath(realPath);
        record.setFileSize(String.valueOf(totalSize));
        record.setFileSizeDesc(FileUtils.byteCountToDisplaySize(totalSize));
        record.setFileSuffix(FileUtils.getFileSuffix(filename));
        record.setFilePreviewContentType(FileUtils.getContentType(realPath));
        record.setIdentifier(identifier);
        record.setCreateUser(userId);
        record.setCreateTime(new Date());

        return record;
    }
}




