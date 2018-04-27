package ldas.model.example.smartgrid;

import greycat.Callback;
import greycat.Graph;
import greycat.GraphBuilder;
import greycat.TaskResult;
import greycat.rocksdb.RocksDBStorage;
import ldas.example.smartgrid.model.init.SmartGrid;
import ldas.model.LDASModelPlugin;

public class Runner {

    public static void main(String[] args) {

        Graph graph = new GraphBuilder()
                .withPlugin(new LDASModelPlugin())
                .withStorage(new RocksDBStorage("./example/model/SmartGrid/data"))
                .build();

        graph.connect((Boolean connectOK) -> {

            // Initialize the model with design elements
            SmartGrid.initTask.execute(graph, new Callback<TaskResult>() {
                @Override
                public void on(TaskResult result) {
                    if (result.exception() != null) {
                        result.exception().printStackTrace();
                    }

                    if(result.output() != null) {
                        System.out.println(result.output());
                    }

                    System.out.println("Done");
                }
            });
        });




    }
}
