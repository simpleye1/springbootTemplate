package com.ysg.servicetemplate.common.general.exceptionhandling.exception;

import com.ysg.servicetemplate.common.general.exceptionhandling.BaseErrorCode;
import lombok.Getter;

@Getter
public class ForbiddenException extends BizException {
    public ForbiddenException() {
        super(BaseErrorCode.FORBIDDEN, "权限非法");
    }

    public ForbiddenException(Object data) {
        super(BaseErrorCode.FORBIDDEN, "权限非法", data);
    }

    public ForbiddenException(String message) {
        super(BaseErrorCode.FORBIDDEN, message);
    }

    public ForbiddenException(String message, Object data) {
        super(BaseErrorCode.FORBIDDEN, message, data);
    }
}
