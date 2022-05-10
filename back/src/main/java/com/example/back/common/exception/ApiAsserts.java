package com.example.back.common.exception;

import com.example.back.common.api.IErrorCode;

public class ApiAsserts {
    /**
     * 抛失败异常
     */

    public static void fail(String message){
        throw new ApiException(message);
    }
    public static void fail(IErrorCode errorCode){
        throw new ApiException(errorCode);
    }
}
