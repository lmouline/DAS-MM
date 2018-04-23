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

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import greycat.Task;
import ldas.language.Model;

import javax.lang.model.element.Modifier;

final class MainGenerator {

    static JavaFile generate(String packagename, Model model) {

        TypeSpec.Builder mainClass = TypeSpec.classBuilder(cleanClassName(model.getName()));
        mainClass.addModifiers(Modifier.PUBLIC, Modifier.FINAL);


        FieldSpec initTaskField = FieldSpec.builder(Task.class,"initTask", Modifier.PUBLIC, Modifier.STATIC)
                .initializer("Tasks.newTask()\n" +
                        "            .travelInWorld(\"0\")\n" +
                        "            .travelInTime(System.currentTimeMillis() + \"\")\n" +
                        "            .pipe(CONTEXT_GEN)\n" +
                        "            .save();")
                .build();
        mainClass.addField(initTaskField);

        return JavaFile.builder(packagename,mainClass.build()).build();

    }


    private static String cleanClassName(String modelName) {
        int extensionSize = Generator.FILE_EXTENSION.length();
        char firstLetter = modelName.charAt(0);

        return Character.toUpperCase(firstLetter) + modelName.substring(1,modelName.length() - extensionSize);
    }
}
