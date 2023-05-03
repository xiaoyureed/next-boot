package io.github.xiaoyureed.javademo.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BaseResponse<T> {
    private Integer code;
    private String error;
    private T data;

    public static <T> BaseResponse<T> ok(T data) {
        return new BaseResponse<>(0, "", data);
    }

    public static BaseResponse<?> ok() {
        return new BaseResponse<>(0, "", null);
    }

    public static BaseResponse<?> error(String message) {
        return new BaseResponse<>(1, message, null);
    }
}
