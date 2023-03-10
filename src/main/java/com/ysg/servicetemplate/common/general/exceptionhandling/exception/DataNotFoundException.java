package com.ysg.servicetemplate.common.general.exceptionhandling.exception;


import com.ysg.servicetemplate.common.general.exceptionhandling.BaseErrorCode;

public class DataNotFoundException extends BizException {
    public DataNotFoundException() {
        super(BaseErrorCode.DATA_NOT_FOUND, "请求数据未找到");
    }

    public DataNotFoundException(Object data) {
        super(BaseErrorCode.DATA_NOT_FOUND, "请求数据未找到", data);
    }

    public DataNotFoundException(String message) {
        super(BaseErrorCode.DATA_NOT_FOUND, message);
    }

    public DataNotFoundException(String message, Object data) {
        super(BaseErrorCode.DATA_NOT_FOUND, message, data);
    }
}
