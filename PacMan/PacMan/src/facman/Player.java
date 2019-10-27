
package facman;

/**
 *
 * @author Dave
 */
public class Player extends Entity
{
    private static int gamesPlayed = 0;
    private int score = 0;
    private int playerX = 0;
    private int playerY = 0;
    
    public static final String ANSI_RESET = "\u001B[";
    public static final String ANSI_YELLOW = "\u001B[33m";
    
    public Player(int playerX, int playerY) 
    {
        super("player");
        setPlayerX(playerX);
        setPlayerY(playerY);
    }
    
    public void setPlayerX(int pX)
    {
        this.playerX = pX;
    }
    
    public void setPlayerY(int pY)
    {
        this.playerY = pY;
    }
    
    public int getPlayerX()
    {
        return this.playerX;
    }
    
    public int getPlayerY()
    {
        return this.playerY;
    }
    
    public int getScore() 
    {
        return score;
    }
    
    public int getPlayTime() 
    {
        return gamesPlayed;
    }
    
    public String currentPlayerLocation()
    {
        return "Current player location X: " + getPlayerX() + " Y: " + getPlayerY();
    }
    
    public String toString() 
    {
        return ANSI_YELLOW + "[F]";
    }
}
