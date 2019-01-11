
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class Stage4FinalTesting.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class Stage4FinalTesting
{
    private Systems systems1;
    private Director director1;
    private Manager manager1;
    private HREmployee hremployee1;
    private Employee employee1;
    private AuthenticationServer authserver1;
    private HRDatabase database1;
    /**
     * Default constructor for test class Stage4FinalTesting
     */
    public Stage4FinalTesting()
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
        database1 = new HRDatabase();
        systems1.addUser(employee1);
        systems1.addUser(manager1);
        systems1.addUser(director1);
        systems1.addUser(hremployee1);
        systems1.login(hremployee1.getUserId(), "Smith", 2);
        systems1.login(manager1.getUserId(),"Roman", 3);
        hremployee1.setUserId(123456);
        director1.setUserId(654321);
        manager1.setUserId(213456);
    }

    /**
     * Tests the functionality of the login() method, and the methods called within login()
     */
    @Test
    public void testReadPersonalDetails()
    {   
        assert(AuthenticationServer.authenticatePDRecordRequest(manager1, hremployee1.getUserId()) == false);
        assert(AuthenticationServer.authenticatePDRecordRequest(manager1, manager1.getUserId()) == true);
        assert(AuthenticationServer.authenticatePDRecordRequest(hremployee1, 9501) == true);
        assert(AuthenticationServer.authenticatePDRecordRequest(hremployee1, director1.getUserId()) == true);
        manager1.readPersonalDetailsRecord(hremployee1.getUserId());
        manager1.readPersonalDetailsRecord(manager1.getUserId());
        hremployee1.readPersonalDetailsRecord(9501);
        hremployee1.readPersonalDetailsRecord(director1.getUserId());
    }

    /**
     * Tests the functionality of the login() method, and the methods called within login()
     */
    @Test
    public void testCreatePersonalDetailsRecord()
    {
        manager1.createPersonalDetailsRecord(14112, "aName", "asurname", "aDateOfBirth", "aAddress", "aCity", "aCounty", "aPostcode", "123", "456", "aNextOfKin", "789");
        hremployee1.createPersonalDetailsRecord(14113, "aName", "asurname", "aDateOfBirth", "aAddress", "aCity", "aCounty", "aPostcode", "123", "456", "aNextOfKin", "789");
        assert(database1.records.isEmpty() == false);
        assert(AuthenticationServer.authenticatePDRecordRequest(manager1, 14112) == false);
        assert(AuthenticationServer.authenticatePDRecordRequest(hremployee1, 14112) == true);
    }

    /**
     * Tests the functionality of the login() method, and the methods called within login()
     */
    @Test
    public void testAmendPersonalDetails()
    {
        hremployee1.createPersonalDetailsRecord(5556, "oldName", "oldsurname", "oldDateOfBirth", "oldAddress", "oldCity", "oldCounty", "oldPostcode", "123", "456", "oldNextOfKin", "789");
        String oldname = HRDatabase.getRecordById(5556).getName();
        hremployee1.amendPersonalDetailsRecord(5556, "newName", "newSurname", "newDateOfBirth", "oldAddress", "oldCity", "oldCounty", "oldPostcode", "123", "456", "oldNextOfKin", "789");
        String newName = HRDatabase.getRecordById(5556).getName();
        assert(oldname != newName);
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
