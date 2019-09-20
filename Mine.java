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
        System.out.println("Game Over!");
        ((Minesweep)getWorld()).end();
    }
}
