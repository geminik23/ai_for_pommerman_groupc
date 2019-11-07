package groupC;

import core.GameState;
import groupC.decisionTree.DecisionTreeNode;
import players.Player;
import players.mcts.MCTSParams;
import players.optimisers.ParameterizedPlayer;
import utils.Types;

import java.util.Random;

public class GroupCPlayer extends ParameterizedPlayer {
    /* Random generator.*/
    private Random m_rnd;

    /* All actions available.*/
    public Types.ACTIONS[] actions;

     /* Params for this MCTS */
    public MCTSParams params;

    /* decisionTree */
    DecisionTreeNode decisionTree;

    GroupCPlayer(long seed, int id, MCTSParams param){
        super(seed, id, param);
    }


    @Override
    public void reset(long seed, int playerID) {
        super.reset(seed, playerID);
    }

    @Override
    public Types.ACTIONS act(GameState gs) {
        // 1. get Agent State
        
        // 2. update the parameter or send with states

        return Types.ACTIONS.ACTION_STOP;
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

