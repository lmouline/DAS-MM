package snt.das.model.example.smartgrid.action;

import das.model.*;
import greycat.Node;
import greycat.Task;
import greycat.TaskContext;
import greycat.Tasks;

public class ActionGen {

    private static final String CURRENT_STRATEGY = "strategy";
    private static final String CURRENT_TACTIC = "strategy";
    private static final String CURRENT_CONDITION = "condition";
    private static final String CURRENT_INPUT = "input";
    private static final String CURRENT_IMPACT = "impact";
    private static final String ALL_STRUCTURES = "allStructures";
    private static final String CURRENT_ACTION = "action";

    private static final Task CREATE_STRAT_1 = Tasks.newTask()
            .createTypedNode(Strategy.META.name)
            .setAttribute(Strategy.NAME.name, Strategy.NAME.type, "managingCustomer")
            .updateIndex(Strategies.META.name)
            .setAsVar(CURRENT_STRATEGY)

            .createTypedNode(Tactic.META.name)
            .setAttribute(Tactic.NAME.name, Tactic.NAME.type, "reducingAmps")
            .setAsVar(CURRENT_TACTIC)
            .readVar(CURRENT_STRATEGY)
            .addVarTo(Strategy.TACTICS.name, CURRENT_TACTIC)
            // Creation condition
            .createTypedNode(Condition.META.name)
            .setAsVar(CURRENT_CONDITION)
            .readVar(CURRENT_TACTIC)
            .addVarTo(Tactic.CONDITION.name, CURRENT_CONDITION)
            .readIndex(Contexts.META.name)
            .traverse(Context.STRUCTURES.name)
            .setAsVar(ALL_STRUCTURES)
            .select((Node node, TaskContext ctx) -> ((Structure)node).getName().equals("Customer"))
            .setAsVar(CURRENT_INPUT)
            .readVar(CURRENT_CONDITION)
            .addVarTo(Condition.INPUT.name, CURRENT_INPUT)
            .readVar(ALL_STRUCTURES)
            .select((Node node, TaskContext ctx) -> ((Structure)node).getName().equals("Meter"))
            .setAsVar(CURRENT_INPUT)
            .readVar(CURRENT_CONDITION)
            .addVarTo(Condition.INPUT.name, CURRENT_INPUT)
            // Creation of actions
            .createTypedNode(Action.META.name)
            .setAttribute(Action.NAME.name, Action.NAME.type, "askReduce")
            .setAsVar(CURRENT_ACTION)
            .readVar(CURRENT_TACTIC)
            .addVarTo(Tactic.ACTIONS.name,CURRENT_ACTION)
            .readVar(ALL_STRUCTURES)
            .select((Node node, TaskContext ctx) -> ((Structure)node).getName().equals("Consumption"))
            .setAsVar(CURRENT_IMPACT)
            .readVar(CURRENT_ACTION)
            .addVarTo(Action.IMPACTED.name,CURRENT_IMPACT)
            .readVar(ALL_STRUCTURES)
            .select((Node node, TaskContext ctx) -> ((Structure)node).getName().equals("Meter"))
            .setAsVar(CURRENT_IMPACT)
            .readVar(CURRENT_ACTION)
            .addVarTo(Action.IMPACTED.name,CURRENT_IMPACT)
            .createTypedNode(Action.META.name)
            .setAttribute(Action.NAME.name, Action.NAME.type, "checkNewLimit")
            .setAsVar(CURRENT_ACTION)
            .readVar(CURRENT_TACTIC)
            .addVarTo(Tactic.ACTIONS.name,CURRENT_ACTION)
            .createTypedNode(Action.META.name)
            .setAttribute(Action.NAME.name, Action.NAME.type, "notifyUser")
            .setAsVar(CURRENT_ACTION)
            .readVar(CURRENT_TACTIC)
            .addVarTo(Tactic.ACTIONS.name,CURRENT_ACTION)



            .createTypedNode(Tactic.META.name)
            .setAttribute(Tactic.NAME.name, Tactic.NAME.type, "cuttingOff")
            .setAsVar(CURRENT_TACTIC)
            .readVar(CURRENT_STRATEGY)
            .addVarTo(Strategy.TACTICS.name, CURRENT_TACTIC);

    private static final Task CREATE_STRAT_2 = Tasks.newTask()
            .createTypedNode(Strategy.META.name)
            .setAttribute(Strategy.NAME.name, Strategy.NAME.type, "modifyingSMConnection")
            .updateIndex(Strategies.META.name)
            .setAsVar(CURRENT_STRATEGY)

            .createTypedNode(Tactic.META.name)
            .setAttribute(Tactic.NAME.name, Tactic.NAME.type, "modifyingRepeater")
            .setAsVar(CURRENT_TACTIC)
            .readVar(CURRENT_STRATEGY)
            .addVarTo(Strategy.TACTICS.name, CURRENT_TACTIC)

            .createTypedNode(Tactic.META.name)
            .setAttribute(Tactic.NAME.name, Tactic.NAME.type, "addingRepeater")
            .setAsVar(CURRENT_TACTIC)
            .readVar(CURRENT_STRATEGY)
            .addVarTo(Strategy.TACTICS.name, CURRENT_TACTIC)

            .createTypedNode(Tactic.META.name)
            .setAttribute(Tactic.NAME.name, Tactic.NAME.type, "removingRepeater")
            .setAsVar(CURRENT_TACTIC)
            .readVar(CURRENT_STRATEGY)
            .addVarTo(Strategy.TACTICS.name, CURRENT_TACTIC);

    private static final Task CREATE_STRAT_3 = Tasks.newTask()
            .createTypedNode(Strategy.META.name)
            .setAttribute(Strategy.NAME.name, Strategy.NAME.type, "modifyingNetwork")
            .updateIndex(Strategies.META.name)
            .setAsVar(CURRENT_STRATEGY)

            .createTypedNode(Tactic.META.name)
            .setAttribute(Tactic.NAME.name, Tactic.NAME.type, "addingCable")
            .setAsVar(CURRENT_TACTIC)
            .readVar(CURRENT_STRATEGY)
            .addVarTo(Strategy.TACTICS.name, CURRENT_TACTIC)

            .createTypedNode(Tactic.META.name)
            .setAttribute(Tactic.NAME.name, Tactic.NAME.type, "removingCable")
            .setAsVar(CURRENT_TACTIC)
            .readVar(CURRENT_STRATEGY)
            .addVarTo(Strategy.TACTICS.name, CURRENT_TACTIC)

            .createTypedNode(Tactic.META.name)
            .setAttribute(Tactic.NAME.name, Tactic.NAME.type, "replacingCable")
            .setAsVar(CURRENT_TACTIC)
            .readVar(CURRENT_STRATEGY)
            .addVarTo(Strategy.TACTICS.name, CURRENT_TACTIC);

    public static final Task ACTION_GEN = Tasks.newTask()
            .pipe(CREATE_STRAT_1, CREATE_STRAT_2, CREATE_STRAT_3);
}
