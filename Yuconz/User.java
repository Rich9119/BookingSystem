
/**
 * Contains the attributes of a User object.
 * 
 * @author (Richard Dight, Arkadiusz Kowalski) 
 * @version (V1)
 */
public abstract class User
{
    private int userId;
    private String name;
    private String password;
    private int accessLevel;
    private boolean loginSession;
    /**
     * Constructor for objects of class User
     */
    public User(String name, String password)
    {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.accessLevel = accessLevel;
        this.loginSession = false;
    }

    /**
     * Returns the current User's userID attribute value 
     * @return the User's userId
     */
    public int getUserId()
    {
        return userId;
    }

    /**
     * Returns the current User's password attribute value
     * @return     The User's password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Returns the current User's accessLevel value 
     * @return the User's accessLevel
     */
    public int getAccessLevel()
    {
        return accessLevel;
    }

    /**
     * Sets a User's loginSession to a given boolean value
     * @param the boolean value to apply  to a User's LoginSession
     */
    public void setLoginSession(boolean value)
    {
        this.loginSession = value;
    }

    /**
     * Returns the User's loginSession value
     * @return the User's loginSession
     */
    public boolean getLoginSession()
    {
        return loginSession;
    }

    /**
     * Sets a User's ud to a given int value
     * @param the int value applied to a User's id
     */
    public void setUserId(int id)
    {
        this.userId = id; 
    }

    /**
     * Sends a call to the System which will communicate with the AuthenticationServer
     * to revoke the accessLevel of the User. The system will then print a message
     * for the User based on the Server's response.
     */
    public void logout()
    {
        Systems.logoutAuthRequest(this);
    }

    /**
     * Sets the accessLevel of a User
     * 
     * @param  The accessLevel applied to User
     */
    public void setAccessLevel(int level)
    {
        this.accessLevel = level;
    }

    /**
     * Method call to amend a document, input involves: userId of the employee for a record + other parameters the record includes. Works through System to be validated, then to Database to perform
     */
    public void amendPersonalDetailsRecord(int userId, String name, String surname, String DOB, String address, String city, String county, String postcode, String landline, String mobileNum, String nextOfKin, String nextOfKinContactNum)
    {
        Systems.amendPDRecordRequest(this, userId, name, surname, DOB, address, city, county, postcode, landline, mobileNum, nextOfKin, nextOfKinContactNum);
    }
    
        /**
     * Method call to amend a document, input involves: userId of the employee for a record + other parameters the record includes. Works through System to be validated, then to Database to perform
     */
    public void amendPerformanceReviewRecord(int userId, String name, String managerDirector, String secondManagerDirector, String section, String jobTitle)
    {
        Systems.amendPFRRecordRequest(this, userId, name, managerDirector, secondManagerDirector, section, jobTitle);
    }

    /**
     * Method call to create a document, input involves: userId of the employee for a record + other parameters the record includes. Works through System to be validated, then to Database to perform
     */
    public void createPersonalDetailsRecord(int userID, String name, String surname, String DOB, String address, String city, String county, String postcode, String landline, String mobileNum, String nextOfKin, String nextOfKinContactNum)
    {
        Systems.newPDRecordRequest(this, userID, name, surname, DOB, address, city, county, postcode, landline, mobileNum, nextOfKin, nextOfKinContactNum);
    }

    /**
     * Method call to read a document, input involves: userId of the employee for a record. Works through System to be validated, then to Database to perform
     */
    public void readPersonalDetailsRecord(int userId)
    {
        Systems.readPDRecordRequest(this, userId);
    }
    
        /**
     * Method call to read a document, input involves: userId of the employee for a record. Works through System to be validated, then to Database to perform
     */
    public void readPerformanceReviewRecord(int userId)
    {
        Systems.readPFRRecordRequest(this, userId);
    }
    
    public void createPerformanceReviewRecord(int userId, String name, String managerDirector, String secondManagerDirector, String section, String jobTitle)
    {
        Systems.newPFRRecordRequest(this, userId, name, managerDirector, secondManagerDirector, section, jobTitle);
    }
}
