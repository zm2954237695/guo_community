package com.example.back.common.exception;

import com.example.back.common.api.ApiResult;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    public ApiResult<Map<String,Object>> handle (ApiException e){
        if(e.getErrorCode()!=null){
            return ApiResult.failed(e.getErrorCode());
        }
        return ApiResult.failed(e.getMessage());
    }
}
