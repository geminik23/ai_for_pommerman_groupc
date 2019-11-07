package groupC.decisionTree;

import groupC.GameStateWrapper;

public interface DecisionTreeNode {
    AgentState makeDecision(GameStateWrapper gsw);
}
