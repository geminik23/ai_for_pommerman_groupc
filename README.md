# GroupC Agent 

## Initialisation

GroupCPlayer class can be initialized with 2 parameters and 1 optional parameters.

```java
GroupCPlayer p = new GroupCPlayer(seed, playerID++);
````

and with GroupCParam, you can change the MCTS parameters.

```java
GroupCParam param = new GroupCParma();
GroupCPlayer p = new GroupCPlayer(seed, playerID++, param);
```

## Extension Mode

For better performance in partial observability mode, extension mode can be passed. Default mode is NO_EXTENTION and it is not valid in full observability mode.

```java
new GroupCParam(ExtensionMode.PREDICT_BOMB);
```


```java
public enum ExtensionMode {
    NO_EXTENSION,
    DEFAULT_EXTENSION,  // just restoring the received tile information.
    PREDICT_BOMB        // predicting the bomb explosion
}
```
