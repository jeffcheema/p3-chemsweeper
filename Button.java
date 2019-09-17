import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

/**
 * An abstract actor for all buttons
 * 
 * @author Nate Beasley
 * @version 1.0.5
 */
public abstract class Button extends greenfoot.Actor implements ButtonInterface
{
    private MouseActor mouseLoc;

    public static final GreenfootImage blank = new GreenfootImage(1,1);

    public void act()
    {
        if (mouseLoc == null && Greenfoot.getMouseInfo() != null)
        {
            mouseLoc = new MouseActor();
            getWorld().addObject(mouseLoc,0,0);
            mouseLoc.act();
        }
        if (isClicked())
            whenClicked();
        if (isDragged())
            whenDragged();
        if (isHovered())
            whenHovered();
    }

    public boolean isClicked()
    {
        return Greenfoot.mouseClicked(this);
    }

    public boolean isDragged()
    {
        return Greenfoot.mouseDragged(this);
    }

    public boolean isHovered()
    {
        try
        {
            return Greenfoot.getMouseInfo() != null && getOneIntersectingObject(MouseActor.class) != null;
        }
        catch (IllegalStateException e)
        {
            return false;
        }
    }

    public void removeSelfFromWorld()
    {
        getWorld().removeObject(this);
    }

    private class MouseActor extends greenfoot.Actor
    {
        public MouseActor()
        {
            setImage(blank);
        }

        public void act()
        {
            try
            {
                setLocation(Greenfoot.getMouseInfo().getX(),Greenfoot.getMouseInfo().getY());
            }
            catch(NullPointerException e){}
        }
    }
}
