package groupC;

import core.GameState;
import players.Player;
import utils.Types;


public class GroupCPlayer extends Player {
    GroupCPlayer(long seed, int id){
        super(seed, id);
    }

    @Override
    public void reset(long seed, int playerID) {
        super.reset(seed, playerID);
    }

    @Override
    public Types.ACTIONS act(GameState gs) {
        return Types.ACTIONS.ACTION_STOP;
    }

    @Override
    public int[] getMessage() {
        // default message
        return new int[Types.MESSAGE_LENGTH];
    }

    @Override
    public Player copy() {
        return new GroupCPlayer(seed, playerID);
    }

}

