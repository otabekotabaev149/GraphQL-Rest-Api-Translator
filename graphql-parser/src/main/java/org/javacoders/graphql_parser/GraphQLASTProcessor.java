package org.javacoders.graphql_parser;

import org.antlr.v4.runtime.tree.ParseTree;

public class GraphQLASTProcessor {
    public void processDocument(GraphQLDocument document) {
        // Traverse and process the AST
        ParseTree tree = document.getDocumentTree();
        // Implement traversal and extraction logic here...
    }
}
