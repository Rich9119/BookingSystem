
/**
 * Write a description of class PersonalDetailsRecord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PersonalDetailsRecord extends Record
{
    private String surname;
    private String dateOfBirth;
    private String address;
    private String city;
    private String county;
    private String postcode;
    private String telephone;
    private String mobileNumber;
    private String nextKin;
    private String nextKinNumber;
    /**
     * Constructor for objects of class PersonalDetailsRecord
     */
    public PersonalDetailsRecord(int userId, String name)
    {
        super(userId, name);
    }

    public void setSurname(String newSurname)
    {
        this.surname = newSurname;
    }

    public void setDOB(String newDOB)
    {
        this.dateOfBirth = newDOB;
    }

    public void setAddress(String newAddress)
    {
        this.address = newAddress;
    }

    public void setCity(String newCity)
    {
        this.city = newCity;
    }

    public void setCounty(String newCounty)
    {
        this.county = newCounty;
    }

    public void setPostcode(String newPostcode)
    {
        this.postcode = newPostcode;
    }

    public void setTelephone(String newTelephone)
    {
        this.telephone = newTelephone;
    }

    public void setMobileNumber(String newMobileNumber)
    {
        this.mobileNumber = newMobileNumber;
    }

    public void setNextOfKin(String newNextOfKin)
    {
        this.nextKin = newNextOfKin;
    }

    public void setNextKinNumber(String newNextKinNumber)
    {
        this.nextKinNumber = newNextKinNumber;
    }

    public String getSurname()
    {
        return this.surname;
    }

    public String getDOB()
    {
        return this.dateOfBirth;
    }

    public String getAddress()
    {
        return this.address;
    }

    public String getCity()
    {
        return this.city;
    }

    public String getCounty()
    {
        return this.county;
    }

    public String getPostcode()
    {
        return this.postcode;
    }

    public String getTelephone()
    {
        return this.telephone;
    }

    public String getMobileNumber()
    {
        return this.mobileNumber;
    }

    public String getKinName()
    {
        return this.nextKin;
    }

    public String getNextKinNumber()
    {
        return this.nextKinNumber;
    }
}
