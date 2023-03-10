package com.ysg.servicetemplate.common.general;

import com.ysg.servicetemplate.ApiBaseControllerTest;
import com.ysg.servicetemplate.common.general.exceptionhandling.BaseErrorCode;
import com.ysg.servicetemplate.common.general.exceptionhandling.exception.BizException;
import com.ysg.servicetemplate.common.general.exceptionhandling.responsehandler.ErrorResponse;
import com.ysg.servicetemplate.common.general.utils.JsonUtils;
import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Objects;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.when;

@ActiveProfiles("api-test")
class GlobalExceptionHandlerTest extends ApiBaseControllerTest {

    @MockBean
    protected StubController stubController;

    @Test
    void should_httpCode_500_when_throw_FeignException() {
        Request request = Request.create(Request.HttpMethod.GET, "/test", new HashMap<>(), Request.Body.create(new byte[0]),
                new RequestTemplate());
        FeignException.FeignServerException exception = new FeignException.FeignServerException(500, "", request, new byte[0], null);
        when(stubController.test()).thenThrow(exception);

        ErrorResponse result = given().get("test").then().statusCode(500).extract().as(ErrorResponse.class);

        then(result.getErrorCode()).isEqualTo(BaseErrorCode.SERVER_ERROR.getCode());
        then(result.getMessage()).isEqualTo(ErrorResponse.defaultInternalServerErrorResponse().getMessage());
    }

    @Test
    void should_httpCode_400_when_throw_FeignClientException() {
        Request request = Request.create(Request.HttpMethod.GET, "/test", new HashMap<>(), Request.Body.create(new byte[0]),
                new RequestTemplate());
        ErrorResponse feignErrorResponse = new ErrorResponse(new BizException(BaseErrorCode.CLIENT_ERROR));
        FeignException.FeignClientException exception = new FeignException.FeignClientException(400, "", request,
                Objects.requireNonNull(JsonUtils.silentMarshal(feignErrorResponse)).getBytes(StandardCharsets.UTF_8), null);
        when(stubController.test()).thenThrow(exception);

        ErrorResponse result = given().get("test").then().statusCode(400).extract().as(ErrorResponse.class);

        then(result.getErrorCode()).isEqualTo(feignErrorResponse.getErrorCode());
        then(result.getMessage()).isEqualTo(feignErrorResponse.getMessage());
    }

    @Test
    void should_httpCode_404_when_throw_FeignNotFoundException() {
        Request request = Request.create(Request.HttpMethod.GET, "/test", new HashMap<>(), Request.Body.create(new byte[0]),
                new RequestTemplate());
        ErrorResponse feignErrorResponse = new ErrorResponse(new BizException(BaseErrorCode.DATA_NOT_FOUND));
        FeignException.NotFound exception = new FeignException.NotFound("", request,
                Objects.requireNonNull(JsonUtils.silentMarshal(feignErrorResponse)).getBytes(StandardCharsets.UTF_8), null);
        when(stubController.test()).thenThrow(exception);
        ErrorResponse result = given().get("test").then().statusCode(404).extract().as(ErrorResponse.class);

        then(result.getErrorCode()).isEqualTo(BaseErrorCode.DATA_NOT_FOUND.getCode());
        then(result.getMessage()).isNotNull();
    }

    @Test
    void should_httpCode_500_when_throw_runtimeException() {
        when(stubController.test()).thenThrow(new RuntimeException("test exception"));

        ErrorResponse result = given().get("test").then().statusCode(500).extract().as(ErrorResponse.class);

        then(result.getErrorCode()).isEqualTo(BaseErrorCode.SERVER_ERROR.getCode());
        then(result.getMessage()).isNotNull();
    }
}

@Controller
class StubController {
    @RequestMapping("test")
    public Object test() {
        return null;
    }
}