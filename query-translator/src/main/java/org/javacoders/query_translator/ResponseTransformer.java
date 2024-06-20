package org.javacoders.query_translator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;

import java.io.IOException;

public class ResponseTransformer {
    private final ObjectMapper objectMapper;

    public ResponseTransformer() {
        this.objectMapper = new ObjectMapper();
    }

    public JsonNode transform(Response response) throws IOException {
        String jsonData = response.body().string();
        return objectMapper.readTree(jsonData);
    }
}
