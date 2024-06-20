package org.javacoders.graphql_parser;

import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

public class GraphQLSchemaUtil {
    private GraphQLSchema schema;

    public GraphQLSchemaUtil(String schemaSDL) {
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeRegistry = schemaParser.parse(schemaSDL);
        RuntimeWiring wiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        this.schema = schemaGenerator.makeExecutableSchema(typeRegistry, wiring);
    }

    private RuntimeWiring buildWiring() {
        // Define wiring
        return RuntimeWiring.newRuntimeWiring().build();
    }

    public GraphQLSchema getSchema() {
        return schema;
    }
}
