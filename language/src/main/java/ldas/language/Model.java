/**
 * Copyright 2017 the LDAS authors.  All rights reserved.
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
package ldas.language;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;

public final class Model {
    private Context context;
    private String name;

    Model(String name) {
        this.name = name;
    }


    void parse(CharStream fileStream) {
        TokenSource tokenSrc = new ldas.language.LDASLanguageLexer(fileStream);
        BufferedTokenStream tokenStream = new CommonTokenStream(tokenSrc);
        ldas.language.LDASLanguageParser parser = new ldas.language.LDASLanguageParser(tokenStream);
        ldas.language.LDASLanguageParser.KnowledgeDclContext kdgeDclContext = parser.knowledgeDcl();

        // Context
        ldas.language.LDASLanguageParser.ContextDclContext ctxDclContext = kdgeDclContext.contextDcl();
        if(ctxDclContext != null) {
            String ctxName = ctxDclContext.ctxName.getText();
            this.context = new Context(ctxName);
        }

    }




    public String getName() {
        return this.name;
    }

    public Context getContext() {
        return context;
    }
}
