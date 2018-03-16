package snt.das.model.example.smartgrid.diagnosisAlgo;

import das.model.Condition;
import das.model.Goal;
import das.model.Tactic;
import greycat.*;

public class GetGoals {

    private static final String TACTICS = "tactics";
    private static final String TIMED_TACTICS = "timedTactics";
    private static final String TIMES = "injectedTimes";
    private static final String GOALS = "goals";


    private static final Task ALGO_1 = Tasks.newTask()
            .readVar(TACTICS)
            .forEach(Tasks.newTask()
                    .thenDo((TaskContext ctx) -> {
                        Tactic tactic = (Tactic) ctx.result().get(0);
                        int idx = ctx.intVar("i");
                        TaskResult times = ctx.variable(TIMES);
                        long time = (long) times.get(idx - 1);

                        tactic.travelInTime(time, (Node n) -> {
                            Tactic casted = (Tactic) n;
                            ctx.addToGlobalVariable(TIMED_TACTICS,casted);
                            ctx.continueTask();
                        });
                    })
            )
            .readVar(TIMED_TACTICS)
            .traverse(Tactic.CONDITION.name)
            .traverse(Condition.IMPLEMENT.name)
            .addToVar(GOALS);

    public static final Goal[] getGoals(final Graph graph, final Tactic[] t, final long[] times) {
        final TaskResult res = Tasks.newTask()
                .declareGlobalVar(TACTICS)
                .travelInWorld("0")
                .inject(t)
                .addToVar(TACTICS)
                .inject(times)
                .addToVar(TIMES)
                .pipe(ALGO_1)
                .readVar(GOALS)
                .executeSync(graph);

        final Goal[] toReturn = new Goal[res.size()];
        for (int i = 0; i < toReturn.length; i++) {
            toReturn[i] = (Goal) res.get(i);
        }

        return toReturn;
    }
}
