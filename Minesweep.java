import greenfoot.Greenfoot;
import greenfoot.Actor;
import java.util.*;

/**
 * A world for minesweeper.
 * 
 * @author Nate Beasley
 * @version 0.9.1
 */
public class Minesweep extends greenfoot.World
{
    public String questions[][] = {{"What is Avogadro’s number rounded to two sig figs in scientific notation (use ^ to indicate exponents)?", "6.0 x 10^23", "stoichiometry"}, {"What is the molar mass of oxygen rounded to the hundredths place (two decimal places) with units?", "16.00 g/mol", "stoichiometry"}, {"What is the mole ratio of hydrogen to oxygen in a water molecule? Use a colon to indicate ratios.", "2:1", "stoichiometry"}, {"What is the molar mass of one mole of water rounded to the hundredths place (two decimal places) with units?", "18.02 g/mol", "stoichiometry"},{"If a precipitate is formed, has a chemical reaction occurred?", "Yes", "Chemical Reactions"}, {"If the substance is hot after going through a chemical reaction, is the reaction exothermic or endothermic?", "Exothermic", "Chemical Reactions"}, {"What is the oxidation number of oxygen in H2O?", "-2", "Chemical Reactions"}, {"True or false: balancing a chemical equation demonstrates the law of conservation of matter.","True", "Chemical Reactions"},{"Which element has the largest atomic radius?", "Francium", "Atomic Structure"}, {"How many valence electrons does magnesium have?", "2", "Atomic Structure"}, {"True or false: ionization energy increases when moving left to right on the periodic table.", "True", "Atomic Structure"}, {"Which noble gas is isoelectronic with NaF?", "Neon", "Atomic Structure"},{"Is H2SO4 an acid or base?", "Acid", "Acid and Bases"}, {"What is the written name for H2SO4?", "Sulfuric acid", "Acid and Bases"}, {"Do bases receive or donate protons?", "Receive", "Acid and Bases"}, {"Soap is a common example of a(n)... (answer with acid or base)", "Base", "Acid and Bases"},{"Which type of chemical equation shows the functional groups in a molecule: empirical, molecular, or structural?", "Structural", "Ionic, Covalent, and Metalloid Substances"}, {"If a metal and a metal are bonded, are they ionic, covalent, or metallic?", "Metallic", "Ionic, Covalent, and Metalloid Substances"}, {"Which type of substance generally has the highest melting point: ionic, covalent, or metallic?", "Ionic", "Ionic, Covalent, and Metalloid Substances"}, {"Which type of substance NEVER conducts electricity: ionic, covalent, or metallic?", "Covalent", "Ionic, Covalent, and Metalloid Substances"}};
    public List<Integer> asked = new ArrayList<Integer>(); 
    public boolean asking = false;
    public int lives = 2; 
    String answer;
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
    public boolean askQuestion(boolean death){
                asking = true; 
                        Random generator = new Random();
                int randomIndex = generator.nextInt(questions.length);        
                if(asked.contains(randomIndex)){
                        askQuestion(death);
                }

                answer = Greenfoot.ask(questions[randomIndex][0]).toLowerCase();
                 
                if (answer.equals(questions[randomIndex][1].toLowerCase())){
                asked.add(randomIndex);
                Greenfoot.ask("Yay!! You got this "+ questions[randomIndex][2] + " question correct. You currently have "+lives+" life left! (Press enter to continue)");  

                    return true;
                
                }
                else{
                    asked.add(randomIndex);
                if (death) {
                   Greenfoot.ask("Game is over! (Press enter to continue)");  
 
                   return false;
                }
                else{
                Greenfoot.ask("Oh no! You got this "+ questions[randomIndex][2] + " question incorrect. You currently have "+lives+" life left! (Press enter to continue)") ;   
                }
                return false;
                
                }
                
    };
    
    /**
     * Let's other objects know the game is over
     * @return The status of the game
     */
    public boolean gameOver()
    {
        return gameOver;
    }
}
