package com.primeton.zipkin.config;

import com.github.kristofa.brave.http.HttpRequest;
import com.github.kristofa.brave.http.SpanNameProvider;

import java.util.Date;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/12/4
 * @description
 */
public class MySpanNameProvider implements SpanNameProvider {
    public String spanName(HttpRequest request) {
        return request.getHttpMethod() + ":" + new Date().toString();
    }
}
