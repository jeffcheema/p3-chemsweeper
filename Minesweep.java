import greenfoot.Greenfoot;
import greenfoot.Actor;
import java.util.List;

/**
 * A world for minesweeper.
 * 
 * @author Nate Beasley
 * @version 0.9.1
 */
public class Minesweep extends greenfoot.World
{
    private boolean gameOver = false;
    private int numberOfMines;

    /**
     * Default constructor, creates a world of 9x9 pieces with 10 mines
     */
    public Minesweep()
    {
        this(9,10);
    }
    
    /**
     * Creates a square minesweeper world
     * @param size The length of each side
     * @param mines The amount of mines in the world
     */
    public Minesweep(int size, int mines)
    {
        this(size, size, mines);
    }
    
    /**
     * Creates a rectangle minesweeper world
     * @param width The width of the world
     * @param height The height of the world
     * @param mines The amount of mines in the world
     */
    public Minesweep(int width, int height, int mines)
    {
        super(width, height, 45);
        if (mines >= width * height)
            throw new IllegalArgumentException("There are no free spaces on the board! Lower the amount of mines!");
        numberOfMines = mines;
        addMines();
        addNumbersAndHiders();
    }
    
    /**
     * Adds mines to the world
     * @param mines The amount of mines in the world
     */
    private void addMines()
    {
        for (int i = 0; i < numberOfMines; i++)
        {
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            if (getObjectsAt(x,y,Mine.class).size() == 0)
                addObject(new Mine(), x, y);
            else
                i--;
        }
    }
    
    /**
     * Creates numbers and adds them into the world
     */
    private void addNumbersAndHiders()
    {
        for (int x = 0; x < getWidth(); x++)
            for (int y = 0; y < getHeight(); y++)
            {
                if (getObjectsAt(x,y,Mine.class).size() == 0)
                {
                    Number num = new Number();
                    addObject(num,x,y);
                    num.setPicture();
                }
                addObject(new Hider(),x,y);
            }
    }
    
    /**
     * The Minesweep world act method.
     */
    public void act()
    {
        if (getObjects(Hider.class).size() == getObjects(Mine.class).size())
            end();
        if (gameOver && Greenfoot.isKeyDown("r"))
        {
            reset();
        }
    }
    
    /**
     * Resets the world based off of it's current stats
     */
    public void reset()
    {
        Greenfoot.setWorld(new Minesweep(getWidth(), getHeight(), numberOfMines));
    }
    
    /**
     * Let's the user know they won or lost
     */
    public void end()
    {
        removeObjects(getObjects(Hider.class));
        gameOver = true;
    }
    
    /**
     * Let's other objects know the game is over
     * @return The status of the game
     */
    public boolean gameOver()
    {
        return gameOver;
    }
}
