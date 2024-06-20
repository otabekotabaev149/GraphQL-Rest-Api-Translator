package org.javacoders.query_translator;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QueryTranslatorService {
    protected final GraphQLToRESTMapper mapper;
    private final RESTRequestBuilder requestBuilder;
    private final ResponseTransformer transformer;

    public QueryTranslatorService() {
        this.mapper = new GraphQLToRESTMapper();
        this.requestBuilder = new RESTRequestBuilder();
        this.transformer = new ResponseTransformer();
    }

    public JsonNode translateAndSendRequest(String graphqlField, Map<String, String> params) throws IOException {
        String endpoint = mapper.getValue(graphqlField);
        if (endpoint == null) {
            throw new IllegalArgumentException("No mapping found for GraphQL field: " + graphqlField);
        }

        // Example: Replace placeholders in the endpoint
        for (Map.Entry<String, String> param : params.entrySet()) {
            endpoint = endpoint.replace("{" + param.getKey() + "}", param.getValue());
        }

        Response response = requestBuilder.sendRequest(endpoint, "GET", new HashMap<>());
        return transformer.transform(response);
    }
}
