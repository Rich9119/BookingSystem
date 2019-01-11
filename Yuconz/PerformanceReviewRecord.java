
/**
 * Write a description of class PerformanceReviewRecord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PerformanceReviewRecord extends Record
{
    private String managerDirector;
    private String secondManagerDirector;
    private String section;
    private String jobTitle;
    /**
     * Constructor for objects of class PerformanceReviewRecord
     */
    public PerformanceReviewRecord(int userId, String name)
    {
        super(userId, name);
    }

    /**
     * Sets the Manager/Director for the current performance review record.
     * @param the name of the Manager/Director you wish to conduct the review.
     */
    public void setManagerDirector(String managerDirector)
    {
        this.managerDirector = managerDirector;
    }

    
    /**
     * Sets the second Manager/Director for the current performance review record.
     * @param the name of the  second Manager/Director you wish to conduct the review.
     */
    public void setSecondManagerDirector(String managerDirector)
    {
        this.secondManagerDirector = managerDirector;
    }
    
    /**
     * Sets the section where the reviewee currently works.
     * @param the section of the reviewee.
     */
    public void setSection(String section)
    {
        this.section = section;
    }
    
    /**
     * Sets the job title of the person currently being reviewed.
     * @param the job title of the person being reviewed.
     */    
    public void setJobTitle(String jobTitle)
    {
        this.jobTitle = jobTitle;
    }

    /**
     * Returns the primary reviewer for the current Performance Review record.
     * @return the primary reviewer of the Record.
     */    
    public String getManagerDirector()
    {
        return this.managerDirector;
    }

    /**
     * Returns the secondary reviewer for the current Performance Review record.
     * @return The secondary reviewer of the Record.
     */ 
        public String getsecondManagerDirector()
    {
        return this.secondManagerDirector;
    }

    /**
     * Returns the section that the performance review is based on.
     * @return the section of the reviewee.
     */
        public String getSection()
    {
        return this.section;
    }

    /**
     * Returns the job title of the person this record relates to
     * @return The job title of the reviewee that this record relates to.
     */
        public String getjobTitle()
    {
        return this.jobTitle;
    }
}
