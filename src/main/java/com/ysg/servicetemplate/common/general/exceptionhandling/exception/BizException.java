package com.ysg.servicetemplate.common.general.exceptionhandling.exception;

import com.ysg.servicetemplate.common.general.exceptionhandling.BaseErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BizException extends RuntimeException {

    protected BaseErrorCode baseErrorCode;

    protected Object data;

    public BizException(BaseErrorCode code, String errorMsg) {
        super(errorMsg);
        this.baseErrorCode = code;
    }

    public BizException(BaseErrorCode code, String errorMsg, Object data) {
        super(errorMsg);
        this.baseErrorCode = code;
        this.data = data;
    }

    public BizException(BaseErrorCode baseErrorCode, Object data) {
        super(baseErrorCode.getCode());
        this.baseErrorCode = baseErrorCode;
        this.data = data;
    }

    public BizException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode.getCode());
        this.baseErrorCode = baseErrorCode;
    }
}
