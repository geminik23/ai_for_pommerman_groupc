package groupC.decisionTree;

import core.GameState;
import objects.Bomb;
import objects.Flame;
import utils.Types;
import utils.Vector2d;

import java.util.ArrayList;



public class DecisionParams {
    public static double DISTANT_MEASUREMENT = 7.01; // diagonal distance of (5,5)

    Vector2d playerPosition;
    ArrayList<Vector2d> enemies = new ArrayList<>();
    ArrayList<Bomb> bombs = new ArrayList<>();
    ArrayList<Flame> flames = new ArrayList<>();

    int ammo;
    Types.TILETYPE[][] board; // + board size

    double min_enemy = Double.POSITIVE_INFINITY;
    double min_bomb = Double.POSITIVE_INFINITY;
    double min_flame = Double.POSITIVE_INFINITY;

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

        // get Items and Enemies
        for (int j=0;j<board.length;j++){
            for(int i=0;i<board.length;i++){
                switch(this.board[j][i]){
                    case AGENT0:
                    case AGENT1:
                    case AGENT2:
                    case AGENT3:
                        if (playerPosition.equals(new Vector2d(i, j))) break;
                        if (enemis.contains(this.board[j][i])) this.enemies.add(new Vector2d(i,j));
                        min_enemy = Math.min(min_bomb, playerPosition.dist(i,j));
                        break;
                    case BOMB:
                    case EXTRABOMB:
                        Bomb b = new Bomb();
                        b.setPosition(new Vector2d(i, j));
                        b.setBlastStrength(bombBlastStrength[j][i]);
                        b.setLife(bombLife[j][i]);
                        this.bombs.add(b);

                        min_bomb = Math.min(min_bomb, b.getPosition().dist(playerPosition));
                        break;
                    case FLAMES:
                        Flame f = new Flame();
                        f.setPosition(new Vector2d(i,j));
                        this.flames.add(f);
                        min_flame= Math.min(min_flame, f.getPosition().dist(playerPosition));
                        break;
                    default:
                        break;
                }
            }
        }
    }

}
