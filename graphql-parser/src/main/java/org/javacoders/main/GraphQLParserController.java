package org.javacoders.main;

import org.javacoders.graphql_parser.GraphQLDocument;
import org.javacoders.graphql_parser.GraphQLParserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/parse")
public class GraphQLParserController {

    @PostMapping
    public Map<String, Object> parseQuery(@RequestBody String query) {
        GraphQLParserService parserService = new GraphQLParserService();
        GraphQLDocument document = parserService.parseQuery(query);

        Map<String, Object> response = new HashMap<>();
        response.put("rootField", document.getRootField());
        response.put("parameters", document.getParameters());
        return response;
    }
}
