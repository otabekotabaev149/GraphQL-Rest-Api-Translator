package org.javacoders.main;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.*;
import org.springframework.http.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/graphql-to-rest")
public class IntegrationController {

    private final RestTemplate restTemplate;

    public IntegrationController() {
        this.restTemplate = new RestTemplate();
    }

    @PostMapping
    public JsonNode handleGraphQLQuery(@RequestBody String query) throws IOException {
        // Step 1: Parse the GraphQL query
        Map<String, Object> parseResponse = restTemplate.postForObject("http://localhost:8081/parse", query, Map.class);

//        String rootField = (String) parseResponse.get("rootField");
//        Map<String, String> params = (Map<String, String>) parseResponse.get("parameters");

        // Step 2: Use Rest API generator to dynamically create and call REST endpoint
//        String endpoint = "http://localhost:8083/api/" + rootField;
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<Map<String, String>> entity = new HttpEntity<>(params, headers);
//
//        ResponseEntity<JsonNode> response = restTemplate.exchange(endpoint, HttpMethod.GET, entity, JsonNode.class);
//
//        return response.getBody();

        // Step 2: Translate the parsed data to REST
//        Map<String, Object> translateRequest = new HashMap<>();
//        translateRequest.put("rootField", rootField);
//        translateRequest.put("parameters", params);

        JsonNode translateResponse = restTemplate.postForObject("http://localhost:8082/translate", parseResponse, JsonNode.class);

        return translateResponse;
    }
}
