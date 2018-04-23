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
package ldas.language.generator;

import com.squareup.javapoet.JavaFile;
import ldas.language.Model;
import ldas.language.ModelGen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Generator {
    public static final String FILE_EXTENSION = ".ldas";
    private Model model;


    public void parse(File srcFile) {
        if(srcFile == null) {
            return;
        }

        if(srcFile.getName().endsWith(FILE_EXTENSION)) {
           model = ModelGen.parse(srcFile);
        } else {
            throw new RuntimeException("File should have " + FILE_EXTENSION + " extension.");
        }
    }

    public void generateJava(String packageName, File dest) {
        List<JavaFile> files = new ArrayList<>();

        files.add(MainGenerator.generate(packageName, model));
        files.add(ContextGenerator.generate(packageName, model.getContext()));

        for (int i = 0; i < files.size(); i++) {
            try {
                JavaFile file = files.get(i);
                file.writeTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
