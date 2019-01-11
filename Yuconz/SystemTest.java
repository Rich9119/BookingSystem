
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SystemTest.
 *
 * @author (Richard Dight, Arkadiusz Kowalski) 
 * @version (V1)
 */
public class SystemTest
{

    private Systems systems1;
    private Director director1;
    private Manager manager1;
    private HREmployee hremployee1;
    private Employee employee1;
    private AuthenticationServer authserver1;
    /**
     * Default constructor for test class SystemTest
     */
    public SystemTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        systems1 = new Systems();
        director1 = new Director("John","Doe");
        manager1 = new Manager("Miles","Roman");
        hremployee1 = new HREmployee("James","Smith");
        employee1 = new Employee("Bob","The Builder");
        authserver1 = new AuthenticationServer();
    }

    /**
     * Tests the functionality of the login() method
     */
    @Test
    public void testAddUser()
    {
        systems1.addUser(director1);
        assert(systems1.users.isEmpty() == false);
        assert(director1.getUserId() != 0);
        assert(director1.getAccessLevel() == 4);
    }

    /**
     * Tests the functionality of the login() method, and the methods called within login()
     */
    @Test
    public void testLogin()
    {
        systems1.addUser(manager1);
        systems1.addUser(employee1);
        systems1.login(manager1.getUserId(),"Roman",3);
        assert(AuthenticationServer.authenticateLogin(manager1.getUserId(),"Roman",3) == true);
        assert(manager1.getLoginSession() == true);
        systems1.login(employee1.getUserId(),"Roman",0);
        assert(employee1.getLoginSession() == false);
        assert(systems1.userIds.isEmpty() == false);
    }

    /**
     * Tests the functionality of the logout() method, and the methods called within logout()
     */
    @Test
    public void testLogout()
    {
        systems1.addUser(manager1);
        systems1.addUser(director1);
        systems1.addUser(employee1);
        systems1.addUser(hremployee1);
        assert(authserver1.authenticateLogout(employee1.getUserId()) == false);
        systems1.login(manager1.getUserId(),"Roman",3);
        systems1.login(employee1.getUserId(),"The Builder",0);
        manager1.logout();
        assert(manager1.getLoginSession() == false);
        assert(authserver1.authenticateLogout(employee1.getUserId()) == true);
    }


    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
