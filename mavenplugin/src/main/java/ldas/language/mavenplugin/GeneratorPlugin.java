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
package ldas.language.mavenplugin;

import ldas.language.generator.GenerationException;
import ldas.language.generator.Generator;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import java.io.File;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES, requiresDependencyResolution = ResolutionScope.COMPILE)
public class GeneratorPlugin extends AbstractMojo {

    /**
     * File containing the LDAS model definition.
     * <br>
     * File should have "{@value ldas.language.generator.Generator#FILE_EXTENSION}" extension
     */
    @Parameter(required = true)
    private File input;

    /**
     * Package name for the generated Java code.
     */
    @Parameter(required = true)
    private String packageName;

    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;

    @Parameter(defaultValue = "${project.build.directory}/generated-sources/ldas-model", readonly = true)
    private File targetGen;

    @Override
    public void execute() throws MojoExecutionException {
        Generator generator = new Generator();
        try {
            generator.parse(input);
            generator.generateJava(packageName,targetGen);
            project.addCompileSourceRoot(targetGen.getAbsolutePath());
        } catch (GenerationException e) {
            throw new MojoExecutionException("Error during LDAS parse stage", e);
        }
    }
}
