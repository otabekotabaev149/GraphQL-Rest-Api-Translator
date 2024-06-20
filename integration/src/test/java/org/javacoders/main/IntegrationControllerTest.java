package org.javacoders.main;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testHandleGraphQLQuery() {
        String query = "{ user(id: \"1\") { name, email } }";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(query, headers);

        ResponseEntity<JsonNode> response = restTemplate.postForEntity("/graphql-to-rest", entity, JsonNode.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
