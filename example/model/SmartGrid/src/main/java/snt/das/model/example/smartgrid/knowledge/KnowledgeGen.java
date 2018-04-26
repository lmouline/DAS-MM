package snt.das.model.example.smartgrid.knowledge;

import ldas.model.*;
import greycat.Task;
import greycat.Tasks;

@Deprecated
public class KnowledgeGen {

    private static final String CONTEXTS = "Contexts";
    private static final String KNOWLEDGE = "Knowledge";
    private static final String STRATEGIES = "Strategies";
    private static final String REQUIREMENTS = "Requirements";


    public static final Task KNOWLEDGE_GEN = Tasks.newTask()
            .createTypedNode(Knowledge.META.name)
            .setAttribute(Knowledge.NAME.name, Knowledge.NAME.type, "SmartGridKnowledge")
            .updateIndex(Knowledges.META.name)
            .setAsVar(KNOWLEDGE)
            .readIndex(Contexts.META.name)
            .setAsVar(CONTEXTS)
            .readIndex(Strategies.META.name)
            .setAsVar(STRATEGIES)
            .readIndex(Requirements.META.name)
            .setAsVar(REQUIREMENTS)
            .readVar(KNOWLEDGE)
            .addVarTo(Knowledge.CONTEXT.name, CONTEXTS)
            .addVarTo(Knowledge.STRATEGIES.name, STRATEGIES)
            .addVarTo(Knowledge.REQUIREMENTS.name, REQUIREMENTS);

}
