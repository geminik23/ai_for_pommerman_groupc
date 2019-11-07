package groupC.decisionTree;

import core.GameState;

public class DefensiveDecision extends BaseDecision {

    DefensiveDecision(DecisionTreeNode truenode, DecisionTreeNode falsenode){
        super( truenode, falsenode);
    }


    @Override
    protected DecisionTreeNode getBranch(DecisionParams param) {
        if(param.ammo ==0  && param.min_enemy<DecisionParams.DISTANT_MEASUREMENT
        && (param.min_bomb < DecisionParams.DISTANT_MEASUREMENT || param.min_flame<DecisionParams.DISTANT_MEASUREMENT)){
            return trueNode;
        }
        return falseNode;
    }

}
