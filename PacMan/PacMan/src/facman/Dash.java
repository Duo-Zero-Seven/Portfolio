/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facman;

/**
 *
 * @author Timothy
 */
public class Dash extends Entity 
{
    private int dashX = 0;
    private int dashY = 0;
    
    public static final String ANSI_RESET = "\u001B[";
    public static final String ANSI_BLUE = "\u001B[34m";
    
    public Dash(int dashX, int dashY)
    {
        super("Dash");
        setDashX(dashX);
        setDashY(dashY);
    }
    
    public void setDashX(int dX)
    {
        this.dashX = dX;
    }
    
    public void setDashY(int dY)
    {
        this.dashY = dY;
    }
    
    public int getDashX()
    {
        return this.dashX;
    }
    
    public int getDashY()
    {
        return this.dashY;
    }
    
    public String toString()
    {
        return ANSI_BLUE + "[ ]";
    }
}
