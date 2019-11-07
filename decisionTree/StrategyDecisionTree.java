package groupC.decisionTree;

import core.GameState;

public class StrategyDecisionTree {
    DecisionTreeNode decisionRoot;

    public StrategyDecisionTree(){
        decisionRoot = new AggressiveDecision(new AggressiveResult(), new DefensiveDecision(new DefensiveResult(), new NeuralResult()));
    }

    public ActionStrategy makeDecision(GameState gs){
        DecisionParams param= new DecisionParams(gs);
        return this.decisionRoot.makeDecision(param);
    }


    /* Results */
    class NeuralResult implements DecisionTreeNode {
        @Override
        public ActionStrategy makeDecision(DecisionParams param) { return ActionStrategy.NEUTRAL; }
    }

    class DefensiveResult implements DecisionTreeNode{
        @Override
        public ActionStrategy makeDecision(DecisionParams param) { return ActionStrategy.DEFENSIVE; }
    }

    class AggressiveResult implements DecisionTreeNode{
        @Override
        public ActionStrategy makeDecision(DecisionParams param) {
            return ActionStrategy.AGGRESIVE;
        }
    }

}
