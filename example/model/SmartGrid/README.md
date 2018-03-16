Model Example: SmartGrid at Luxembourg
--------------------------------------

To allow interactive diagnosis of adaptive systems, we developed a model-based solution.
In this example, we will describe how to use our solution on a smart grid system.
We will first describe a smart grid example, then the different steps that engineers will have to do to use our approach.
As this approach is a Model-Driven Engineering (MDE), using our approach mainly means instantiating the proposed meta-model.
**Disclaimer:** We have not developed any specific language to instantiate easily our meta-model. To do so, an engineer will have to use our basic Java or javascript API.
To show how to instantiate our meta-model, we describe both an object model and a Java code. The complete object model can be seen here (**TODO**) and the Java code [here](src/main/java).

## Description of the example

The National Institute of Standards and Technology (NIST) defines smart grids as "a modernized grid that enables bidirectional flows of energy and uses two-way communication and **control capabilities** that will lead to a collection of **new functionalities and applications**".
These capabilities, functionalities, and applications can be implemented using approaches developed by the adaptive system community [1].

Hartmann et al., [2] described the smart grid at Luxembourg with three elements: central system, concentrator, and smart meters.
Smart meters regularly measure resource consumption (water, gas, or electricity) and report them to the central systems through the concentrator.
A smart meter can also modify the maximal consumption or even cut off the resource.
In addition to storing the consumption data, concentrators autonomously manage smart meters according to the configuration sent by the central system.

Among the different goals of concentrators, they have to avoid any overload on the network.
They have two action points: either on the production side or the consumption side.
They can reduce or increase the production by (dis)connecting production unit or the consumption by modifying the maximally allowed consumption.
However, as all adaptive systems, smart grids are prone to failures [1].

Using our approach, an engineer or a system could try to answer the following questions:

- what goal(s) the system was trying to reach by cutting off the power consumption of the building B?
- when the system has decided to reduce the power consumption of M. and Mrs. X, what was the load of the network of their districts?
- when the system has decided to connect the production unit, what was the expected network load?
- yesterday, there was an overload in the district Z, what were the decisions that have modified the network load during all the previous day?

## Introduction

Our approach can be used both at design time and at run time.
At design time, we define the different static elements that are implied in adaptation process: the actions, the requirements and the data structure for the context (step 1)
At run time, we add the values that are related to the executions of the adaptation process: data about executions of actions and data about context information (step 2).
Based on the model that combine both, we can, at run time, query it to have information about the running adaptation process (step 3).

## Step 1: Describing the adaptation process at design time

### Context

