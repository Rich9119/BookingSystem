

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class Stage4Testing.
 *
 * @author (Richard Dight, Arkadiusz Kowalski) 
 * @version (V1)
 */
public class Stage4Testing
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
    public Stage4Testing()
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
        systems1.addUser(employee1);
        systems1.addUser(manager1);
        systems1.addUser(director1);
        systems1.addUser(hremployee1);
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
