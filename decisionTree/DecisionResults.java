package groupC.decisionTree;

import core.GameState;

public class DecisionResults {
    public class Aggresive implements DecisionTreeNode{
        @Override
        public AgentState makeDecision(GameState gs) {
            return AgentState.AGGRESIVE;
        }
    }
    public class Defensive implements DecisionTreeNode{
        @Override
        public AgentState makeDecision(GameState gs) {
            return AgentState.DEFENSIVE;
        }
    }
    public class Neutral implements DecisionTreeNode{
        @Override
        public AgentState makeDecision(GameState gs) {
            return AgentState.NEUTRAL;
        }
    }
}
