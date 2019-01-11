import java.util.*;
import java.io.*;
import java.nio.file.*;
/**
 * HRDatabase, database class storing all the records in the company.
 * 
 * @author (Richard Bassey-Dight, Arkadiusz Kowalski)
 * @version (1.0)
 */
public class HRDatabase
{
    protected static ArrayList<Record> records = new ArrayList<Record>();

    /**
     * Constructor for objects of class HRDatabase
     */
    public HRDatabase()
    {
    }

    /**
     * The method is called from User -> System -> this method. taking all the parementers which are passed from user. The method is validated in the system class, and creates the Record in the HRDatabase, adding the record to the ArrayList records
     * Prints the information and saves it to a txt file which is stored in PDRecords directory.
     */
    public static void createPDRecord(int userId, String name, String surname, String DOB, String address, String city, String county, String postcode, String landline, String mobileNum, String nextOfKin, String nextOfKinContactNum) throws IOException
    {
        PersonalDetailsRecord newRecord = new PersonalDetailsRecord(userId, name);
        newRecord.setAddress(address);
        newRecord.setCity(city);
        newRecord.setCounty(county);
        newRecord.setDOB(DOB);
        newRecord.setMobileNumber(mobileNum);
        newRecord.setNextKinNumber(nextOfKinContactNum);
        newRecord.setNextOfKin(nextOfKin);
        newRecord.setPostcode(postcode);
        newRecord.setSurname(surname);
        newRecord.setTelephone(landline);
        records.add(newRecord);

        PrintWriter printWriter = new PrintWriter("PDRecords/"+userId+".txt");
        printWriter.println("Name: "+ name);
        printWriter.println("Surname: "+ surname);
        printWriter.println("Date of Birth: "+DOB);
        printWriter.println("Address: "+address);
        printWriter.println("Town/City: "+city);
        printWriter.println("County: "+county);
        printWriter.println("Postcode: "+postcode);
        printWriter.println("Telephone Number: "+landline);
        printWriter.println("Mobile Number: "+mobileNum);
        printWriter.println("Next of Kin: "+nextOfKin);
        printWriter.println("Next of Kin contact number: "+nextOfKinContactNum);
        printWriter.close();
    }

    /**
     * The method is called from User -> System -> this method. taking all the parementers which are passed from user. The method is validated in the system class, and creates the Record in the HRDatabase, adding the record to the ArrayList records
     * Prints the information and saves it to a txt file which is stored in PDRecords directory.
     */
    public static void createPFRRecord(int userId, String name, String managerDirector, String secondManagerDirector, String section, String jobTitle) throws IOException
    {
        PerformanceReviewRecord newRecord = new PerformanceReviewRecord(userId, name);
        newRecord.setManagerDirector(managerDirector);
        newRecord.setSecondManagerDirector(secondManagerDirector);
        newRecord.setSection(section);
        newRecord.setJobTitle(jobTitle);
        records.add(newRecord);

        PrintWriter printWriter = new PrintWriter("PFRRecords/"+userId+".txt");
        printWriter.println("Name: "+ name);
        printWriter.println("Manager/Director: "+ managerDirector);
        printWriter.println("2nd Manager/Director: "+secondManagerDirector);
        printWriter.println("Section: "+section);
        printWriter.println("jobTitle: "+jobTitle);
        printWriter.close();
    }

    /**
     * The fucntion is called from User -> System - > then this method, which reads the information from the txt document, with the right userId, as all documents are named after userID's. The validation of access for this method is done in the system.
     */
    public static void readPDRecord(int userId) throws IOException
    {
        System.out.println(Files.readAllLines(Paths.get("PDRecords/"+userId+".txt")));
    }

    public static void readPFRRecord(int userId) throws IOException
    {
        System.out.println(Files.readAllLines(Paths.get("PFRRecords/"+userId+".txt")));
    }

