package com.hiu.template.springboottemplate.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Accessors(chain = true)
public class HttpResponseMessage implements Serializable {

    private boolean success;
    private String error;
    private ResponseData responseData;
    private String msg;
    private String token;
    private String refreshToken;
    private long totalCount;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private long timeout;

    // Private constructor used by the builder
    private HttpResponseMessage(boolean success,
                                String error,
                                Object data,
                                String msg,
                                String token,
                                String refreshToken,
                                long totalCount,
                                long timeout) {
        this.success = success;
        this.error = error;
        this.responseData = buildResponseData(data);
        this.msg = msg;
        this.token = token;
        this.refreshToken = refreshToken;
        this.totalCount = totalCount;
        this.timeout = timeout;
    }

    private ResponseData buildResponseData(Object data) {
        if (data == null) {
            return new ResponseData(null, null);
        }
        if (data instanceof Iterable || data.getClass().isArray()) {
            return new ResponseData(null, convertToList(data));
        }
        return new ResponseData(data, null);
    }

    private List<Object> convertToList(Object data) {
        List<Object> list = new ArrayList<>();
        if (data instanceof Iterable<?>) {
            ((Iterable<?>) data).forEach(list::add);
        } else if (data.getClass().isArray()) {
            int length = Array.getLength(data);
            for (int i = 0; i < length; i++) {
                list.add(Array.get(data, i));
            }
        }
        return list;
    }

    // Manuel Builder Pattern
    public static HttpResponseMessageBuilder customBuilder() {
        return new HttpResponseMessageBuilder();
    }

    public static class HttpResponseMessageBuilder {
        private boolean success;
        private String error;
        private Object data;
        private String msg;
        private String token;
        private String refreshToken;
        private long totalCount;
        private long timeout;

        public HttpResponseMessageBuilder success(boolean success) {
            this.success = success;
            return this;
        }

        public HttpResponseMessageBuilder error(String error) {
            this.error = error;
            return this;
        }

        // "data" parametresi: tek obje, Collection veya Array
        public HttpResponseMessageBuilder data(Object data) {
            this.data = data;
            return this;
        }

        public HttpResponseMessageBuilder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public HttpResponseMessageBuilder token(String token) {
            this.token = token;
            return this;
        }

        public HttpResponseMessageBuilder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public HttpResponseMessageBuilder totalCount(long totalCount) {
            this.totalCount = totalCount;
            return this;
        }

        public HttpResponseMessageBuilder timeout(long timeout) {
            this.timeout = timeout;
            return this;
        }

        public HttpResponseMessage build() {
            return new HttpResponseMessage(success, error, data, msg, token, refreshToken, totalCount, timeout);
        }
    }
}
