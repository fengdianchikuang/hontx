package com.ruoyi.common.exception;

/**
 * 百度证件识别异常类
 */
public class AiBaiDuException extends RuntimeException  {
    private static final long serialVersionUID = 1L;
    private int code = 500;
    private String message;

    public AiBaiDuException(String message) {
        super(message);
        this.message = message;
    }

    public AiBaiDuException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public AiBaiDuException(String message, Throwable cause) {
        super(message);
        this.message = message;
    }

    public AiBaiDuException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
