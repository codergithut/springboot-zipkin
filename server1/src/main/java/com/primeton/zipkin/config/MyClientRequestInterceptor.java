package com.primeton.zipkin.config;


import com.github.kristofa.brave.*;
import com.twitter.zipkin.gen.Endpoint;

import static com.github.kristofa.brave.internal.Util.checkNotNull;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/12/5
 * @description
 */
public class MyClientRequestInterceptor extends ClientRequestInterceptor {


    ClientTracer clientTracer;

    public MyClientRequestInterceptor(ClientTracer clientTracer) {
        super(clientTracer);
    }

    /**
     * Handles outgoing request.
     *
     * @param adapter The adapter deals with implementation specific details.
     */
    public void handle(ClientRequestAdapter adapter) {

        SpanId spanId = clientTracer.startNewSpan(adapter.getSpanName());
        if (spanId == null) {
            // We will not trace this request.
            adapter.addSpanIdToRequest(null);
        } else {
            adapter.addSpanIdToRequest(spanId);
            for (KeyValueAnnotation annotation : adapter.requestAnnotations()) {
                clientTracer.submitBinaryAnnotation(annotation.getKey(), annotation.getValue());
            }
            recordClientSentAnnotations(adapter.serverAddress());
        }
    }

    private void recordClientSentAnnotations(Endpoint serverAddress) {
        if (serverAddress == null) {
            clientTracer.setClientSent();
        } else {
            clientTracer.setClientSent(serverAddress.ipv4, serverAddress.port, serverAddress.service_name);
        }
    }
}
