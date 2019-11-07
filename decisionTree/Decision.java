package groupC.decisionTree;

import groupC.GameStateWrapper;

public abstract class Decision implements DecisionTreeNode {
    DecisionTreeNode trueNode;
    DecisionTreeNode fasleNode;

    Decision(DecisionTreeNode truenode, DecisionTreeNode falsenode) {
        this.trueNode = truenode;
        this.fasleNode = falsenode;
    }

    protected abstract DecisionTreeNode getBranch(GameStateWrapper gsw);

    @Override
    public AgentState makeDecision(GameStateWrapper gsw) {
        DecisionTreeNode node = this.getBranch(gsw);
        if (node != null) return node.makeDecision(gsw);
        return null;
    }
}
