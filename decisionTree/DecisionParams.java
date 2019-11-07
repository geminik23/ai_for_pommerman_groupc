package groupC.decisionTree;

import core.GameState;
import utils.Types;
import utils.Vector2d;

import java.util.ArrayList;

public class DecisionParams {
    //** player params
    Vector2d playerPosition;
    int ammo;
    ArrayList<Types.TILETYPE> enemis;

    //** overall params
    boolean teamMode;

    int[][] bombBlastStrength;
    int[][] bombLife;
    Types.TILETYPE[][] board;

    DecisionParams(){}
    DecisionParams(GameState gs){
        this.initWith(gs);
    }

    public void initWith(GameState gs) {
        // get GameState params for Decision Tree
        int nEnemies = gs.getAliveEnemyIDs().size();
        teamMode = gs.getGameMode() != Types.GAME_MODE.FFA;
        enemis = gs.getAliveEnemyIDs();

        int idx = -1;
        for (int i = 0; i < enemis.size(); i++) {
            if (enemis.get(i) == Types.TILETYPE.AGENTDUMMY){
                idx = i;
            }
        }
        if (idx != -1) enemis.remove(idx);

        // player position
        playerPosition = gs.getPosition();
        ammo = gs.getAmmo();

        bombBlastStrength = gs.getBombBlastStrength();
        bombLife = gs.getBombLife();
        board = gs.getBoard();







    }

}
