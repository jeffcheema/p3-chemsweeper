import greenfoot.GreenfootImage;
import greenfoot.Greenfoot;
import java.util.ArrayList;

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
    private static final GreenfootImage question = new GreenfootImage("question.png");

    private GreenfootImage backgroundImage = normal;

    private int setting = 0;

    private boolean removing = false;

    public Hider()
    {
        setImage(normal);
    }

    public void act()
    {
        super.act();
        if (!isHovered() && !backgroundImage.equals(normal))
        {
            backgroundImage = new GreenfootImage(normal);
            drawSetting();
        }
    }

    public void whenDragged(){}

    public void whenHovered()
    {
        backgroundImage = new GreenfootImage(hovered);
        drawSetting();
    }

    public void whenClicked()
    {
        if (Greenfoot.getMouseInfo().getButton() == 3)
        {
            if (setting == 2)
                setting = -1;
            setting++;
            drawSetting();
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
                    return;
                }
                removeSelfAndOthers();
            }
        }
    }

    private void drawSetting()
    {
        setImage(new GreenfootImage(backgroundImage));
        switch(setting)
        {
            case 1: getImage().drawImage(flag,0,0);
            break;
            case 2: getImage().drawImage(question,0,0);
            default:;
        }
    }

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