Context meta-model can be seen as a data structure of the collected information about the context (any information relevant for the adaptation process).
Defined at designed time, the values will be created and/or updated at runtime.
Here, we described an object model that instantiated our [context meta-model](../../README.md#graphical-version) and the Java code.
The full version of the object model can be found here (**TODO**) and the Java program [here](src/main/java/snt/das/model/example/smartgrid/context/ContextGen.java).
The presented object model has been built in accordance with the one described by Hartmann et al. in [2].
Here the presented meta-model by Hartmann et al. in [2]:

![](https://raw.githubusercontent.com/thomashartmann/smartgrid-topology-generator/master/lu.snt.smartgrid-topology-generator.model/meta-model.png)

In this document, we will depicted how to create: the context, one structure (entity), one of its attribute, [...] (**TODO**)

**Creation of the context**:

![](img/context-mm-context.svg)

Java code:
```java
Tasks.newTask()
  .createTypedNode(Context.META.name)
  .setAttribute(Context.NAME.name, Context.NAME.type, "smartGridCtx")
  .setAsVar("Context")
  .updateIndex(Contexts.META.name);
```

**Creation of one structure**:

![](img/context-mm-structure.svg)

Java code:
```java
Tasks.newTask()
  .createTypedNode(Structure.META.name)
  .setAttribute(Structure.NAME.name, Structure.NAME.type, "Entity")
  .setAsVar("Entity")
  .readVar("Context")
  .addVarTo(Context.STRUCTURES.name, "Entity")
```

**Creation of one attribute and one relation**:

![](img/context-mm-property.svg)

Java code:
```java
Tasks.newTask()
  // Creation of one relation
  .createTypedNode(Relation.META.name)
  .setAttribute(Relation.NAME.name, Relation.NAME.type, "registeredEntities")
  .setAttribute(Relation.ISHISTORIC.name, Relation.ISHISTORIC.type, "true")
  .setAttribute(Relation.ISSTATIC.name, Relation.ISSTATIC.type, "false")
  .addVarTo(Relation.TYPEREL.name, "Entity")
  .setAsVar("registeredEntitiesProp")
  .readVar("Entity")
  .addVarTo(Structure.PROPERTIES.name, "registeredEntitiesProp")
  // Creation of one attribute
  .createTypedNode(Attribute.META.name)
  .setAttribute(Attribute.NAME.name, Attribute.NAME.type, "communicationActive")
  .setAttribute(Attribute.ISHISTORIC.name, Attribute.ISHISTORIC.type, "true")
  .setAttribute(Attribute.ISSTATIC.name, Attribute.ISSTATIC.type, "false")
  .setAttribute(Attribute.TYPEATT.name, Attribute.TYPEATT.type, Constants.TYPE_BOOLEAN)
  .setAttribute(Attribute.ISARRAY.name, Attribute.ISARRAY.type, "false")
  .setAsVar("currentProperty")
  .readVar("Entity")
  .addVarTo(Structure.PROPERTIES.name, "currentProperty");
```

**Creation of a source**:

![](img/context-mm-source.svg)

```java
Tasks.newTask()
  .createTypedNode(Sensor.META.name)
  .addVarTo(Sensor.STRUCTINFO.name, "Entity")
  .setAsVar("currentSrc")
  .readVar("registeredEntitiesProp")
  .addVarTo(Property.SOURCE.name, "currentSrc");
```

**Creation of an uncertainty**:

![](img/context-mm-uc.svg)

```java
Tasks.newTask()
  .createTypedNode(Uncertainty.META.name)
  .setAttribute(Uncertainty.VALUE.name, Uncertainty.VALUE.type, "0.9")
  .setAsVar("currentUC")
  .readVar("registeredEntitiesProp")
  .addVarTo(Relation.UNCERTAINTY.name, CURRENT_UC);
```

### Actions

Action meta-model provides a high vision of the different actions possible on the system.
Actions can be either automatic, from simple API calls to complex scripts, or manual, like the intervention of a technician at a customer place to install a new meter.
The action meta-model should not describe in details the different actions.
It can be see as a catalogue of actions.
Some part of this meta-model can be automatically created by analysing the code of the different actions.

Here we describe an object model that instantiated our [action meta-model](../../README.md#graphical-version) and the Java code.
The full version of the object model can be found here (**TODO**) and the Java program [here](src/main/java/snt/das/model/example/smartgrid/action/ActionGen.java).

The different actions that we have in our example are:

- reducing the amps of one (or a set of) customer
- cutting off resource of one (or a set of) customer places
- modifying/adding/removing a repeater for a smart meter (local action)
- adding/removing cables
- modifying cable
- fixing cable

**Creation of strategies:**

![](img/strategy-mm-strategy.svg)

```java
Tasks.newTask()
  .createTypedNode(Strategy.META.name)
  .setAttribute(Strategy.NAME.name, Strategy.NAME.type, "managingCustomer")
  .updateIndex(Strategies.META.name)
  .setAsVar("strategy");
```

**Creation of tactics:**

![](img/strategy-mm-tactic.svg)

```java
Tasks.newTask()
  .createTypedNode(Tactic.META.name)
  .setAttribute(Tactic.NAME.name, Tactic.NAME.type, "reducingAmps")
  .setAsVar("tactic")
  .readVar("strategy")
  .addVarTo(Strategy.TACTICS.name, "tactic")
```

**Creation of conditions:**

![](img/strategy-mm-condition.svg)

```java
Tasks.newTask()
  .createTypedNode(Condition.META.name)
  .setAsVar("condition")
  .readVar("tactic")
  .addVarTo(Tactic.CONDITION.name, "condition")
  .readIndex(Contexts.META.name)
  .traverse(Context.STRUCTURES.name)
  .setAsVar("allStructures")
  .select((Node node, TaskContext ctx) -> ((Structure)node).getName().equals("Customer"))
  .setAsVar("input")
  .readVar("condition")
  .addVarTo(Condition.INPUT.name, "input")
  .readVar("allStructures")
  .select((Node node, TaskContext ctx) -> ((Structure)node).getName().equals("Meter"))
  .setAsVar("input")
  .readVar("condition")
  .addVarTo(Condition.INPUT.name, "input");
```

**Creation of actions:**

![](img/strategy-mm-actions0.svg)

```java
Tasks.newTask()
  .createTypedNode(Action.META.name)
  .setAttribute(Action.NAME.name, Action.NAME.type, "askReduce")
  .setAsVar("action")
  .readVar("tactic")
  .addVarTo(Tactic.ACTIONS.name,"action")
  .createTypedNode(Action.META.name)
  .setAttribute(Action.NAME.name, Action.NAME.type, "checkNewLimit")
  .setAsVar("action")
  .readVar("tactic")
  .addVarTo(Tactic.ACTIONS.name,"action")
  .createTypedNode(Action.META.name)
  .setAttribute(Action.NAME.name, Action.NAME.type, "notifyUser")
  .setAsVar("action")
  .readVar("tactic")
  .addVarTo(Tactic.ACTIONS.name,"action")
  .readVar("allStructures")
  .select((Node node, TaskContext ctx) -> ((Structure)node).getName().equals("Consumption"))
  .setAsVar("impact")
  .readVar("action")
  .addVarTo(Action.IMPACTED.name,"impact")
  .readVar("allStructures")
  .select((Node node, TaskContext ctx) -> ((Structure)node).getName().equals("Meter"))
  .setAsVar("impact")
  .readVar("action")
  .addVarTo(Action.IMPACTED.name,"impact");
```

### Requirements

Requirement meta-model allow to abstract the different goals of the system.

**Creation of natures:**

![](img/requirement-mm-nature.svg)

```java
Tasks.newTask()
  .createTypedNode(Functional.META.name)
  .setAttribute(Functional.NAME.name, Functional.NAME.type, "functional")
  .defineAsGlobalVar("functional")
  .updateIndex(Natures.META.name);
```

**Creation of a requirement:**

![](img/requirement-mm-requirement.svg)

```java
Tasks.newTask()
  .createTypedNode(Requirement.META.name)
  .setAttribute(Requirement.NAME.name, Requirement.NAME.type, "balance")
  .updateIndex(Requirements.META.name)
  .setAsVar("requirement");
```

**Creation of goals:**

![](img/requirement-mm-goals.svg)

```java
Tasks.newTask()
  .createTypedNode(Goal.META.name)
  .setAttribute(Goal.GOAL.name, Goal.GOAL.type, "The system shall not have a cable in overload")
  .setAsVar("goal")
  .addVarTo(Goal.NATURE.name, "quality")
  .readVar("requirement")
  .addVarTo(Requirement.GOALS.name, "goal")

  .createTypedNode(Goal.META.name)
  .setAttribute(Goal.GOAL.name, Goal.GOAL.type, "The system shall provide enough resources for the current consumption")
  .setAsVar("goal")
  .addVarTo(Goal.NATURE.name, "performance")
  .readVar("requirement")
  .addVarTo(Requirement.GOALS.name, "goal")

  .createTypedNode(Goal.META.name)
  .setAttribute(Goal.GOAL.name, Goal.GOAL.type, "The system shall not loss resources")
  .setAsVar("goal")
  .addVarTo(Goal.NATURE.name, "performance")
  .readVar("requirement")
  .addVarTo(Requirement.GOALS.name, "goal");
```


### Knowledge

Knowledge meta-model allow to link the different elements implied in adaptation processes.
At design time, it can be seen as the root element of the model.
Its usage will be more important at runtime.

Here we describe an object model that instantiated our [action meta-model](../../README.md#graphical-version) and the Java code.
The full version of the object model can be found here (**TODO**) and the Java program [here](src/main/java/snt/das/model/example/smartgrid/action/ActionGen.java).

![](img/knowledge-mm-all.svg)

```java
Tasks.newTask()
  .createTypedNode(Knowledge.META.name)
  .setAttribute(Knowledge.NAME.name, Knowledge.NAME.type, "SmartGridKnowledge")
  .updateIndex(Knowledges.META.name)
  .setAsVar("Knowledge")
  .readIndex(Contexts.META.name)
  .setAsVar("Contexts")
  .readIndex(Strategies.META.name)
  .setAsVar("Strategies")
  .readIndex(Requirements.META.name)
  .setAsVar("Requirements")
  .readVar("Knowledge")
  .addVarTo(Knowledge.CONTEXT.name, "Contexts")
  .addVarTo(Knowledge.STRATEGIES.name, "Strategies")
  .addVarTo(Knowledge.REQUIREMENTS.name, "Requirements");
```


## Step 2: Creating procedure to update the model with logging values at runtime

The second step consist in adding run time values about context modifications, action executions and decisions.

### Context

Here, we wll show how to set consumption data in the context model:

![](img/context-om-values.svg)

### Actions

Let's imagine that the adaptation process decided to reduce the amps of two uses.
It will thus execute the `reducingAmps` for the two customers, in parallel.
The action sequence will look like:

![](img/strategy-execution.svg)

In our solution, this will be represented as depicted in the following figure:

![](img/strategy-om-execution.svg)

### Knowledge

In this section, we show you how to model a decision that has been taken by the adaptation process.

![](img/knowledge-om-decision)

## Step 3: Query the model to diagnose a self adaptive system at runtime

Based on the model presented above, an engineer will query it in order to diagnose the root cause of a suboptimal configuration.

Here a implementation of the first query: **What goal(s) the system was trying to reach by executing a tactic?** (Complete version [here](src/main/java/snt/das/model/example/smartgrid/diagnosisAlgo/GetGoals))

```java
Tasks.newTask()
  .inject(tactics)
  .addToVar("tactics")
  .inject(times)
  .addToVar("times")
  .readVar("tactics")
  .forEach(Tasks.newTask()
    .thenDo((TaskContext ctx) -> {
      TaskResult times = ctx.variable(TIMES);
      int idx = ctx.intVar("i");
      long time = (long) times.get(idx - 1);

      ctx.setVariable(TARGET_TIME, time);
    
      ctx.continueTask();
    })
    .travelInTime("{{" + TIMED_TACTICS + "}}")
  )
  .readVar("timedTactics")
  .traverse(Tactic.CONDITION.name)
  .traverse(Condition.IMPLEMENT.name)
  .addToVar("goals");
```


## TODO

- [ ] GreyCat Inspector with the values
- [ ] Web view with object model


## References

[1] Hartmann, T., Fouquet, F., Klein, J., Nain, G., & Le Traon, Y. (2014, February). Reactive security for smart grids using models@ run. time-based simulation and reasoning. In International Workshop on Smart Grid Security (pp. 139-153). Springer, Cham.

[2] Hartmann, T., Fouquet, F., Klein, J., Le Traon, Y., Pelov, A., Toutain, L., & Ropitault, T. (2014, November). Generating realistic smart grid communication topologies based on real-data. In Smart Grid Communications (SmartGridComm), 2014 IEEE International Conference on (pp. 428-433). IEEE.
