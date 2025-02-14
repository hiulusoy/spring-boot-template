package com.hiu.template.springboottemplate.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData {
    private Object item;
    private List<?> items;

    public ResponseData(Object item, List<?> items) {
        this.item = item;
        this.items = items;
    }
}
