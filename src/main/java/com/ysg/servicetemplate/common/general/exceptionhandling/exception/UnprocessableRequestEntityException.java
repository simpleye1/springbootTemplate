package com.ysg.servicetemplate.common.general.exceptionhandling.exception;

import com.ysg.servicetemplate.common.general.exceptionhandling.BaseErrorCode;

public class UnprocessableRequestEntityException extends BizException {
    public UnprocessableRequestEntityException() {
        super(BaseErrorCode.UNPROCESSABLE_REQUEST_BODY, "无法处理的请求体");
    }

    public UnprocessableRequestEntityException(Object data) {
        super(BaseErrorCode.UNPROCESSABLE_REQUEST_BODY, "无法处理的请求体", data);
    }

    public UnprocessableRequestEntityException(String message) {
        super(BaseErrorCode.UNPROCESSABLE_REQUEST_BODY, message);
    }

    public UnprocessableRequestEntityException(String message, Object data) {
        super(BaseErrorCode.UNPROCESSABLE_REQUEST_BODY, message, data);
    }
}
