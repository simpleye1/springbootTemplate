package com.ysg.servicetemplate.common.general.exceptionhandling.responsehandler;

import com.ysg.servicetemplate.common.general.exceptionhandling.BaseErrorCode;
import com.ysg.servicetemplate.common.general.exceptionhandling.exception.BizException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private String errorCode;
    private String message;
    private Object data;

    public ErrorResponse(BizException bizException) {
        this.errorCode = bizException.getBaseErrorCode().getCode();
        this.message = bizException.getMessage();
        this.data = bizException.getData();
    }

    public static ErrorResponse defaultInternalServerErrorResponse() {
        return new ErrorResponse(BaseErrorCode.SERVER_ERROR.getCode(), "服务器异常", null);
    }
}
