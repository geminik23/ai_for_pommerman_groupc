package groupC.decisionTree;

import core.GameState;

public class DecisionResults {
    public class Aggresive implements DecisionTreeNode{
        @Override
        public AgentStrategy makeDecision(GameState gs) {
            return AgentStrategy.AGGRESIVE;
        }
    }
    public class Defensive implements DecisionTreeNode{
        @Override
        public AgentStrategy makeDecision(GameState gs) {
            return AgentStrategy.DEFENSIVE;
        }
    }
    public class Neutral implements DecisionTreeNode{
        @Override
        public AgentStrategy makeDecision(GameState gs) {
            return AgentStrategy.NEUTRAL;
        }
    }
}
