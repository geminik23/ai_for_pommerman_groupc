package groupC.decisionTree;


public abstract class BaseDecision implements DecisionTreeNode {
    DecisionTreeNode trueNode = null;
    DecisionTreeNode fasleNode = null;

    BaseDecision(DecisionTreeNode truenode, DecisionTreeNode falsenode) {
        this.trueNode = truenode;
        this.fasleNode = falsenode;
    }

    protected abstract DecisionTreeNode getBranch(DecisionParams param);

    @Override
    public AgentStrategy makeDecision(DecisionParams param) {
        DecisionTreeNode node = this.getBranch(param);
        if (node != null) return node.makeDecision(param);
        return null;
    }
}
