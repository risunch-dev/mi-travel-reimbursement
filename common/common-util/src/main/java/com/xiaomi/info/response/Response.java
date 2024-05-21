package com.xiaomi.info.response;

import com.xiaomi.info.response.code.ResponseCodeEnum;
import com.xiaomi.info.response.header.DefaultHeader;
import com.xiaomi.info.response.header.IHeader;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: Response
 * Package: com.xiaomi.info.common.response
 * Description:
 *
 * @Author 朱安迪
 * @Create 2024/5/18 13:05
 * @Version 1.0
 */
@Data
public class Response<T extends Serializable> implements Serializable {

    private IHeader header;

    private T body;

    public Response() {
        header = new DefaultHeader(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getDesc());
    }

    public Response(T body) {
        header = new DefaultHeader(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getDesc());
        this.body = body;
    }

    public Response(IHeader header) {
        this.header = new DefaultHeader(header.getCode(), header.getDesc());
    }

    public Response(IHeader header, T body) {
        this.header = new DefaultHeader(header.getCode(), header.getDesc());
        this.body = body;
    }

    /**
     * 判断代码是否成功，不会抛出异常
     * @return boolean
     */
    public boolean isSuccess() {
        return this.getHeader().getCode() == ResponseCodeEnum.SUCCESS.getCode();
    }

    public static <R extends Serializable> Response<R> success() {
        return new Response<>();
    }

    public static <R extends Serializable> Response<R> success(R body) {
        return new Response<>(body);
    }

    public static <R extends Serializable> Response<R> error() {
        return new Response<>(new DefaultHeader(ResponseCodeEnum.SERVER_ERROR.getCode(),
                ResponseCodeEnum.SERVER_ERROR.getDesc()));
    }

    public static <R extends Serializable> Response<R> error(String desc) {
        return new Response<>(new DefaultHeader(ResponseCodeEnum.SERVER_ERROR.getCode(), desc));
    }

    public static <R extends Serializable> Response<R> error(int code, String desc) {
        return new Response<>(new DefaultHeader(code, desc));
    }
}
