package snt.das.model.example.smartgrid;

import das.model.DASModelPlugin;
import greycat.Graph;
import greycat.GraphBuilder;
import greycat.TaskResult;
import greycat.Tasks;
import greycat.rocksdb.RocksDBStorage;
import greycat.websocket.WSSharedServer;
import snt.das.model.example.smartgrid.action.ActionGen;
import snt.das.model.example.smartgrid.context.ContextGen;
import snt.das.model.example.smartgrid.knowledge.KnowledgeGen;

public class RunnerWithServer {
    public static void main(String[] args) {
        Graph graph = new GraphBuilder()
                .withPlugin(new DASModelPlugin())
                .withStorage(new RocksDBStorage("./example/model/SmartGrid/data"))
                .build();

        int port = 12346;
        WSSharedServer.attach(graph, port);

        graph.connect((Boolean connectOK) -> {
            Tasks.newTask()
                    .travelInWorld("0")
                    .travelInTime(System.currentTimeMillis() + "")
                    .pipe(ContextGen.CONTEXT_GEN, ActionGen.ACTION_GEN, KnowledgeGen.KNOWLEDGE_GEN)
                    .save()
                    .execute(graph, (TaskResult result) -> {
                        if (result.exception() != null) {
                            result.exception().printStackTrace();
                        }

                        result.free();
                        result.clear();
                    });
        });


    }
}
