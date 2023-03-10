package com.ysg.servicetemplate.common.general.exceptionhandling.responsehandler;

import com.ysg.servicetemplate.common.general.exceptionhandling.exception.BizException;
import com.ysg.servicetemplate.common.general.exceptionhandling.exception.DataNotFoundException;
import com.ysg.servicetemplate.common.general.exceptionhandling.exception.ForbiddenException;
import com.ysg.servicetemplate.common.general.exceptionhandling.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalApplicationExceptionHandler {
    @ExceptionHandler({ForbiddenException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse handleForbiddenException(ForbiddenException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponse handleUnauthorizedException(UnauthorizedException exception) {
        return new ErrorResponse(exception);
    }


    @ExceptionHandler({DataNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleDataNotFoundException(DataNotFoundException exception) {
        return new ErrorResponse(exception);
    }

    @ExceptionHandler({BizException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleAppException(BizException exception) {
        return new ErrorResponse(exception);
    }

}
