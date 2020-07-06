package com.sync.common;

public enum ErrorCode {
  
	FORBIDDEN(403, "FORBIDDEN"),
    
    INTERNAL_ERROR(1000001, "System error."),
    
    OPERATING_ERROR(1000002, " Operating too fast."),
    
    OBJECT_IS_NOT_EXISTS(1000003, "object is not exists."),
        
    INVALID_PARAM(1000005, "the param is falied."),
    
    CODE_PARAM(1000006, "the code is error."),
        
    DB_ERROR(2000001, "db error."),
    
    REDIS_ERROR(3000001, "redis error."),
    
    ERROR_LINK(4000001, "link error.");

    public final int CODE;
    public final String MSG;
    
    ErrorCode(int code, String msg) {
        this.CODE = code;
        this.MSG = msg;
    }
}
