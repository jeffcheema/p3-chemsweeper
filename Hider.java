import greenfoot.GreenfootImage;
import greenfoot.Greenfoot;
import java.util.*;
import greenfoot.*; 
/**
 * A hider "hides" the object beneath it.
 * 
 * @author Nate Beasley
 * @version 1.0.0
 */
public class Hider extends Button
{
    private static final GreenfootImage normal = new GreenfootImage("Hidden.png");
    private static final GreenfootImage hovered = new GreenfootImage("Background.png");
    private static final GreenfootImage flag = new GreenfootImage("flag.png");

    private GreenfootImage backgroundImage = normal;

    private int setting = 0;

    private boolean removing = false;
    /**
     * sets the image to a normal state 
     */
    public Hider()
    {
        setImage(normal);
    }
    /**
     * Describes behavior for the hover  
     */
    public void act()
    {
        super.act();
        if (!isHovered() && !backgroundImage.equals(normal))
        {
            backgroundImage = new GreenfootImage(normal);
            drawSetting();
        }
    }
    
    public void whenDragged(){
    }
    /**
     * Handles the specific behavior of the hovering effect. 
     */
    public void whenHovered()
    {
        backgroundImage = new GreenfootImage(hovered);
                   

      
                    System.out.println(setting);
                   drawSetting();
                
    }
    /**
     * Handles different input (right click, left click) and changes the tiles accordingly. 
     */
    public void whenClicked()
    {
        if (Greenfoot.getMouseInfo().getButton() == 3)
        {
            
            
            if (setting == 2)
                setting = -1;
            setting++;
           if (((Minesweep)getWorld()).askQuestion(false)) {
           
            drawSetting();
            
        }
        else{
        setting = 0;
    }
        }
                

        else
        {
            if (setting != 1)
            {
                
                Mine mine = (Mine)getOneIntersectingObject(Mine.class);
                if (mine != null)
                {
                    getWorld().removeObject(this);
                   
                    mine.activate();
                    setting = 1; 
                    return;
                    
                }
                setting = 1; 
                removeSelfAndOthers();
            }
            setting = 1; 
        }
    }
    /**
     * Places a flag based on behavior determined in whenClicked()
     */
    void drawAsk(){

    getImage().drawImage(flag,0,0);

    }
    /** 
     * When needed to draw a flag this method checks the state of the mouse input and calls drawAsk accordingly. 
     */
    private void drawSetting()
    {
        setImage(new GreenfootImage(backgroundImage));
   
            if (setting == 1 || setting == 2){
                drawAsk();
                
            }
     
        
        
    }
    
    /**
     * This method removes the current tile and changes the tiles surrounding the click center accordingly to the mechanics of minesweeper.
     */
    public void removeSelfAndOthers()
    {
        ArrayList<Hider> hiders = new ArrayList<Hider>(9);
        if (getOneIntersectingObject(Number.class) == null && !removing)
        {
            removing = true;
            for (int dx = -1; dx <= 1; dx++)
                for (int dy = -1; dy <= 1; dy++)
                {
                    Hider h = (Hider)getOneObjectAtOffset(dx,dy,Hider.class);
                    if (h != null)
                        hiders.add(h);
            }
        }
        getWorld().removeObject(this);
        for (Hider h : hiders)
            try
            {
                h.removeSelfAndOthers();
            }
            catch (IllegalStateException e){}
    }
}
