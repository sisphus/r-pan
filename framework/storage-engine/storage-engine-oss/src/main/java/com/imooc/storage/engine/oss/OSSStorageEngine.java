package com.imooc.storage.engine.oss;

import com.imooc.pan.storage.engine.core.AbstractStorageEngine;
import com.imooc.pan.storage.engine.core.context.DeleteFileContext;
import com.imooc.pan.storage.engine.core.context.StoreFileContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 基于OSS的文件存储引擎
 */
@Component
public class OSSStorageEngine extends AbstractStorageEngine {
    @Override
    protected void doStore(StoreFileContext context) throws IOException {

    }

    @Override
    protected void doDelete(DeleteFileContext context) throws IOException {

    }
}
