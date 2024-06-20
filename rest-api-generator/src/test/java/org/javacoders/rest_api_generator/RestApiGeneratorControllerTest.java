package org.javacoders.rest_api_generator;

import static org.junit.jupiter.api.Assertions.*;

import graphql.schema.GraphQLSchema;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestApiGeneratorControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GraphQLSchema schema;

    @Test
    void testHandleQuery() {
        String query = "user";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(Map.of("id", "1"), headers);

        ResponseEntity<Map> response = restTemplate.exchange("/api/{query}", HttpMethod.GET, entity, Map.class, query);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().containsKey("response"));
    }

    @Test
    void testHandleMutation() {
        String mutation = "updateUser";
        Map<String, Object> payload = new HashMap<>();
        payload.put("id", "1");
        payload.put("name", "Updated Name");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

        ResponseEntity<Map> response = restTemplate.exchange("/api/{mutation}", HttpMethod.POST, entity, Map.class, mutation);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().containsKey("response"));
    }
}
