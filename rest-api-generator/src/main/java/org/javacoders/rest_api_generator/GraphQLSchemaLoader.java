package org.javacoders.rest_api_generator;

import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GraphQLSchemaLoader {
    public GraphQLSchema loadSchema(String schemaPath) throws IOException {
        String schemaSDL = new String(Files.readAllBytes(Paths.get(schemaPath)));

        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeRegistry = schemaParser.parse(schemaSDL);

        RuntimeWiring wiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();

        return schemaGenerator.makeExecutableSchema(typeRegistry, wiring);
    }

    private RuntimeWiring buildWiring() {
        // Implement necessary wiring
        return RuntimeWiring.newRuntimeWiring().build();
    }
}
