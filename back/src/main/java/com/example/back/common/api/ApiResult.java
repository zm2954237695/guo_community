package com.example.back.common.api;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.text.html.Option;
import java.io.Serializable;
import java.util.Optional;


@Data
@NoArgsConstructor
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = -4153430394359594346L;

    private long code;
    private T data;
    private String message;

    public ApiResult(long code,String message,T data){
        this.code=code;
        this.data=data;
        this.message=message;
    }
    public ApiResult(IErrorCode errorCode){
        errorCode  = Optional.ofNullable(errorCode).orElse(ApiErrorCode.FAILED);
        this.code=errorCode.getCode();
        this.message=errorCode.getMessage();
    }

    public static <T> ApiResult<T> success(){
        return new ApiResult<>(ApiErrorCode.SUCCESS.getCode(),ApiErrorCode.SUCCESS.getMessage(), null);
    }

    public static <T> ApiResult<T> success(T data){
        return new ApiResult<>(ApiErrorCode.SUCCESS.getCode(),ApiErrorCode.SUCCESS.getMessage(), data);
    }

    public static <T> ApiResult<T> success(T data,String message){
        return new ApiResult<>(ApiErrorCode.SUCCESS.getCode(), message,data);
    }
    public static <T> ApiResult <T> failed(){
        return new ApiResult<>(ApiErrorCode.FAILED);
    }
    public static <T> ApiResult <T> failed(String message){
        return new ApiResult<>(ApiErrorCode.SUCCESS.getCode(), message,null);
    }
    public static <T> ApiResult <T> failed(IErrorCode errorCode){
        return new ApiResult<>(errorCode.getCode(),errorCode.getMessage(),null);
    }
    public static <T> ApiResult <T> failed(IErrorCode errorCode,String message){
        return new ApiResult<>(errorCode.getCode(),message,null);
    }
    public static <T> ApiResult <T> validateFailed(){
        return failed(ApiErrorCode.VALIDATE_FAILED);
    }
    public static <T> ApiResult<T> validateFailed(String message) {
        return new ApiResult<T>(ApiErrorCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> ApiResult<T> unauthorized(T data) {
        return new ApiResult<T>(ApiErrorCode.UNAUTHORIZED.getCode(), ApiErrorCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> ApiResult<T> forbidden(T data) {
        return new ApiResult<T>(ApiErrorCode.FORBIDDEN.getCode(), ApiErrorCode.FORBIDDEN.getMessage(), data);
    }
}
