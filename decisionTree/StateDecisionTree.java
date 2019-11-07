package groupC.decisionTree;

import core.GameState;

public class StateDecisionTree {
    DecisionTreeNode decisionRoot;

    public StateDecisionTree(){
        decisionRoot = new AggressiveDecision(new AggressiveResult(), new DefensiveDecision(new DefensiveResult(), new NeuralResult()));
    }

    public AgentState makeDecision(GameState gs){
        DecisionParams param= new DecisionParams(gs);
        return this.decisionRoot.makeDecision(param);
    }




    // TODO maybe change later.
    /* Results */
    class NeuralResult implements DecisionTreeNode {
        @Override
        public AgentState makeDecision(DecisionParams param) { return AgentState.NEUTRAL; }
    }

    class DefensiveResult implements DecisionTreeNode{
        @Override
        public AgentState makeDecision(DecisionParams param) { return AgentState.DEFENSIVE; }
    }

    class AggressiveResult implements DecisionTreeNode{
        @Override
        public AgentState makeDecision(DecisionParams param) {
            return AgentState.AGGRESIVE;
        }
    }

}
