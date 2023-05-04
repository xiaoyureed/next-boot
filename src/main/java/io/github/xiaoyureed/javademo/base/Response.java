package io.github.xiaoyureed.javademo.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Response<T> {
    private Integer code;
    private String error;
    private T data;

    public static <T> Response<T> ok(T data) {
        return new Response<>(0, "", data);
    }

    public static Response<?> ok() {
        return new Response<>(0, "", null);
    }

    public static Response<?> error(String message) {
        return new Response<>(1, message, null);
    }
}
