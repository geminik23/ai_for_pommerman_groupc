package groupC.decisionTree;

import core.GameState;

public class DefensiveDecision extends BaseDecision {

    DefensiveDecision(DecisionTreeNode truenode, DecisionTreeNode falsenode){
        super( truenode, falsenode);
    }


    @Override
    protected DecisionTreeNode getBranch(GameState gs) {
        return null;
    }

}