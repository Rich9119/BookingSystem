import java.util.*;
/**
 * Write a description of class Reviewer here.
 * 
 * @author (Richard Dight, Arkadiusz Kowalski) 
 * @version (V1)
 */
public abstract class Reviewer extends User
{
    private ArrayList<User> allocatedEmployees = new ArrayList<User>();
    
    /**
     * Constructor for objects of class Reviewer
     */
    public Reviewer(String name, String password)
    {
        super(name, password);
    }
    
    public void allocateReviewer(Reviewer firstReviewer, Reviewer secondReviewer, User reviewedEmployee)
    {
        firstReviewer.allocatedEmployees.add(firstReviewer);
        secondReviewer.allocatedEmployees.add(secondReviewer);
    }
}
