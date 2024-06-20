package org.javacoders.main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GraphQLParserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testParseQuery() {
        String query = "{ user(id: \"1\") { name, email } }";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(query, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity("/parse", entity, Map.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("user", response.getBody().get("rootField"));
    }
}
