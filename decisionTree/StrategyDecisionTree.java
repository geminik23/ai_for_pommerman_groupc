package groupC.decisionTree;

import core.GameState;

public class StrategyDecisionTree {
    DecisionTreeNode decisionRoot;

    public StrategyDecisionTree(){
        decisionRoot = new AggressiveDecision(new AggressiveResult(), new DefensiveDecision(new DefensiveResult(), new NeuralResult()));
    }

    public AgentStrategy makeDecision(GameState gs){
        DecisionParams param= new DecisionParams(gs);
        return this.decisionRoot.makeDecision(param);
    }


    /* Results */
    class NeuralResult implements DecisionTreeNode {
        @Override
        public AgentStrategy makeDecision(DecisionParams param) { return AgentStrategy.NEUTRAL; }
    }

    class DefensiveResult implements DecisionTreeNode{
        @Override
        public AgentStrategy makeDecision(DecisionParams param) { return AgentStrategy.DEFENSIVE; }
    }

    class AggressiveResult implements DecisionTreeNode{
        @Override
        public AgentStrategy makeDecision(DecisionParams param) {
            return AgentStrategy.AGGRESIVE;
        }
    }

}
