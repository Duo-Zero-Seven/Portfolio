/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facman;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author T
 */
public class LevelImporter 
{
    static Entity [][] levelAssets = new Entity [22][19];
    public static final String ANSI_RESET = "\u001B[";
    
    public LevelImporter() throws IOException
    {
        levelImporter();
        printLevel();
    }
    
    static public Entity[][] levelImporter() throws IOException
    {
        String fileName = "PacMan/src/facman/Pac-Man-Alternate-Template-Comma.txt";
        
        
        try
        { 
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            System.out.println("Generating Level: ");
            
            for(int j = 0; j < 22; j++)
            {
                String tokens[] = br.readLine().split(",");
                for(int k = 0; k < tokens.length; k++)
                {
                    String asset = tokens[k];

                    if(levelAssets[j][k] == null)
                    {
                        levelAssets[j][k] = createLevel(asset, k, j);
                    }
                }
            }
                
            br.close(); 
        }
        catch(IOException e)
        {
            System.err.println("Error reading " + fileName);
        }
        
        return levelAssets;
    }
    
    static private Entity createLevel(String asset, int x, int y)
    {
        Entity levelAsset = null;
        
        if(asset.toLowerCase().contains("#"))
        {
            levelAsset = new Wall(x, y);
        }
        else if(asset.toLowerCase().contains("@"))
        {
            levelAsset = new Dot(x, y);
        }
        else if(asset.toLowerCase().contains("$"))
        {
            levelAsset = new Player(x, y);
        } 
        else if(asset.toLowerCase().contains("-"))
        {
            levelAsset = new Dash(x, y);
        }
        else if(asset.toUpperCase().contains("G"))
        {
            levelAsset = new Ghost(x, y);
        }
  
        return levelAsset;
    }
    
    public void printLevel()
    {   
        int i = 0;
        int j = 0;
        
        for(int y = i; y < 22; y++)
        {
            for(int x = j; x < 19; x++)
            {
                System.out.print(levelAssets[y][x]);
            }
            System.out.println();
        }
        //System.out.print(ANSI_RESET);
    }
    
    public Entity[][] assetTracker()
    {
        return levelAssets;
    }
}
