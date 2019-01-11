import java.util.*;
import java.io.*;
/**
 * Write a description of class System here.
 * 
 * @author (Richard Dight, Arkadiusz Kowalski) 
 * @version (V1)
 */
public class Systems
{
    protected static ArrayList<User> users = new ArrayList<User>();
    protected static ArrayList<String> userIds = new ArrayList<String>(); //Contains the userIds of all Users.
    /**
     * Constructor for objects of class System
     */
    public Systems()
    {
    }

    /**
     * Generates a unique id and assigns the value to the User.
     * The User is then added to arraylist of Users and their userId is passed into the userIds arraylist.
     * @param  The User Object you wish to add to the system.
     */
    public void addUser(User newUser)
    {
        int id = generateId();
        newUser.setUserId(id);       
        newUser.setAccessLevel(AuthenticationServer.authenticate(newUser));
        users.add(newUser);
        userIds.add(String.valueOf(id));
    }

    /**
     * Deletes a User from the System by removing the from the User arraylist. This method will be called
     * when a staff member leaves the company. Their userID is kept within the arraylist of userIds.
     * @param  The id of the User you wish to remove from the System.
     */
    public void deleteUser(int id)
    {
        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i).getUserId() == id){
                users.get(i).setLoginSession(false);
                users.remove(i);
            }
        }
    }

    /**
     * Requests the login authentication of a User by passing login details into the AuthenticateServer. 
     * If the Server returns the login as true. The user will then recieve a message regarding the outcome of
     *  the login attempt.
     * @param  The User's id, password and the accessLevel they have requested.
     */
    public void login(int id, String password, int reqAccessLevel)
    {
        boolean login = AuthenticationServer.authenticateLogin(id,password,reqAccessLevel);
        if(login == true)
        {
            System.out.println("\nUser has succesfully logged in");
        }
        else
        {
            System.out.println("\nLogin failed. Please check credentials and try again.");
        }
    }

    /**
     * Requests the authentication of a User's logout call. This method calls the authenticateLogout method within AuthenticationServer,
     * and returns true if the authentication was a success. This result is then passed back to the User
     * @param  The User object you wish to authorise.
     * @return The boolean result 
     */
    public static void logoutAuthRequest(User thisUser)
    {
        if(AuthenticationServer.authenticateLogout(thisUser.getUserId()) == false)
        {
            System.out.println("Logout failed. User must be logged in first.");
        }
        else
        {
            System.out.println("User succesfully logged out.");
        }
    }

    /**
     * Requests the authorisation of a User by calling the auroise method within AuthenticationServer, and
     * returns true, if the authorisation was a success.
     * @param  The User object you wish to authorise.
     * @return The boolean result 
     */
    public static boolean authorisationRequest(User thisUser)
    {
        return AuthenticationServer.authorise(thisUser, thisUser.getAccessLevel());
    }

    /**
     * The method proceeds the call by User, validating the authorisation of the user calling the method, if successful proceed else abandon.
     */
    public static void newPDRecordRequest(User thisUser, int userId, String name, String surname, String DOB, String address, String city, String county, String postcode, String landline, String mobileNum, String nextOfKin, String nextOfKinContactNum)
    {
        if(AuthenticationServer.authenticatePDRecordRequest(thisUser, userId) == true)
        {
            if(thisUser.getLoginSession() == true)
            {
                if(!(thisUser instanceof Director))
                {
                    try{    
                        HRDatabase.createPDRecord(userId, name, surname, DOB, address, city, county, postcode, landline, mobileNum, nextOfKin, nextOfKinContactNum);
                        System.out.println("New personal details record successfuly created");
                    } catch (IOException e){
                        System.out.println("Unable to create Record");
                    }
                }
                else
                {
                    System.out.println("You must be a HREmployee to create an inital record");
                }
            }
            else
            {
                System.out.println("Please login to use this function");
            }
        }
        else
        {
            System.out.println("Unauthorised access. Your access level is: "+ thisUser.getAccessLevel() + ". This action requires an access level greaer than 2 \nand can only used by Directors and HREmployees");
        }
    }

    /**
     * The method proceeds the call by User, validating the authorisation of the user calling the method, if successful proceed else abandon.
     */
    public static void newPFRRecordRequest(User thisUser, int userId, String name, String managerDirector, String secondManagerDirector, String section, String jobTitle)
    {
        if(AuthenticationServer.authenticatePFRRecordRequest(thisUser, thisUser.getUserId()) == true)
        {
            if(thisUser.getLoginSession() == true)
            {
                try{    
                    HRDatabase.createPFRRecord(userId, name, managerDirector, secondManagerDirector, section, jobTitle);
                    System.out.println("New Performance Review Record successfuly created");
                } catch (IOException e){
                    System.out.println("Unable to create Record");
                }
            }
            else
            {
                System.out.println("Please login to use this function");
            }   
        }
        else
        {
            System.out.println("Unauthorised access. Your access level is: "+ thisUser.getAccessLevel() + ". This action requires an access level greaer than 0");
        }
    }

    /**
     * The method proceeds the call by User, validating the authorisation of the user calling the method, if successful proceed else abandon.
     */
    public static void amendPDRecordRequest(User thisUser, int userId, String name, String surname, String DOB, String address, String city, String county, String postcode, String landline, String mobileNum, String nextOfKin, String nextOfKinContactNum)
    {
        if(AuthenticationServer.authenticatePDRecordRequest(thisUser, userId) == true)
        {
            try{    
                HRDatabase.amendPDRecord(userId, name, surname, DOB, address, city, county, postcode, landline, mobileNum, nextOfKin, nextOfKinContactNum);
                System.out.println("Document successfuly amended");
            } catch (IOException e){
                System.out.println("The Reocrd does not exist.");
            }
        }
        else if((AuthenticationServer.authenticatePDRecordRequest(thisUser, userId) == false))
        {
            System.out.println("Unauthorised access. Your access level is: "+ thisUser.getAccessLevel() + ". This action requires an access level greaer than 2 \nand can only used by Directors and HREmployees");
        }
        else if(thisUser.getLoginSession() == false)
        {
            System.out.println("Please login to use this function");
        }
    }
    
     /**
     * The method proceeds the call by User, validating the authorisation of the user calling the method, if successful proceed else abandon.
     */
    public static void amendPFRRecordRequest(User thisUser, int userId, String name, String managerDirector, String secondManagerDirector, String section, String jobTitle)
    {
        if(AuthenticationServer.authenticatePFRRecordRequest(thisUser, thisUser.getUserId()) == true)
        {
            try{    
                HRDatabase.amendPFRRecord(userId, name, managerDirector, secondManagerDirector, section, jobTitle);
                System.out.println("Document successfuly amended");
            } catch (IOException e){
                System.out.println("The Reocrd does not exist.");
            }
        }
        else if((AuthenticationServer.authenticatePDRecordRequest(thisUser, userId) == false))
        {
            System.out.println("Unauthorised access. Your access level is: "+ thisUser.getAccessLevel() + ". This action requires an access level greaer than 2 \nand can only used by Directors and HREmployees");
        }
        else if(thisUser.getLoginSession() == false)
        {
            System.out.println("Please login to use this function");
        }
    }

    /**
     * The method proceeds the call by User, validating the authorisation of the user calling the method, if successful proceed else abandon.
     */
    public static void readPDRecordRequest(User thisUser, int userId)
    {
        if(AuthenticationServer.authenticatePDRecordRequest(thisUser, userId) == true)
        {
            try{    
                HRDatabase.readPDRecord(userId); 
            } catch (IOException e){
                System.out.println("The Reocrd does not exist.");
            }
        }
        else
        {
            System.out.println("Unauthorised access. Your access level is: "+ thisUser.getAccessLevel() + ". Requests for Records that are not your own can only be called by HREmployees and Directors");
        }
    }
    
        /**
     * The method proceeds the call by User, validating the authorisation of the user calling the method, if successful proceed else abandon.
     */
    public static void readPFRRecordRequest(User thisUser, int userId)
    {
        if(AuthenticationServer.authenticatePFRRecordRequest(thisUser, thisUser.getUserId()) == true)
        {
            try{    
                HRDatabase.readPFRRecord(userId); 
            } catch (IOException e){
                System.out.println("The Reocrd does not exist.");
            }
        }
        else
        {
            System.out.println("Unauthorised access. Your access level is: "+ thisUser.getAccessLevel() + ". Requests for Records that are not your own can only be called by HREmployees and Directors");
        }
    }

    /**
     * The generator for all userIds. If the arraylist of userIds contains the new id, the method will 
     * be called again.
     * @return  The newly generated ID.
     */
    public int generateId()
    {
        Random r = new Random();
        int n = 100000 + r.nextInt(900000);
        int usersL = userIds.size();
        for(int i = 0; i < usersL; i++)
        {
            if(userIds.get(i) == String.valueOf(n))
            {
                generateId();
            }
        }
        return n;
    }
}
