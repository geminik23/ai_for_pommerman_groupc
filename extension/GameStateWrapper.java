package groupC.extension;
import core.GameState;
import utils.Types;
import utils.Vector2d;
import java.util.ArrayList;

/*
 * [x] only extend map
 * [x] check observability parameters -> BoardExtensionPolicy
 * [x] consider the bomb and flame (?)
 * [x] add bomb life
 * [x] for test, change the code -> 4 players (RULE / OSLA / MCTS / RHEA)
 * [x] bomb
 */
public class GameStateWrapper {
    GameState internal;
    ExtensionMode policy;
    boolean updated = false;

    Types.TILETYPE[][] extendedBoard = null;
    int bombLife[][] = null;
    int bombBlastStrength[][] = null;

    public GameStateWrapper(){
        this(ExtensionMode.DEFAULT_EXTENSION);
    }

    public GameStateWrapper(ExtensionMode policy){
        this.policy = policy;
        if (Types.DEFAULT_VISION_RANGE == -1){ this.policy = ExtensionMode.NO_EXTENSION; }
    }

    boolean noExtension(){return this.policy == ExtensionMode.NO_EXTENSION;}

    public void setGameState(GameState gs){
        this.internal = gs;
        if ( this.noExtension())return;
        if (this.extendedBoard == null){
            int l = gs.getBoard().length;
            extendedBoard = new Types.TILETYPE[l][];
            bombLife = new int[l][];
            bombBlastStrength = new int[l][];
            for (int i=0;i<l;i++) {
                extendedBoard[i] = new Types.TILETYPE[l];
                bombLife[i] = new int[l];
                bombBlastStrength[i] = new int[l];
                for (int j = 0; j < l; j++) {
                    extendedBoard[i][j] = Types.TILETYPE.FOG;
                    bombLife[i][j] = 0;
                    bombBlastStrength[i][j] = 0;
                }
            }
        }
    }

    void update() {
        if ( !updated) updated = true;
        if ( this.noExtension())return;
        Types.TILETYPE[][] oboard = internal.getBoard();
        int[][] bomblife = this.internal.getBombLife();
        int[][] bombblaststrength = this.internal.getBombBlastStrength();
        boolean bombDied=false, bombUpdate = false;

        // copy tiles
        for(int i=0; i<oboard.length; i++) {
            for (int j = 0; j < oboard[i].length; j++) {
                bombDied=false;
                bombUpdate = oboard[i][j] == Types.TILETYPE.BOMB;
                switch (extendedBoard[i][j] ) {
                    case RIGID: break; // ignore
                    /* weapons */
                    case BOMB:
                        if (this.policy == ExtensionMode.PREDICT_BOMB){
                            switch( oboard[i][j]){
                                case FOG: // increase member variable
                                    if (--this.bombLife[i][j] == 0) {
                                        this.bombBlastStrength[i][j] = 0;
                                        bombDied = true;
                                    }
                                    break;
//                                case BOMB: bombUpdated = true;break;
                                case PASSAGE:
                                default:
                                    this.bombLife[i][j] = 0;
                                    this.bombBlastStrength[i][j] = 0;
                                    break;
                            }
                        }
                    default: // other all TILES - FLAMES:PASSAGE:WOOD:FOG:EXTRABOMB:INCRRANGE:KICK:AGENTDUMMY:
                        if (bombUpdate){
                            this.bombLife[i][j] = bomblife[i][j];
                            this.bombBlastStrength[i][j] = bombblaststrength[i][j];
                        }
                        if (oboard[i][j] != Types.TILETYPE.FOG) this.extendedBoard[i][j] = oboard[i][j];
                        if(bombDied) this.extendedBoard[i][j] = Types.TILETYPE.PASSAGE;
                        break;
                }
            }
        }
    }

