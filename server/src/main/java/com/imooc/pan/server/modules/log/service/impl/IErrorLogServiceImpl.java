package com.imooc.pan.server.modules.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.pan.server.modules.log.entity.RPanErrorLog;
import com.imooc.pan.server.modules.log.service.IErrorLogService;
import com.imooc.pan.server.modules.log.mapper.RPanErrorLogMapper;
import org.springframework.stereotype.Service;

/**
* @author weileipeng
* @description 针对表【r_pan_error_log(错误日志表)】的数据库操作Service实现
* @createDate 2024-04-06 18:03:00
*/
@Service
public class IErrorLogServiceImpl extends ServiceImpl<RPanErrorLogMapper, RPanErrorLog>
    implements IErrorLogService {

}




