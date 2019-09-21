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

    
    public void activate()
    {
        if (((Minesweep)getWorld()).lives > 0)
        {
           Greenfoot.ask("Oh no! You have hit a mine. Answer a question to keep going, if you answer incorrectly the game will end!") ;   


             if (((Minesweep)getWorld()).askQuestion(true)) //use array of questions to look for corresponding answer
             {
               
                ((Minesweep)getWorld()).lives--;
             }
             else
             //answer is incorrect and game ends
             ((Minesweep)getWorld()).end();
        }
        else
        Greenfoot.ask("Game is over! (Press enter to continue)");  
        ((Minesweep)getWorld()).end();
    }
}
