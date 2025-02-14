package com.hiu.template.springboottemplate.model.response;

import jakarta.annotation.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class GlobalResponseWrapper implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, @Nullable Class converterType) {
        // Yanıt tipi zaten HttpResponseMessage ise sarmaya gerek yok.
        return !returnType.getParameterType().equals(HttpResponseMessage.class);
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body,
                                  @Nullable MethodParameter returnType,
                                  @Nullable MediaType selectedContentType,
                                  @Nullable Class selectedConverterType,
                                  @Nullable ServerHttpRequest request,
                                  @Nullable ServerHttpResponse response) {
        // Eğer yanıt zaten HttpResponseMessage ise direkt döneriz.
        if (body instanceof HttpResponseMessage) {
            return body;
        }
        // Yanıtı HttpResponseMessage ile sarmalıyoruz.
        return HttpResponseMessage.customBuilder()
                .success(true)
                .data(body)
                .msg("Success")
                .build();
    }
}
