LDAS - Distributed Adaptive Systems
-----------------------------------

This repository is my main playground to develop techniques for (self-)adaptive systems.

## Folder structure

|- [model](model): A meta-model use to specify the elements implied in adaptive systems and to structure the logs

## Model

### Graphical version

**TBD**

### Dependencies

- [GreyCat, v10.8](https://github.com/datathings/greycat)

### How to use it

Maven, add the following dependency:

```
<dependency>
  <groupId>snt.das</groupId>
  <artifactId>model</artifactId>
  <version>${VERSION}</version>
</dependency>
```

If you use a released version, add the following repository:
```
<repository>
  <id>inria.artifactory</id>
  <url>http://maven.inria.fr/artifactory/public-snapshot</url>
  <snapshots>
    <enabled>false</enabled>
  </snapshots>
  <releases>
    <enabled>true</enabled>
  </releases>
</repository>
```

Otherwise:
```
<repository>
  <id>inria.artifactory</id>
  <url>http://maven.inria.fr/artifactory/public-release/</url>
  <snapshots>
    <enabled>true</enabled>
  </snapshots>
  <releases>
    <enabled>false</enabled>
  </releases>
</repository>
```

### Available Versions

- 1.0.5-SNAPSHOTS