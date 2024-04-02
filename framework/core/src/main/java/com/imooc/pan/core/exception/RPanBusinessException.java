package com.imooc.pan.core.exception;

import com.imooc.pan.core.response.ResponseCode;
import lombok.Data;

/**
 * 自定义异常类
 */
@Data
public class RPanBusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;

    public RPanBusinessException(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getDesc();
    }

    public RPanBusinessException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RPanBusinessException(String msg) {
        this.code = ResponseCode.ERROR.getCode();
        this.msg = msg;
    }

    public RPanBusinessException() {
        this.code = ResponseCode.ERROR.getCode();
        this.msg = ResponseCode.ERROR.getDesc();
    }

}
