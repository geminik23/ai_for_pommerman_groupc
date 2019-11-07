package groupC.decisionTree;

import core.GameState;
import objects.Bomb;
import utils.Types;
import utils.Vector2d;

import java.util.ArrayList;

public class DecisionParams {
    //** player params
    Vector2d playerPosition;
    ArrayList<Vector2d> enemies;
    ArrayList<Bomb> bombs;

    int ammo;
    Types.TILETYPE[][] board;


    /* constructor */
    DecisionParams(){}
    DecisionParams(GameState gs){
        this.initWith(gs);
    }

    public void initWith(GameState gs) {
        // get GameState params for Decision Tree
        ArrayList<Types.TILETYPE> enemis = gs.getAliveEnemyIDs();
        int idx = -1;
        for (int i = 0; i < enemis.size(); i++) if (enemis.get(i) == Types.TILETYPE.AGENTDUMMY) idx = i;
        if (idx != -1) enemis.remove(idx);

        // player position
        playerPosition = gs.getPosition();
        ammo = gs.getAmmo();

        int[][] bombBlastStrength = gs.getBombBlastStrength();
        int[][] bombLife = gs.getBombLife();
        board = gs.getBoard();

        for (int j=0;j<board.length;j++){
            for(int i=0;i<board.length;i++){
                switch(this.board[j][i]){
                    case AGENT0:
                    case AGENT1:
                    case AGENT2:
                    case AGENT3:
                        if (playerPosition.equals(new Vector2d(i, j))) break;
                        if (enemis.contains(this.board[j][i])) this.enemies.add(new Vector2d(i,j));
                        break;
                    case BOMB:
                    case EXTRABOMB:
                        


                }
            }
        }
    }

}
