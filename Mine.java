import greenfoot.*;
/**
 * A mine
 * 
 * @author Nate Beasley
 * @version 1.0.0
 */
public class Mine extends greenfoot.Actor
{
    public void activate()
    {
        String q=Greenfoot.ask("Oh no! You died. Answer this question for another chance.");
        
        ((Minesweep)getWorld()).end();
    }
}
