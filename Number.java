
/**
 * A number sees how many mines are around it and set's it's picture accordingly
 * 
 * @author Nate Beasley
 * @version 1.0.0
 */
public class Number extends greenfoot.Actor
{
    public void setPicture()
    {
        int num = 0;
        for (int dx = -1; dx <= 1; dx++)
            for (int dy = -1; dy <= 1; dy++)
                if (getOneObjectAtOffset(dx, dy, Mine.class) != null)
                    num++;
        if (num == 0)
            getWorld().removeObject(this);
        else
            setImage(new greenfoot.GreenfootImage(num + ".png"));
    }
}
