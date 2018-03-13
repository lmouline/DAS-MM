Model Example: SmartGrid at Luxembourg
--------------------------------------

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

## Step 1: Describing the adaptation process

### Requirements

### Actions

### Context

In [2], Hartmann et al., describe a meta-model that can be seen as the context meta-model. Here, we will show how we can use our approach to define a similar one.
Here the Hartmann's meta-model:

![](https://raw.githubusercontent.com/thomashartmann/smartgrid-topology-generator/master/lu.snt.smartgrid-topology-generator.model/meta-model.png)

Context meta-model can be seen as a data structure of the collected information about the context (any information relevant for the adaptation process).
Define at design time, it will be instantiated at runtime.
Below, you will find the object model that instantiate our  [context meta-model](../../README.md#graphical-version)

### Knowledge


## References

[1] Hartmann, T., Fouquet, F., Klein, J., Nain, G., & Le Traon, Y. (2014, February). Reactive security for smart grids using models@ run. time-based simulation and reasoning. In International Workshop on Smart Grid Security (pp. 139-153). Springer, Cham.

[2] Hartmann, T., Fouquet, F., Klein, J., Le Traon, Y., Pelov, A., Toutain, L., & Ropitault, T. (2014, November). Generating realistic smart grid communication topologies based on real-data. In Smart Grid Communications (SmartGridComm), 2014 IEEE International Conference on (pp. 428-433). IEEE.
