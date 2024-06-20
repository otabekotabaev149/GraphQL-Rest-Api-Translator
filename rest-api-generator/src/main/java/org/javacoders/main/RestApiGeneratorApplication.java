package org.javacoders.main;

import graphql.schema.GraphQLSchema;
import org.javacoders.rest_api_generator.GraphQLSchemaLoader;
import org.javacoders.rest_api_generator.RESTEndpointGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestApiGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiGeneratorApplication.class, args);
	}

	@Bean
	public GraphQLSchema graphQLSchema() throws Exception {
		GraphQLSchemaLoader loader = new GraphQLSchemaLoader();
		return loader.loadSchema("path/to/schema.graphqls"); // Adjust the path to your schema file
	}

	@Bean
	public RESTEndpointGenerator restEndpointGenerator(GraphQLSchema schema) {
		return new RESTEndpointGenerator(schema);
	}

}
