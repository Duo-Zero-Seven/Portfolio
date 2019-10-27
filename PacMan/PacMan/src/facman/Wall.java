/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facman;

/**
 *
 * @author Dave
 */
public class Wall extends Entity
{
    private int wallX = 0;
    private int wallY = 0;
    
    public static final String ANSI_RESET = "\u001B[";
    public static final String ANSI_BLUE = "\u001B[34m";
    
    public Wall(int wallX, int wallY) 
    {
        super("wall");
        setWallX(wallX);
        setWallY(wallY);
    }
    
    public void setWallX(int wX)
    {
        this.wallX = wX;
    }
    
    public void setWallY(int wY)
    {
        this.wallY = wY;
    }
    
    public int getWallX()
    {
        return this.wallX;
    }
    
    public int getWallY()
    {
        return this.wallY;
    }
    
    public String toString() 
    {
        return ANSI_BLUE + "[#]";
    }
}
