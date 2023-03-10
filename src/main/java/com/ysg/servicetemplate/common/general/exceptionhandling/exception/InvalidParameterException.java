package com.ysg.servicetemplate.common.general.exceptionhandling.exception;

import com.ysg.servicetemplate.common.general.exceptionhandling.BaseErrorCode;

public class InvalidParameterException extends BizException {
    public InvalidParameterException() {
        super(BaseErrorCode.INVALID_PARAMETER, "无效的参数异常");
    }

    public InvalidParameterException(Object data) {
        super(BaseErrorCode.INVALID_PARAMETER, "无效的参数异常", data);
    }

    public InvalidParameterException(String message) {
        super(BaseErrorCode.INVALID_PARAMETER, message);
    }

    public InvalidParameterException(String message, Object data) {
        super(BaseErrorCode.INVALID_PARAMETER, message, data);
    }
}
