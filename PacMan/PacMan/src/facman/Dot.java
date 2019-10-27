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
public class Dot extends Entity 
{
    private int dotX = 0;
    private int dotY = 0;
    
    public static final String ANSI_RESET = "\u001B[";
    public static final String ANSI_GREEN = "\u001B[32m";
    
    public Dot(int dotX, int dotY)
    {
        super("Dot");
        setDotX(dotX);
        setDotY(dotY);
    }
    
    public void setDotX(int dX)
    {
        this.dotX = dX;
    }
    
    public void setDotY(int dY)
    {
        this.dotY = dY;
    }
    
    public int getDotX()
    {
        return this.dotX;
    }
    
    public int getDotY()
    {
        return this.dotY;
    }
    
    public String toString()
    {
        return ANSI_GREEN + "[@]";
    }
}
