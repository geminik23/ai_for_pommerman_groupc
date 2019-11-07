package groupC;

import core.GameState;
import groupC.decisionTree.ActionStrategy;
import groupC.decisionTree.StrategyDecisionTree;
import groupC.extension.GameStateWrapper;
import players.Player;
import players.mcts.MCTSParams;
import players.optimisers.ParameterizedPlayer;
import utils.ElapsedCpuTimer;
import utils.Types;

import java.util.ArrayList;
import java.util.Random;

public class GroupCPlayer extends ParameterizedPlayer {
    /* Random generator.*/
    private Random m_rnd;

    /* All actions available.*/
    public Types.ACTIONS[] actions;

     /* Params for this MCTS */
    public MCTSParams params;

    /* decisionTree */
    StrategyDecisionTree stateDecision;

    /* GameState Wrapper */
    GameStateWrapper gsw;

    GroupCPlayer(long seed, int id, MCTSParams param){
        super(seed, id, param);
        stateDecision = new StrategyDecisionTree();
        gsw = new GameStateWrapper();
    }

    @Override
    public void reset(long seed, int playerID) {
        super.reset(seed, playerID);

        ArrayList<Types.ACTIONS> actionsList = Types.ACTIONS.all();
        actions = new Types.ACTIONS[actionsList.size()];
        int i = 0;
        for (Types.ACTIONS act : actionsList) {
            actions[i++] = act;
        }
    }

    @Override
    public Types.ACTIONS act(GameState gs) {
        // Wrapping GameState
        gsw.setGameState(gs);

        // get Agent State
        ActionStrategy strategy = this.stateDecision.makeDecision(gs);


        ElapsedCpuTimer ect = new ElapsedCpuTimer();
        ect.setMaxTimeMillis(params.num_time);

        // Number of actions available
        int num_actions = actions.length;

        // Root of the tree
        MCTSNode m_root = new MCTSNode(params, m_rnd, num_actions, actions, strategy);
        m_root.setRootGameState(gsw);

        //Determine the action using MCTS...
        m_root.mctsSearch(ect);

        //Determine the best action to take and return it.
        int action = m_root.mostVisitedAction();

        return this.actions[action];
    }

    @Override
    public int[] getMessage() {
        // default message
        return new int[Types.MESSAGE_LENGTH];
    }

    @Override
    public Player copy() {
        return new GroupCPlayer(seed, playerID, params);
    }

}

