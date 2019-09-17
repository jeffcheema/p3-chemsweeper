import greenfoot.GreenfootImage;
import greenfoot.Greenfoot;
import javax.swing.JOptionPane;

/**
 * The menu and HUD world that creates minesweeper worlds for the user.
 * 
 * @author Nate Beasley
 * @version 0.2.1
 */
public class HUD extends greenfoot.World
{
    private Class[] paintOrder = {Hider.class, Number.class, Mine.class};
    private PIP msActor;
    
    private static GreenfootImage bg = new GreenfootImage(600,400);
    
    private int width = 9;
    private int height = 9;
    private int mines = 10;
    private final static int MAX_SIZE = 50;

    /**
     * Constructor for objects of class Menu.
     */
    public HUD()
    {
        super(600, 400, 1);
        background();
        updateStats(9,9,10);
        addObject(msActor,getWidth()/2,getHeight()/2);
        msActor.run();
    }
    
    /**
     * Sets the background
     */
    private void background()
    {
        bg.setColor(greenfoot.Color.GRAY);
        bg.fill();
        setBackground(bg);
    }
    
    public void act()
    {
        
    }
    
    private void updateStats(int w, int h, int m)
    {
        width = w;
        height = h;
        mines = m;
        if (w > MAX_SIZE || h > MAX_SIZE)
            throw new IllegalArgumentException("There cannot be more than " + MAX_SIZE + " cells!");
        updateWorld();
    }
    
    private void updateWorld()
    {
        msActor = new PIP(new Minesweep(width, height, mines), paintOrder);
    }
    
    
    /*
     * A private button used for this world, it is private and in this world
     * because it directly edits stats of this world.
     *
    private class StatButton extends Button
    {
        private static final GreenfootImage img = new GreenfootImage("Stats.png");
        
        public StatButton()
        {
            setImage(img);
        }
        
        public void whenDragged(){}
        public void whenHovered(){}
        
        public void whenClicked()
        {
            setStats();
        }
        
        private void setStats()
        {
            int width;
            int height;
            int mines;
            boolean boo = true;
            JOptionPane.showMessageDialog(null, "When you close this box, type two digits 0-9 to determine the width of the world, ex: " + Greenfoot.getRandomNumber(10) + " & " + Greenfoot.getRandomNumber(10), "Choose the width", JOptionPane.WARNING_MESSAGE);
            while (boo)
            {
                String key = Greenfoot.getKey();
                
            }
        }
    }*/
}
