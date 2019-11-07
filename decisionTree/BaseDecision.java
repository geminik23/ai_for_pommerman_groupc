package groupC.decisionTree;


public abstract class BaseDecision implements DecisionTreeNode {

    DecisionTreeNode trueNode = null;
    DecisionTreeNode falseNode = null;

    BaseDecision(DecisionTreeNode truenode, DecisionTreeNode falsenode) {
        this.trueNode = truenode;
        this.falseNode = falsenode;
    }

    protected abstract DecisionTreeNode getBranch(DecisionParams param);

    @Override
    public ActionStrategy makeDecision(DecisionParams param) {
        DecisionTreeNode node = this.getBranch(param);
        if (node != null) return node.makeDecision(param);
        return null;
    }
}