    /**
     * This method is very like the createPDRecord method, it is called from User -> System -> this method, which reassigns the values of attributes which hold the information. The validation of access for this method is done in the system class.
     * The method amends the file with changes, and overwrites them. The functionality of the method assures with the loop, that there is a such file with the existing name, to ensure amending a file not creating a new file.
     */    
    public static void amendPDRecord(int userId, String name, String surname, String DOB, String address, String city, String county, String postcode, String landline, String mobileNum, String nextOfKin, String nextOfKinContactNum) throws IOException
    {
        for(int i = 0; i < records.size(); i++)
        {
            if(records.get(i).getUserId() == userId && records.get(i) instanceof PersonalDetailsRecord)
            {
                Record thisRecord = records.get(i);
                ((PersonalDetailsRecord)thisRecord).setName(name);
                ((PersonalDetailsRecord)thisRecord).setAddress(address);
                ((PersonalDetailsRecord)thisRecord).setCity(city);
                ((PersonalDetailsRecord)thisRecord).setCounty(county);
                ((PersonalDetailsRecord)thisRecord).setDOB(DOB);
                ((PersonalDetailsRecord)thisRecord).setMobileNumber(mobileNum);
                ((PersonalDetailsRecord)thisRecord).setNextKinNumber(nextOfKinContactNum);
                ((PersonalDetailsRecord)thisRecord).setNextOfKin(nextOfKin);
                ((PersonalDetailsRecord)thisRecord).setPostcode(postcode);
                ((PersonalDetailsRecord)thisRecord).setSurname(surname);
                ((PersonalDetailsRecord)thisRecord).setTelephone(landline);

                PrintWriter printWriter = new PrintWriter("PDRecords/"+userId+".txt");
                printWriter.println("Date of Birth: "+DOB);
                printWriter.println("Address: "+address);
                printWriter.println("Town/City: "+city);
                printWriter.println("County: "+county);
                printWriter.println("Postcode: "+postcode);
                printWriter.println("Telephone Number: "+landline);
                printWriter.println("Mobile Number: "+mobileNum);
                printWriter.println("Next of Kin: "+nextOfKin);
                printWriter.println("Next of Kin contact number: "+nextOfKinContactNum);
                printWriter.close(); 

            }
        }
    }

    /**
     * This method is very like the createPDRecord method, it is called from User -> System -> this method, which reassigns the values of attributes which hold the information. The validation of access for this method is done in the system class.
     * The method amends the file with changes, and overwrites them. The functionality of the method assures with the loop, that there is a such file with the existing name, to ensure amending a file not creating a new file.
     */    
    public static void amendPFRRecord(int userId, String name, String managerDirector, String secondManagerDirector, String section, String jobTitle) throws IOException
    {
        for(int i = 0; i < records.size(); i++)
        {
            if(records.get(i).getUserId() == userId && records.get(i) instanceof PerformanceReviewRecord)
            {
                Record thisRecord = records.get(i);
                ((PerformanceReviewRecord)thisRecord).setName(name);
                ((PerformanceReviewRecord)thisRecord).setManagerDirector(managerDirector);
                ((PerformanceReviewRecord)thisRecord).setSecondManagerDirector(secondManagerDirector);
                ((PerformanceReviewRecord)thisRecord).setSection(section);
                ((PerformanceReviewRecord)thisRecord).setJobTitle(jobTitle);

                PrintWriter printWriter = new PrintWriter("PFRRecords/"+userId+".txt");
                printWriter.println("Name: "+ name);
                printWriter.println("Manager/Director: "+ managerDirector);
                printWriter.println("2nd Manager/Director: "+secondManagerDirector);
                printWriter.println("Section: "+section);
                printWriter.println("jobTitle: "+jobTitle);
                printWriter.close();

            }
        }
    }

    /**
     * Used for testing purposes
     */
    public static Record getRecordById(int userId)
    {
        for(int i = 0; i < records.size(); i++)
        {
            if(records.get(i).getUserId() == userId)
            {
                return records.get(i);
            }
        }
        return null;
    }
}
