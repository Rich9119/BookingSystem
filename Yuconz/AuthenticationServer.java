
/**
 * Write a description of class AuthenticationServer here.
 * 
 * @author (Richard Dight, Arkadiusz Kowalski) 
 * @version (V1)
 */
public class AuthenticationServer
{

    /**
     * Constructor for objects of class AuthenticationServer
     */
    public AuthenticationServer()
    {
    }

    /**
     * Checks to see if the accessLevel of a User object is greater than or equal to the level passed through the
     * parameter.
     * @param the User object, int accessLevel to authorise.
     * @return returns the boolean result of the method.
     */
    public static boolean authorise(User type, int accessLevel)
    {
        if(type instanceof Director && type.getAccessLevel() >= accessLevel)
        {
            return true;
        }
        else if(type instanceof Manager && type.getAccessLevel() >= accessLevel)
        {
            return true;
        }
        else if(type instanceof HREmployee && type.getAccessLevel() >= accessLevel)
        {
            return true;
        }
        else if(type instanceof Reviewer && type.getAccessLevel() >= accessLevel)
        {
            return true;
        }
        else if(type instanceof Employee && type.getAccessLevel() >= accessLevel)
        {
            return true;
        }
        return false;
    }

    /**
     * Returns the an int value based on the type of User object.
     * @param the User object
     * @return returns the default accessLevel based on the type of User
     */
    public static int authenticate(User newUser)
    {      
        if(newUser instanceof Director){
            return 4;
        }
        else if(newUser instanceof Manager){
            return 3;
        }
        else if(newUser instanceof HREmployee){
            return 2;
        }
        else if(newUser instanceof Employee){
            return 0;
        }
        else{
            return 1;
        }
    }

    /**
     * Logs the user out of the system and changes the accessLevel of the User to the one requested.
     * @param the userId of a User object, the accessLevel you wish to have.
     */
    public void reauthenticate(int userId, int accessLevel)
    {
        for(int i = 0; i < Systems.users.size(); i++)
        {
            if(Systems.users.get(i).getUserId() == userId)
            {
                if(Systems.users.get(i).getLoginSession() == true)
                {
                    if(accessLevel >=0 && accessLevel <=4 && accessLevel <= authenticate(Systems.users.get(i)))
                    {
                        Systems.users.get(i).logout();
                        Systems.users.get(i).setAccessLevel(accessLevel);
                    }
                    else
                    {
                        System.out.println("\nAccess Level out of bounds for current User");
                    }
                }
                else
                {
                    System.out.println("\nNo need to reauthenticate. Please specify accessLevel at login");
                }
            }
            else
            {
                System.out.println("\nInvalid userId");
            }
        }
    }

    /**
     * Returns a boolean result based on the given login details which is used for 
     * the User's login function.
     * 
     * @param  The userId you are logging on with, the password, the requested accessLevel
     * @return     true if details match, Or false
     */
    public static boolean authenticateLogin(int userId, String password, int reqAccessLevel)
    {
        int usersL = Systems.users.size();
        for(int i = 0; i < usersL; i++)
        {
            if(Systems.users.get(i).getUserId() == userId)
            {
                if(authorise(Systems.users.get(i), reqAccessLevel) == true && password.equals(Systems.users.get(i).getPassword()))
                {
                    Systems.users.get(i).setAccessLevel(reqAccessLevel);
                    Systems.users.get(i).setLoginSession(true);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns a boolean result based on the given logout ID which is used for 
     * the User's logiout function.
     * 
     * @param  The userId you are logging on with, the password, the requested accessLevel
     * @return     true if details match, Or false
     */
    public static boolean authenticateLogout(int userId)
    {
        int usersL = Systems.users.size();
        for(int i = 0; i < usersL; i++)
        {
            if(Systems.users.get(i).getUserId() == userId && Systems.users.get(i).getLoginSession() == true)
            {
                Systems.users.get(i).setLoginSession(false);
                Systems.users.get(i).setAccessLevel(authenticate(Systems.users.get(i)));
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a boolean result based on the given logout ID which is used for 
     * the User's logiout function.
     * 
     * @param  The userId you are logging on with, the password, the requested accessLevel
     * @return     true if details match, Or false
     */
    public static boolean authenticatePDRecordRequest(User thisUser,int userId)
    {
        if(AuthenticationServer.authorise(thisUser, 2) == true | thisUser.getUserId() == userId)
        {
            if(!(thisUser instanceof Manager))
            {
                return true;
            }
            else if(thisUser.getUserId() == userId)
            {
                return true;
            }
        }
        return false;
    }
    
        /**
     * Returns a boolean result based on the given logout ID which is used for 
     * the User's logiout function.
     * 
     * @param  The userId you are logging on with, the password, the requested accessLevel
     * @return     true if details match, Or false
     */
    public static boolean authenticatePFRRecordRequest(User thisUser,int userId)
    {
        if(AuthenticationServer.authorise(thisUser, 0) == true)
        {
            if(thisUser.getUserId() == userId)
            {
                return true;
            }
        }
        return false;
    }
}
