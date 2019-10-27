
package facman;
import java.util.Scanner;
import java.io.IOException;
/**
 * 
 * @author Dave Kinsman
 * 1276664
 */
public class FacMan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        // TODO code application logic here
        String input = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("************************************************ \n"
                         + "             PACMAN              \n" + 
                           "************************************************\n\n");
        
        while(!input.equals("1") && !input.equals("2") && !input.equals("3")) 
        {
            System.out.println("what would you like to do? (1/2/3) \n1: New Game \n2: View instructions \n3: Quit");
            System.out.print(">");
            input = sc.next();
            
            if(!input.equals("1") && !input.equals("2") && !input.equals("3")) 
            {
                System.out.println("Please enter a valid input (1/2/3) \n");
            }
        }
        
        switch(input) 
        {
            case "1":
                System.out.println("\n\n");
                Game pacman = new Game();
                pacman.gameLoop();

                break;
                
            case "2":
                System.out.println("Instructions:\n\nThe game Pacman is fun and simple. The aim of the game is to collect all  \n"
                        + "of the dots/balls in the level/map. Seems easy right? It will be if you can elude the ghosts\n"
                        + "that are tryiong to hunt you down and eat your circular soul. \n\n"
                        + "The controls consist of W,A,S,D and Enter \n"
                        + "A for left \nW for up \nS for down \nD for right \nThe game will wait for you to press a valid \n"
                        + "direction input and for you to press enter and it will then make your move for you.\n"
                        + "If you hit into a ghost you lose the game and will have to start over\n"
                        + "Have Fun!");
                break;
                
            case "3": 
                System.out.println("Thanks for Playing!");
                System.exit(1);
        }
        
    }
    
}
