package org.javacoders.graphql_parser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.javacoders.graphqlparser.GraphQLLexer;
import org.javacoders.graphqlparser.GraphQLParser;

public class GraphQLParserService {
    public GraphQLDocument parseQuery(String query) {
        // Create a lexer
        GraphQLLexer lexer = new GraphQLLexer(CharStreams.fromString(query));

        // Create a parser
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GraphQLParser parser = new GraphQLParser(tokens);

        // Parse and return the AST
        GraphQLParser.DocumentContext documentContext = parser.document();
        return new GraphQLDocument(documentContext);
    }
}
