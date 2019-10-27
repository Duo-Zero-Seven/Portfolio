/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facman;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author T
 */

public class Game
{
    LevelImporter Li;
    Player p1;
    Ghost g1;
    int playerX = 9;
    int playerY = 12;
    int ghostX = 9;
    int ghostY = 10;
    int prevPlayerX = 0;
    int prevPlayerY = 0;
    int objectX = 0;
    int objectY = 0;
    int playerScore = 0;
    Entity [][] levelAssets;
    
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    public void gameLoop() throws IOException
    {
        String input = "";
        Scanner sc = new Scanner(System.in);
        Li = new LevelImporter();
        gameLoop:
        while(sc.hasNext())
        {
            playerMovement();
            input = sc.nextLine();
            
            switch(input.toUpperCase()) 
            {
                case "A":
                    prevPlayerX = playerX;
                    prevPlayerY = playerY;
                    objectX = playerX;
                    objectY = playerY;
                    if(!(objectX-- == 0) && !(levelAssets[objectY][objectX].getName().equals("wall")))
                    {
                        if(levelAssets[objectY][objectX].getName().equals("Dot"))
                        {
                            playerScore++;
                        }
                        
                        p1.setPlayerX((playerX--));
                        levelAssets[prevPlayerY][prevPlayerX] = new Dash(prevPlayerX, prevPlayerY);
                        levelAssets[playerY][playerX] = new Player(playerX, playerY);
                        Li.printLevel();
                    }
                    else
                    {
                        System.out.println(ANSI_RED + "Illegal Move, Level Bounds Reached!" + ANSI_RESET);
                    }
                    break;

                case "D":
                    prevPlayerX = playerX;
                    prevPlayerY = playerY;
                    objectX = playerX;
                    objectY = playerY;
                    if(!(objectX++ == 18) && !(levelAssets[objectY][objectX].getName().equals("wall")))
                    {
                        if(levelAssets[objectY][objectX].getName().equals("Dot"))
                        {
                            playerScore++;
                        }
                        
                        p1.setPlayerX((playerX++));
                        levelAssets[prevPlayerY][prevPlayerX] = new Dash(prevPlayerX, prevPlayerY);
                        levelAssets[playerY][playerX] = new Player(playerX, playerY);
                        Li.printLevel();
                    }
                    else
                    {
                        System.out.println(ANSI_RED + "Illegal Move, Level Bounds Reached!" + ANSI_RESET);
                    }
                    break;

                case "W":
                    prevPlayerX = playerX;
                    prevPlayerY = playerY;
                    objectX = playerX;
                    objectY = playerY;
                    if(!(objectY-- == 0) && !(levelAssets[objectY][objectX].getName().equals("wall")))
                    {
                        if(levelAssets[objectY][objectX].getName().equals("Dot"))
                        {
                            playerScore++;
                        }
                        
                        p1.setPlayerY((playerY--));
                        levelAssets[prevPlayerY][prevPlayerX] = new Dash(prevPlayerX, prevPlayerY);
                        levelAssets[playerY][playerX] = new Player(playerX, playerY);
                        Li.printLevel();
                    }
                    else
                    {
                        System.out.println(ANSI_RED + "Illegal Move, Level Bounds Reached!" + ANSI_RESET);
                    }
                    break;

                case "S":
                    prevPlayerX = playerX;
                    prevPlayerY = playerY;
                    objectX = playerX;
                    objectY = playerY;
                    if(!(objectY++ == 21) && !(levelAssets[objectY][objectX].getName().equals("wall")))
                    {
                        if(levelAssets[objectY][objectX].getName().equals("Dot"))
                        {
                            playerScore++;
                        }
                        
                        p1.setPlayerY((playerY++));
                        levelAssets[prevPlayerY][prevPlayerX] = new Dash(prevPlayerX, prevPlayerY);
                        levelAssets[playerY][playerX] = new Player(playerX, playerY);
                        Li.printLevel();
                    }
                    else
                    {
                        System.out.println(ANSI_RED + "Illegal Move, Level Bounds Reached!" + ANSI_RESET);
                    }
                    break;
            }
            
            String scoreKeeper = "";
         
            if(playerScore < 10)
            {
                scoreKeeper += ANSI_YELLOW + "[0][0][" + playerScore +"]" + ANSI_RESET;
            }
            else if(playerScore > 10 && playerScore < 100)
            {
                scoreKeeper += ANSI_YELLOW + "[0][" + (playerScore / 10) + "][" + (playerScore - ((playerScore / 10) * 10)) + "]" + ANSI_RESET;
            }
            else if(playerScore > 100 && playerScore < 181)
            {
                scoreKeeper += ANSI_YELLOW + "[" + (playerScore / 100) + "][" + ((playerScore - ((playerScore / 100) * 100)) / 10) + "][" + (playerScore - ((playerScore / 10) * 10)) + "]"
                            + ANSI_RESET;
            }
            else if(playerScore == 182)
            {
                playerScore += 100;
                System.out.println(ANSI_GREEN + "[ ][L][E][V][E][L][ ][ ][C][O][M][P][L][E][T][E][D][!][ ]" + ANSI_RESET);
                scoreKeeper += ANSI_YELLOW + "[" + (playerScore / 100) + "][" + ((playerScore - ((playerScore / 100) * 100)) / 10) + "][" + (playerScore - ((playerScore / 10) * 10)) + "]"
                            + ANSI_RESET;
                break gameLoop;
            }
            
            System.out.println("[ ][P][L][A][Y][E][R][ ][S][C][O][R][E][:][ ]" + scoreKeeper + "[ ]");
        }
    }
    
    public void playerMovement()
    {
        levelAssets = Li.assetTracker();
        p1 = (Player) levelAssets[playerY][playerX];
        g1 = (Ghost) levelAssets[ghostY][ghostX];
        System.out.println("Please select your movement direction: ((A)LEFT (S)RIGHT (W)UP (D)DOWN)");
    }
}
