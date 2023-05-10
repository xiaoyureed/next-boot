package io.github.xiaoyureed.nextbootslim.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public record ResponseWrapper<T>(Integer code, String error, T data) {
    public static <T> ResponseWrapper<T> ok(T data) {
        return new ResponseWrapper<>(0, "", data);
    }

    public static ResponseWrapper<?> ok() {
        return new ResponseWrapper<>(0, "", null);
    }

    public static ResponseWrapper<?> error(String message) {
        return new ResponseWrapper<>(1, message, null);
    }
}
