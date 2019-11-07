package groupC.decisionTree;

import core.GameState;
import utils.Types;
import utils.Vector2d;

import java.util.ArrayList;

public class DecisionParams {
    //** player params
    Vector2d playerPosition;



    //** overall params


    DecisionParams(){}
    DecisionParams(GameState gs){
        this.initWith(gs);
    }

    public void initWith(GameState gs) {

        int nEnemies = gs.getAliveEnemyIDs().size();
        // Init weights based on game mode
        if (gs.getGameMode() != Types.GAME_MODE.FFA) {
            int nTeammates = gs.getAliveTeammateIDs().size();  // We only need to know the alive teammates in team modes
            nEnemies -= 1;  // In team modes there's an extra Dummy agent added that we don't need to care about
        }
        ArrayList<Types.TILETYPE> enemis = gs.getAliveEnemyIDs();
        int idx = -1;
        for (int i = 0; i < enemis.size(); i++) {
            if (enemis.get(i) == Types.TILETYPE.AGENTDUMMY){
                idx = i;
            }
        }
        if (idx != -1) enemis.remove(idx);

        // player position
        playerPosition = gs.getPosition();







    }
}
