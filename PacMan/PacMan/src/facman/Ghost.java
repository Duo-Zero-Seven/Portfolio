/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facman;

/**
 *
 * @author T
 */
public class Ghost extends Entity
{
    private int ghostX = 0;
    private int ghostY = 0;
    
    public static final String ANSI_RESET = "\u001B[";
    public static final String ANSI_RED = "\u001B[31m";
    
    public Ghost(int ghostX, int ghostY) 
    {
        super("Ghost");
        setGhostX(ghostX);
        setGhostY(ghostY);
    }
    
    public void setGhostX(int gX)
    {
        this.ghostX = gX;
    }
    
    public void setGhostY(int gY)
    {
        this.ghostY = gY;
    }
    
    public int getGhostX()
    {
        return this.ghostX;
    }
    
    public int getGhostY()
    {
        return this.ghostY;
    }
    
    public String toString() 
    {
        return ANSI_RED + "[G]";
    }
}
