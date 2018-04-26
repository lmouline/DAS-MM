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

import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import greycat.Task;
import greycat.Tasks;
import ldas.language.ContextNode;
import ldas.model.Context;
import ldas.model.Contexts;

import javax.lang.model.element.Modifier;

final class ContextGenerator {

    static JavaFile generate(String packagename, ContextNode context) {
        TypeSpec.Builder mainClass = TypeSpec.classBuilder("ContextGen");
        mainClass.addModifiers(Modifier.PUBLIC, Modifier.FINAL);

        FieldSpec initTaskField = FieldSpec.builder(Task.class,"CONTEXT_GEN", Modifier.PUBLIC, Modifier.STATIC)
//                .initializer("Tasks.newTask()\n" +
//                        "            .createTypedNode(Context.META.name)\n" +
//                        "            .setAttribute(Context.NAME.name, Context.NAME.type, \"" + context.getName() +  "\")\n" +
//                        "            .updateIndex(Contexts.META.name);")
                .initializer(CodeBlock.builder()
                        .addStatement("$T.newTask()\n" +
                                "            .createTypedNode($T.META.name)\n" +
                                "            .setAttribute($T.NAME.name, $T.NAME.type, $S)\n" +
                                "            .updateIndex($T.META.name);", Tasks.class,Context.class,Context.class, Context.class, context.getName(), Contexts.class )
                        .build()
                )
                .build();
        mainClass.addField(initTaskField);

        return JavaFile.builder(packagename,mainClass.build()).build();
    }
}
