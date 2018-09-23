
/**
 * Write a description of class NPC here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NPC
{
    private final String name;
    private final int strength;
    private final int wisdom;
    private final String Description;
    private final String Answer;
    /**
     * Constructor for objects of class NPC
     */
    public NPC()
    {
        name = "Beecher";
        strength = 49;
        wisdom = 78;
        Description = "Mr. Beecher is your Computer Science teacher" + "\n" +
                      "You've better handed your LabReport in! Or is it already past 10?";
        Answer = "Mr. Beecher: 'Hey, have you seen the BlueJ book somewhere?'";
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String getDescription()
    {
        return Description;
    }
    
    public String getName(){
        return name;
    }
    
    public int getStrength(){
        return strength;
    }
    
    public int getWisdom(){
        return wisdom;
    }
    
    public String getAnswer(){
        return Answer;
    }
}
