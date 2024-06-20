package org.javacoders.rest_api_generator;

import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class RESTEndpointGenerator {
    private final Map<String, GraphQLFieldDefinition> queries;
    private final Map<String, GraphQLFieldDefinition> mutations;

    public RESTEndpointGenerator(GraphQLSchema schema) {
        this.queries = loadQueries(schema);
        this.mutations = loadMutations(schema);
    }

    private Map<String, GraphQLFieldDefinition> loadQueries(GraphQLSchema schema) {
        GraphQLObjectType queryType = schema.getQueryType();
        Map<String, GraphQLFieldDefinition> queries = new HashMap<>();
        for (GraphQLFieldDefinition field : queryType.getFieldDefinitions()) {
            queries.put(field.getName(), field);
        }
        return queries;
    }

    private Map<String, GraphQLFieldDefinition> loadMutations(GraphQLSchema schema) {
        GraphQLObjectType mutationType = schema.getMutationType();
        Map<String, GraphQLFieldDefinition> mutations = new HashMap<>();
        if (mutationType != null) {
            for (GraphQLFieldDefinition field : mutationType.getFieldDefinitions()) {
                mutations.put(field.getName(), field);
            }
        }
        return mutations;
    }

    @GetMapping("/{query}")
    public Object handleQuery(@PathVariable String query, @RequestParam Map<String, String> params) {
        // Logic to handle queries based on GraphQL schema
        GraphQLFieldDefinition field = queries.get(query);
        if (field != null) {
            return processRequest(field, params);
        }
        return Collections.singletonMap("error", "Query not found");
    }

    @PostMapping("/{mutation}")
    public Object handleMutation(@PathVariable String mutation, @RequestBody Map<String, Object> payload) {
        // Logic to handle mutations based on GraphQL schema
        GraphQLFieldDefinition field = mutations.get(mutation);
        if (field != null) {
            return processRequest(field, payload);
        }
        return Collections.singletonMap("error", "Mutation not found");
    }

    private Object processRequest(GraphQLFieldDefinition field, Map<String, ?> params) {
        // Dynamic processing based on GraphQL field and parameters
        return Collections.singletonMap("response", "Processed: " + field.getName());
    }
}
