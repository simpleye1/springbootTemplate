package com.ysg.servicetemplate.common.general.exceptionhandling.responsehandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.ysg.servicetemplate.common.general.exceptionhandling.exception.InvalidParameterException;
import com.ysg.servicetemplate.common.general.exceptionhandling.exception.UnprocessableRequestEntityException;
import com.ysg.servicetemplate.common.general.utils.JsonUtils;
import feign.FeignException;
import feign.Request;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.event.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.nio.ByteBuffer;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@RestControllerAdvice
@Slf4j
public class GlobalGeneralExceptionHandler {

    @ExceptionHandler({FeignException.class})
    public ResponseEntity<ErrorResponse> feignServerException(FeignException e) {
        log.error("==== FEIGN EXCEPTION THROWN ====");
        if (e.status() >= 500) {
            printFeignResponse(e, Level.ERROR);
            printFeignRequest(e, Level.ERROR);
            return new ResponseEntity<>(ErrorResponse.defaultInternalServerErrorResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            printFeignResponse(e, Level.WARN);
            printFeignRequest(e, Level.WARN);
            HttpStatus status = HttpStatus.resolve(e.status());
            return new ResponseEntity<>(extractErrorResponseFromFeignException(e),
                    Objects.nonNull(status) ? status : HttpStatus.BAD_REQUEST);
        }
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse runtimeException(RuntimeException e, HttpServletRequest request) {
        log.error("==== SERVER EXCEPTION THROWN ====");
        printClientRequest(request, Level.ERROR);
        log.error(e.getMessage(), e);
        return ErrorResponse.defaultInternalServerErrorResponse();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ErrorResponse methodArgumentNotValidException(Exception e) {
        List<FieldError> errorList = e instanceof MethodArgumentNotValidException
                ? ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors()
                : ((BindException) e).getFieldErrors();

        Map<String, Object> errors = errorList.stream()
                .collect(toMap(FieldError::getField,
                        ex -> Optional.ofNullable(ex.getDefaultMessage()).orElse(""),
                        (e1, e2) -> e1));
        return new ErrorResponse(new InvalidParameterException(errors));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse methodConstraintViolationException(ConstraintViolationException e) {
        List<ErrorKeyValue<String, String>> errors = e.getConstraintViolations()
                .stream()
                .map(validation -> new ErrorKeyValue<>(validation.getPropertyPath().toString(), validation.getMessage()))
                .collect(toList());
        return new ErrorResponse(new InvalidParameterException(errors));
    }

    @ExceptionHandler( {JsonMappingException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorResponse httpMessageNotReadableException(Exception exception, HttpServletRequest request) {
        log.warn("RECEIVED AN UNPROCESSABLE HTTP REQUEST BODY.");
        printClientRequest(request, Level.WARN);
        log.warn("REASON: {}", exception.getMessage());
        return new ErrorResponse(new UnprocessableRequestEntityException());
    }

    private void printClientRequest(HttpServletRequest request, Level level) {

        String queryString = request.getQueryString();
        String method = request.getMethod();
        String url = request.getRequestURL().toString();

        String contentString = null;
        if(request instanceof ContentCachingRequestWrapper){
            byte[] contentAsByteArray = ((ContentCachingRequestWrapper) request).getContentAsByteArray();
            contentString = new String(contentAsByteArray, 0, contentAsByteArray.length);
        }

        switch (level) {
            case WARN:
                log.warn("CLIENT REQUEST ---> request: {} {} \r\n body:{}", method, url + "?" + queryString, contentString);
                break;
            case ERROR:
            default:
                log.error("CLIENT REQUEST ---> request: {} {} \r\n body:{}", method, url + "?" + queryString, contentString);
                break;
        }
    }

    @SneakyThrows
    private ErrorResponse extractErrorResponseFromFeignException(FeignException e) {
        if (!e.responseBody().isPresent()) {
            return new ErrorResponse();
        }
        ByteBuffer byteBuffer = e.responseBody().get();
        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes, 0, bytes.length);
        String str = new String(bytes, 0, bytes.length);
        return JsonUtils.unmarshal(str, ErrorResponse.class);
    }

    private void printFeignResponse(FeignException e, Level level) {
        switch (level) {
            case WARN:
                log.warn("FEIGN RESPONSE <-- status code: {}, \r\n headers: {}, \r\n body: {}", e.status(), e.responseHeaders(),
                        e.contentUTF8());
                break;
            case ERROR:
            default:
                log.error("FEIGN RESPONSE <-- status code: {}, \r\n headers: {}, \r\n body: {}", e.status(), e.responseHeaders(),
                        e.contentUTF8());
                break;
        }
    }

    private void printFeignRequest(FeignException e, Level level) {
        Request feignRequest = e.request();
        if (feignRequest == null) {
            return;
        }
        byte[] body = feignRequest.body();
        if (body == null) {
            body = new byte[0];
        }
        String bodyStr = new String(body, 0, body.length);
        String method = feignRequest.httpMethod().name();
        String url = feignRequest.url();
        Map<String, Collection<String>> headers = feignRequest.headers();
        switch (level) {
            case WARN:
                log.warn("FEIGN REQUEST ---> request: {} {}, \r\n header: {}, \r\n body: {}", method, url, headers, bodyStr);
                break;
            case ERROR:
            default:
                log.error("FEIGN REQUEST ---> request: {} {}, \r\n header: {}, \r\n body: {}", method, url, headers, bodyStr);
                break;
        }
    }
}