    GameStateWrapper copy(GameState gs){
        GameStateWrapper gsw = new GameStateWrapper();
        gsw.setGameState(gs.copy()); // copy GameState Object

        if (this.noExtension()) return gsw;
        // deep copy
        for(int i = 0; i < gsw.extendedBoard.length; i++)
        {
            Types.TILETYPE[] lines = this.extendedBoard[i];
            int[] blife = this.bombLife[i];
            int  length = lines.length;
            System.arraycopy(lines, 0, gsw.extendedBoard[i], 0, length);
            System.arraycopy(blife, 0, gsw.bombLife[i], 0, length);
        }
        return gsw;
    }


    public boolean next(Types.ACTIONS[] actions){
        this.updated = false;
        boolean r = internal.next(actions);
        this.update();
        return r;
    }

    public GameState getInternal(){return this.internal;}

    public GameStateWrapper copy() {
        return this.copy(internal.copy());
    }

    public Types.TILETYPE[][] getBoard(){
        if (this.noExtension()) return internal.getBoard();
        this.update();
        return extendedBoard;
    }

    public int[][] getBombBlastStrength(){return internal.getBombBlastStrength();}

    public int[][] getBombLife() {
        if (this.policy == ExtensionMode.PREDICT_BOMB){
            return this.bombLife;
        }
        return internal.getBombLife();
    }

    public int getTeam(){return internal.getTeam();}

    public Types.TILETYPE[] getTeammates(){return internal.getTeammates();}

    public Types.TILETYPE[] getEnemies(){return internal.getEnemies();}

    public int nActions(){return internal.nActions();}

    public Types.RESULT winner(){return internal.winner();}

    public int getBlastStrength(){return internal.getBlastStrength();}

    public int getPlayerId(){return internal.getPlayerId();}

    public int getAmmo(){return internal.getAmmo();}

    public boolean canKick(){return internal.canKick();}

    public Vector2d getPosition(){return internal.getPosition();}

    public Types.GAME_MODE getGameMode(){return internal.getGameMode();}

    public Types.TILETYPE[] getAliveAgentIDs(){return internal.getAliveAgentIDs();}

    public ArrayList<Types.TILETYPE> getAliveTeammateIDs(){return internal.getAliveTeammateIDs();}

    public ArrayList<Types.TILETYPE> getAliveEnemyIDs(){return internal.getAliveEnemyIDs();}

    public boolean isTerminal(){return internal.isTerminal();}

    public int getTick(){return internal.getTick();}

    public void addBomb(int x, int y, int blastStrength, int bombLife, int playerIdx, boolean addToBoard){ internal.addBomb(x, y, blastStrength, bombLife, playerIdx, addToBoard);}

    public void addFlame(int x, int y, int life){internal.addFlame(x, y, life);}

    public void addPowerUp(int x, int y, Types.TILETYPE type, boolean visible){internal.addPowerUp(x, y, type, visible);}

    public void addObject(int x, int y, Types.TILETYPE type){internal.addObject(x, y, type);}

    public void removeObject(int x, int y, Types.TILETYPE type, boolean onlyBoard){internal.removeObject(x, y, type, onlyBoard);}

    public void removePowerUp(int x, int y, Types.TILETYPE type){internal.removePowerUp(x, y, type);}

    public void addAgent(int x, int y, int idx){internal.addAgent(x, y, idx);}

    public void setAgent(int playerIdx, int x, int y, boolean canKick, int ammo, int blastStrength){internal.setAgent(playerIdx, x, y, canKick, ammo, blastStrength);}

    public void setBomb(int x, int y, int playerIdx, Vector2d velocity){internal.setBomb(x, y, playerIdx, velocity);}

    public void setFlame(int x, int y, int life){ internal.setFlame(x, y, life);}

    public int[] getMessage(){return internal.getMessage();}

    public int[] getMessage(int playerIdx){return internal.getMessage(playerIdx);}

    @Override
    public String toString(){return internal.toString();}

    @Override
    public boolean equals(Object o){return internal.equals(o);}

    public String toJson(){ return internal.toJson();}
}
