package groupC.decisionTree;

public abstract class Decision implements DecisionTreeNode {
    DecisionTreeNode trueNode;
    DecisionTreeNode fasleNode;

    Decision(DecisionTreeNode truenode, DecisionTreeNode falsenode) {
        this.trueNode = truenode;
        this.fasleNode = falsenode;
    }

    protected abstract DecisionTreeNode getBranch();

    @Override
    public AgentState makeDecision() {
        DecisionTreeNode node = this.getBranch();
        if (node != null) return node.makeDecision();
        return null;
    }
}
