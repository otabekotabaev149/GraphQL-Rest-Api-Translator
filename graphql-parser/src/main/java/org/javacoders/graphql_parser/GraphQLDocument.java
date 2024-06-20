package org.javacoders.graphql_parser;

import graphql.parser.antlr.GraphqlParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashMap;
import java.util.Map;

public class GraphQLDocument {
    private ParseTree documentTree;

    public GraphQLDocument(){}
    public GraphQLDocument(ParseTree documentTree) {
        this.documentTree = documentTree;
    }

    // Add methods to process and extract data

    public ParseTree getDocumentTree() {
        return documentTree;
    }

    public void setDocumentTree(ParseTree documentTree) {
        this.documentTree = documentTree;
    }

    // Assuming a simple structure, provide methods to extract root field and parameters
    public String getRootField() {
        // Logic to extract root field from the AST
        // This is a simplified example, adjust based on your AST structure

        // Assuming the root field is the first field in the first selection set
        ParseTree selectionSetNode = documentTree.getChild(0).getChild(0).getChild(1); // document -> definition -> operationDefinition -> selectionSet
        ParseTree fieldNode = selectionSetNode.getChild(1).getChild(0);    // selectionSet -> '{' selection '}'
        return fieldNode.getChild(0).getText();
    }

    public Map<String, String> getParameters() {
        // Logic to extract parameters from the AST
        // This is a simplified example, adjust based on your AST structure
        Map<String, String> params = new HashMap<>();

        ParseTree selectionSetNode = documentTree.getChild(0).getChild(0).getChild(1); // document -> definition -> operationDefinition -> selectionSet
        ParseTree fieldNode = selectionSetNode.getChild(1).getChild(0);    // selectionSet -> '{' selection '}'

        if (fieldNode.getChildCount() > 1 && fieldNode.getChild(1) instanceof GraphqlParser.ArgumentsContext) {
            ParseTree argumentsNode = fieldNode.getChild(1);
            for (int i = 0; i < argumentsNode.getChildCount(); i++) {
                if (argumentsNode.getChild(i) instanceof GraphqlParser.ArgumentContext) {
                    ParseTree argumentNode = argumentsNode.getChild(i);
                    String paramName = argumentNode.getChild(0).getText();
                    String paramValue = argumentNode.getChild(2).getText(); // argument -> NAME ':' value
                    params.put(paramName, paramValue);
                }
            }
        }

        return params;
    }

    public static void main(String[] args) {
        GraphQLDocument document = new GraphQLParserService().parseQuery("""
                query {
                    user(id: 2) {
                        id
                        name
                        profile {
                            age
                            bio
                        }
                        posts {
                            id
                            title
                            comments {
                                id
                                content
                            }
                            likes {
                                id
                                user {
                                    id
                                    name
                                }
                            }
                        }
                    }
                }
                """);
        System.out.println(document.getRootField());
        System.out.println(document.getParameters().entrySet().iterator().next().getKey());
        System.out.println(document.getParameters().entrySet().iterator().next().getValue());
    }
}
