
/**
 * Abstract class Record - Contains the similarities between various types of records.
 * 
 * @author (Richard Bassey-Dight, Arkadiusz Kowalski)
 * @version (1.0)
 */
public abstract class Record
{
    private int userId;
    private String name;

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y    a sample parameter for a method
     * @return        the sum of x and y 
     */
    public Record(int userId, String name)
    {
        this.userId = userId;
        this.name = name;
    }

    public int getUserId()
    {
        return this.userId;
    }

        public void setName(String newName)
    {
        this.name = newName;
    }
    
        public String getName()
    {
        return this.name;
    }
}
