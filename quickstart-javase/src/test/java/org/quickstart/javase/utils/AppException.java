package org.quickstart.javase.utils;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/17 23:33
 * @version v1.0
 */
public class AppException extends RuntimeException {

    private HandleExceptionEnum errorType;
    private String errorDeatil;

    public AppException(HandleExceptionEnum errorType, String errorDeatil) {
        this.errorType = errorType;
        this.errorDeatil = errorDeatil;
    }

    public AppException(HandleExceptionEnum errorType, String errorDeatil, Exception e) {
        this.errorType = errorType;
        this.errorDeatil = errorDeatil;
    }

}
