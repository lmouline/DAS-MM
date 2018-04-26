package snt.das.model.example.smartgrid.requirement;

import ldas.model.*;
import greycat.Task;
import greycat.Tasks;

@Deprecated
public class RequirementGen {

    private static final String CURRENT_REQ = "requirement";
    private static final String CURRENT_GOAL = "goal";
    private static final String FUNCTIONAL = "functional";
    private static final String QUALITY = "quality";
    private static final String PERFORMANCE = "performance";

    private static final Task NATURES_GEN = Tasks.newTask()
            .createTypedNode(Functional.META.name)
            .setAttribute(Functional.NAME.name, Functional.NAME.type, "functional")
            .defineAsGlobalVar(FUNCTIONAL)
            .updateIndex(Natures.META.name)

            .createTypedNode(Quality.META.name)
            .setAttribute(Quality.NAME.name, Quality.NAME.type, "quality")
            .defineAsGlobalVar(QUALITY)
            .updateIndex(Natures.META.name)

            .createTypedNode(Performance.META.name)
            .setAttribute(Performance.NAME.name, Performance.NAME.type, "performance")
            .defineAsGlobalVar(PERFORMANCE)
            .updateIndex(Natures.META.name);

    public static final Task REQUIREMENT_GEN = Tasks.newTask()
            .pipe(NATURES_GEN)

            .createTypedNode(Requirement.META.name)
            .setAttribute(Requirement.NAME.name, Requirement.NAME.type, "balance")
            .updateIndex(Requirements.META.name)
            .setAsVar(CURRENT_REQ)

            .createTypedNode(Goal.META.name)
            .setAttribute(Goal.GOAL.name, Goal.GOAL.type, "The system shall not have a cable in overload")
            .setAsVar(CURRENT_GOAL)
            .addVarTo(Goal.NATURE.name, QUALITY)
            .readVar(CURRENT_REQ)
            .addVarTo(Requirement.GOALS.name, CURRENT_GOAL)

            .createTypedNode(Goal.META.name)
            .setAttribute(Goal.GOAL.name, Goal.GOAL.type, "The system shall provide enough resources for the current consumption")
            .setAsVar(CURRENT_GOAL)
            .addVarTo(Goal.NATURE.name, PERFORMANCE)
            .readVar(CURRENT_REQ)
            .addVarTo(Requirement.GOALS.name, CURRENT_GOAL)

            .createTypedNode(Goal.META.name)
            .setAttribute(Goal.GOAL.name, Goal.GOAL.type, "The system shall not loss resources")
            .setAsVar(CURRENT_GOAL)
            .addVarTo(Goal.NATURE.name, PERFORMANCE)
            .readVar(CURRENT_REQ)
            .addVarTo(Requirement.GOALS.name, CURRENT_GOAL)




            .createTypedNode(Requirement.META.name)
            .setAttribute(Requirement.NAME.name, Requirement.NAME.type, "balance")
            .updateIndex(Requirements.META.name)
            .setAsVar(CURRENT_REQ);
}
