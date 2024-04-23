package com.imooc.pan.storage.engine.fastdfs;

import com.imooc.pan.storage.engine.core.AbstractStorageEngine;
import com.imooc.pan.storage.engine.core.context.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 基于FastDFS实现的文件存储引擎
 */
@Component
public class FastDFSStorageEngine extends AbstractStorageEngine {
    @Override
    protected void doStore(StoreFileContext context) throws IOException {

    }

    @Override
    protected void doStoreChunk(StoreFileChunkContext context) throws IOException {

    }

    @Override
    protected void doDelete(DeleteFileContext context) throws IOException {

    }

    @Override
    protected void doReadFile(ReadFileContext context) throws IOException {

    }

    @Override
    protected void doMergeFile(MergeFileContext context) throws IOException {

    }
}

