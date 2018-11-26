/**
 * Class that represents Data held in a File of type mdf Stores a single
 * measurement and flags it if its bad
 * 
 * @author Feras Salous Also Collaborated with TA's and Nahall Mansouri
 * @version 2018-10-26 Lab Section 13
 *
 */
public class Observation extends AbstractObservation
{
    /**
     * Variables Stored in Type Observation
     */

    private double value;
    // private boolean valid;
    private String stid;

    /**
     * Constructor, that takes in a Double Value and a String value and analyzes if
     * the value is valid.
     * 
     * @param double
     *            value
     * @param String
     *            stid
     */
    public Observation(double value, String stid)
    {
        this.value = value;
        this.stid = stid;

        this.value = value;
        this.stid = stid;
        if (value <= -900)
        {
            valid = false;
        }
        else
        {
            valid = true;
        }
    }

    /**
     * Gets the Value that is constructed in type Observation
     * 
     * @return The double value in the Object Constructed in Observation
     */
    public double getValue()
    {

        return value;
    }

    /**
     * Verifies that the Value which was placed in the Observation Constructor is
     * Valid
     * 
     * @return True if Valid and False if not valid
     */
    public boolean isValid()
    {

        return valid;

    }

    /**
     * Gets the station id contained in the Observation constructor
     * 
     * @return String stid
     * 
     */
    public String getStid()
    {

        return stid;
    }

    /**
     * Formats a String in this format: Double Value C At Station id Example: 36.5 C
     * at HOOK
     * 
     * @return a string in that specfic format
     */

    public String toString()

    {

        return String.format("%.2f C At %s", this.value, this.stid);
    }

}
