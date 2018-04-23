/**
 * Copyright 2017 The LDAS Authors.  All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 grammar LDASLanguage;

fragment ESC : '\\' (["\\/bfnrt] | UNICODE) ;
fragment UNICODE : 'u' HEX HEX HEX HEX ;
fragment HEX : [0-9a-fA-F] ;

STRING:  '"' (ESC | ~["\\])* '"' | '\'' (ESC | ~["\\])* '\'' ;
IDENT: [a-zA-Z_][a-zA-Z_0-9]*;
NUMBER : [\-]?[0-9]+'.'?[0-9]*;

PRIMITIVE_TYPE: ('boolean' | 'cahr' | 'string' | 'byte' | 'int' | 'double' | 'long' | 'float');
ARRAY_TYPE: PRIMITIVE_TYPE '[]';

knowledgeDcl: importDcl* contextDcl;

importDcl: 'import' pathOrName=STRING;

contextDcl: 'context' ctxName=STRING '{' (attributeDcl | structureDcl | fragmentDcl)* '}';
attributeDcl: (varDcl | constDcl | tempVarDcl);
varDcl: 'var' varName=IDENT ':' type=(PRIMITIVE_TYPE|ARRAY_TYPE);
constDcl: 'const' varName=IDENT ':' type=(PRIMITIVE_TYPE|ARRAY_TYPE) ('=' STRING | IDENT | NUMBER)?;
tempVarDcl: 'temporal' varName=IDENT ':' type=(PRIMITIVE_TYPE|ARRAY_TYPE);
structureDcl: 'structure' structName=STRING ('extends' structParent=IDENT)? '{' (structureDcl | propertyDcl)* '}';
fragmentDcl: 'fragment' structName=STRING ('extends' structParent=IDENT)? '{' (structureDcl | propertyDcl)* '}';
propertyDcl: (attributeDcl | relationDcl) ('{' srcDcl? uncertaintyDcl? connectionDcl? '}')?;
relationDcl: relDcl | constRelDcl | tempRelDcl;
relDcl: 'rel' varName=IDENT ':' type=IDENT;
constRelDcl: 'const' varName=IDENT ':' type=IDENT;
tempRelDcl: 'temporal' varName=IDENT ':' type=IDENT;
srcDcl: 'source' STRING;
uncertaintyDcl: 'uncertainty' NUMBER;
connectionDcl: (influenceDcl | computedDcl | consistentDcl);
influenceDcl: 'influence' IDENT (',' IDENT)*;
computedDcl: 'computed' IDENT (',' IDENT)*;
consistentDcl: 'consistent' IDENT (',' IDENT)*;




