package com.yisn.modules.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yisn on 2021/04/17/19:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    public static final int OK = 200;
    public static final int ERROR = 400;

    private int code;
    private String message;
    private Object data;

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
