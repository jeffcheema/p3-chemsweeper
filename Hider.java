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
    private static final GreenfootImage question = new GreenfootImage("question.png");
    String questions[][] = {{"What is Avogadro’s number rounded to two sig figs in scientific notation (use ^ to indicate exponents)?", "6.0 x 10^23", "stoichiometry"}, {"What is the molar mass of oxygen rounded to the hundredths place (two decimal places) with units?", "16.00 g/mol", "stoichiometry"}, {"What is the mole ratio of hydrogen to oxygen in a water molecule? Use a colon to indicate ratios.", "2:1", "stoichiometry"}, {"What is the molar mass of one mole of water rounded to the hundredths place (two decimal places) with units?", "18.02 g/mol", "stoichiometry"},{"If a precipitate is formed, has a chemical reaction occurred?", "Yes", "Chemical Reactions"}, {"If the substance is hot after going through a chemical reaction, is the reaction exothermic or endothermic?", "Exothermic", "Chemical Reactions"}, {"What is the oxidation number of oxygen in H2O?", "-2", "Chemical Reactions"}, {"True or false: balancing a chemical equation demonstrates the law of conservation of matter.","True", "Chemical Reactions"},{"Which element has the largest atomic radius?", "Francium", "Atomic Structure"}, {"How many valence electrons does magnesium have?", "2", "Atomic Structure"}, {"True or false: ionization energy increases when moving left to right on the periodic table.", "True", "Atomic Structure"}, {"Which noble gas is isoelectronic with NaF?", "Neon", "Atomic Structure"},{"Is H2SO4 an acid or base?", "Acid", "Acid and Bases"}, {"What is the written name for H2SO4?", "Sulfuric acid", "Acid and Bases"}, {"Do bases receive or donate protons?", "Receive", "Acid and Bases"}, {"Soap is a common example of a(n)... (answer with acid or base)", "Base", "Acid and Bases"},{"Which type of chemical equation shows the functional groups in a molecule: empirical, molecular, or structural?", "Structural", "Ionic, Covalent, and Metalloid Substances"}, {"If a metal and a metal are bonded, are they ionic, covalent, or metallic?", "Metallic", "Ionic, Covalent, and Metalloid Substances"}, {"Which type of substance generally has the highest melting point: ionic, covalent, or metallic?", "Ionic", "Ionic, Covalent, and Metalloid Substances"}, {"Which type of substance NEVER conducts electricity: ionic, covalent, or metallic?", "Covalent", "Ionic, Covalent, and Metalloid Substances"}};
    List<Integer> asked = new ArrayList<Integer>(); 
    private GreenfootImage backgroundImage = normal;
    boolean asking = true;
    String answer;
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
            case 1: 
                
                if (asking) {  
                Random generator = new Random();
                int randomIndex = generator.nextInt(questions.length);
                answer = Greenfoot.ask(questions[randomIndex][0]);
                asking = false;
                }
                getImage().drawImage(flag,0,0);
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
