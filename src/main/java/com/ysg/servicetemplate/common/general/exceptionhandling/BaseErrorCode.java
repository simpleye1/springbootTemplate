package com.ysg.servicetemplate.common.general.exceptionhandling;

import lombok.Getter;

@Getter
public class BaseErrorCode {

    public static final BaseErrorCode SERVER_ERROR = new BaseErrorCode("server_error");

    public static final BaseErrorCode CLIENT_ERROR = new BaseErrorCode("client_error");

    public static final BaseErrorCode UNPROCESSABLE_REQUEST_BODY = new BaseErrorCode("unprocessable_request_body");

    public static final BaseErrorCode FORBIDDEN = new BaseErrorCode("forbidden");

    public static final BaseErrorCode UNAUTHORIZED = new BaseErrorCode("unauthorized");

    public static final BaseErrorCode INVALID_PARAMETER = new BaseErrorCode("invalid_parameter");

    public static final BaseErrorCode DATA_NOT_FOUND = new BaseErrorCode("data_not_found");

    protected String code;

    @Deprecated
    protected String desc;

    public BaseErrorCode(String errorCode) {
        this.code = errorCode;
    }

    @Deprecated
    protected BaseErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
