import greenfoot.*;


/**
 *       An actor object that captures the current visual state of a world object.  A world object is passed to this object in the constructor call.
 * <br />
 * <br />That world (usually not the world that this actor is placed into) can be controlled through the methods of this class.  
 * <l>
 *      <li>For 'Run' and 'Pause', use the 'run' and 'pause' methods (or the 'setActiveState' method) on the PIP object.</li>
 *      <li>For 'Step', use the 'step' method on the PIP object.</li>
 * </l>
 * 
 * <h3>NOTE: DO NOT MODIFY THIS CLASS</h3>
 * 
 *       AUTHOR: danpost
 * <br />DATE: May 22, 2013
 */
public class PIP extends Actor
{
    private World minor; // the world to display in the image of this PIP object
    private Class[] paintOrder; // the paint order of the world to display
    private boolean activeState; // the current active state of this actor which runs the world that is displayed

    /**
     *       creates the picture-in-picture object
     *
     * @param minorWorld the world object to portray
     * @param classes the paint order of the world to portray
     */
    public PIP(World minorWorld, Class[] classes)
    {
        // save arguments to instance fields
        minor = minorWorld;
        paintOrder = classes;
        // create the base image of the PIP object
        int width = minor.getWidth()*minor.getCellSize();
        int height = minor.getHeight()*minor.getCellSize();
        GreenfootImage image = new GreenfootImage(width+11, height+11);
        // paint the background of the base image white
        image.setColor(Color.WHITE);
        image.fill();
        // create the picture frame
        image.setColor(Color.BLACK);
        image.drawRect(0, 0, width+10, height+10);
        image.drawRect(1, 1, width+8, height+8);
        image.drawRect(4, 4, width+2, height+2);
        // set the base image as the current image of this PIP object
        setImage(image);
        // update the picture of this PIP object (display the given world)
        updateImage();
    }
    
    /**
     *       controls the running state of the displayed world
     */
    public void act()
    {
        if (!activeState) return;
        for (Object obj : minor.getObjects(null))
        {
            Actor actor = (Actor)obj;
            actor.act();
        }
        minor.act();
        updateImage();
    }        

    /**
     *       updates the picture of the given world within the image of this PIP object
     */
    private void updateImage()
    {
        GreenfootImage view = new GreenfootImage(minor.getBackground());
        for (Object obj : minor.getObjects(null))
        {
            Actor actor = (Actor)obj;
            if(!isPaintOrderActor(actor))
            {
                int x=actor.getX()*minor.getCellSize()+minor.getCellSize()/2;
                int y=actor.getY()*minor.getCellSize()+minor.getCellSize()/2;
                GreenfootImage img = getActorImage(actor);
                int w=actor.getImage().getWidth(), h=actor.getImage().getHeight();
                view.drawImage(img, x-img.getWidth()/2, y-img.getHeight()/2);
            }
        }
        for(int i=1; i<=paintOrder.length; i++) for(Object obj: minor.getObjects(paintOrder[paintOrder.length-i]))
        {
            Actor actor = (Actor)obj;
            int x=actor.getX()*minor.getCellSize()+minor.getCellSize()/2;
            int y=actor.getY()*minor.getCellSize()+minor.getCellSize()/2;
            GreenfootImage img = getActorImage(actor);
            int w=actor.getImage().getWidth(), h=actor.getImage().getHeight();
            view.drawImage(img, x-img.getWidth()/2, y-img.getHeight()/2);
        }
        getImage().drawImage(view, 5, 5);
    }
    
    /**
     *       returns the image of an actor within the displayed world
     *
     * @param actor the actor whose image is to be placed within the displayed world
     * @return a GreenfootImage object showing the current visual state of an actor
     */
    private GreenfootImage getActorImage(Actor actor)
    {
        GreenfootImage actorImg = actor.getImage();
        int w = actorImg.getWidth();
        int h = actorImg.getHeight();
        int max = Math.max(w, h);
        GreenfootImage image = new GreenfootImage(max*2, max*2);
        image.drawImage(actorImg, max-actorImg.getWidth()/2, max-actorImg.getHeight()/2);
        image.rotate(actor.getRotation());
        return image;
    }
        
    /**
     *       determines whether an actor is an instance of a class contained in the paint order of the displayed world
     *
     * @param actor an actor from within the world that is displayed
     * @return the state of the class of a given actor being contained within the paint order array
     */
    private boolean isPaintOrderActor(Actor actor)
    {
        for(int i=0; i<paintOrder.length; i++) if(actor.getClass().equals(paintOrder[i])) return true;
        return false;
    }

    /**
     *       sets the active state of this PIP object; use this as an alternative to using the 'run' and 'pause' methods
     *
     * @param newActiveState the active state to set this PIP object to
     */
    public void setActiveState(boolean newActiveState)
    {
        activeState = newActiveState;
    }
    /**
     *       gets the current active state of this PIP object
     *
     * @return the current active state of this PIP object
     */
    public boolean getActiveState()
    {
        return activeState;
    }
    
    /**
     *       activates this PIP object, setting the displayed world into a running state
     */
    public void run()
    {
        setActiveState(true);
    }
    
    /**
     *       pauses this PIP object, setting the displayed world into a stopped state
     */
    public void pause()
    {
        setActiveState(false);
    }
    
    /**
     *       executes one cycle of the displayed world
     */
    public void step()
    {
        if (activeState) return;
        activeState = true;
        act();
        activeState = false;
    }
}