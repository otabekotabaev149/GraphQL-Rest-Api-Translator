package org.javacoders.main;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QueryTranslatorControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testTranslateQuery() {
        Map<String, Object> request = new HashMap<>();
        request.put("rootField", "user");
        request.put("parameters", new HashMap<String, String>() {{
            put("id", "1");
        }});

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<JsonNode> response = restTemplate.postForEntity("/translate", entity, JsonNode.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
