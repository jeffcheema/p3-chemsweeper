import greenfoot.*;
/**
 * A mine
 * 
 * @author Nate Beasley
 * @version 1.0.0
 */
public class Mine extends greenfoot.Actor
{
    boolean flag = true;

    /**
     * The activate function activates the mine. It asks the user to answer a question to in order to avoid setting off the mine. The user has 3 lives. 
     */
    public void activate()
    {
        if (((Minesweep)getWorld()).lives > 0)
        {
            
           Greenfoot.ask("Oh no! You have hit a mine. Answer a question to keep going, if you answer incorrectly the game will end!") ;   
            

             if (((Minesweep)getWorld()).askQuestion(true)) //use array of questions to look for corresponding answer
             {
                System.out.println("Lives: ");
                ((Minesweep)getWorld()).lives -= 1;
                System.out.println(((Minesweep)getWorld()).lives);
             }
             else{
                Greenfoot.ask("Game is over! (Press enter to continue)"); 
                //answer is incorrect and game ends
                ((Minesweep)getWorld()).end();
            }
        }
        else{
            Greenfoot.ask("Game is over! (Press enter to continue)");  
            ((Minesweep)getWorld()).end();
        }
    }
}
