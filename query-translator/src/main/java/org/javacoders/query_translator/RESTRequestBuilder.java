package org.javacoders.query_translator;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;

public class RESTRequestBuilder {
    private final OkHttpClient client;

    public RESTRequestBuilder() {
        this.client = new OkHttpClient();
    }

    public Response sendRequest(String url, String method, Map<String, String> params) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        for (Map.Entry<String, String> param : params.entrySet()) {
            urlBuilder.addQueryParameter(param.getKey(), param.getValue());
        }
        Request request = new Request.Builder().url(urlBuilder.build()).method(method, null).build();
        return client.newCall(request).execute();
    }

    public Response sendRequest(String url, String method, RequestBody body) throws IOException {
        Request request = new Request.Builder().url(url).method(method, body).build();
        return client.newCall(request).execute();
    }
}
