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

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import java.io.File;
import java.io.IOException;

public final class ModelGen {
    public static Model parse(File srcFile) {
        if(srcFile == null) {
            return null;
        }

        CharStream fileStream;
        try {
            fileStream = CharStreams.fromFileName(srcFile.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Incorrect file: " + srcFile + "\n" + e.getMessage());
        }

        if(fileStream.size() == 0) {
            return new Model(srcFile.getName());
        }

        Model model = new Model(srcFile.getName());
        model.parse(fileStream);
        return model;
    }
}
