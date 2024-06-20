grammar GraphQL;

// Lexer rules
FRAGMENT: 'fragment';
QUERY: 'query';
NAME: ('_' | 'A'..'Z' | 'a'..'z') ('_' | 'A'..'Z' | 'a'..'z' | '0'..'9')* ;
NUMBER: [0-9]+ ;
STRING: '"' ('\\' . | ~('\\' | '"'))* '"' ;
WS: [ \t\r\n]+ -> skip ;

// Parser rules
document: definition+ ;
definition: operationDefinition | fragmentDefinition ;
operationDefinition: QUERY? selectionSet ;
fragmentDefinition: FRAGMENT NAME 'on' NAME selectionSet ;

selectionSet: '{' selection+ '}' ;
selection: field ;
field: NAME arguments? selectionSet? ;
arguments: '(' argument (',' argument)* ')' ;
argument: NAME ':' value ;
value: NUMBER | STRING | NAME ;
