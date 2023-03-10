package com.ysg.servicetemplate.common.general.exceptionhandling.exception;

import com.ysg.servicetemplate.common.general.exceptionhandling.BaseErrorCode;

public class UnauthorizedException extends BizException {
    public UnauthorizedException() {
        super(BaseErrorCode.UNAUTHORIZED, "未授权");
    }

    public UnauthorizedException(Object data) {
        super(BaseErrorCode.UNAUTHORIZED, "未授权", data);
    }

    public UnauthorizedException(String message) {
        super(BaseErrorCode.UNAUTHORIZED, message);
    }

    public UnauthorizedException(String message, Object data) {
        super(BaseErrorCode.UNAUTHORIZED, message, data);
    }
}
