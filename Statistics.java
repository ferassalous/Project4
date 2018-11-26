import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;

/**
 * This is the Statistics class which has a constructor and methods for
 * Gregorian And ZonedDateTime Calendar
 * 
 * @author Ferassalous
 * @version 2018-10-26
 */
public class Statistics extends Observation implements DateTimeComparble
{
    /**
     * String representing DATE_TIME_FORMAT as such: "yyyy-MM-dd'T'HH:mm:ss z"
     * 
     */
    protected String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss z";

    /**
     * GregorainCalendar utcDateTime
     */
    protected DateTimeFormatter format;

    private GregorianCalendar utcDateTime;
    /**
     * The numberOfReportingStations in the Statistics Class
     */

    private int numberOfReportingStations;
    /**
     * Represents StatsType statsType
     */
    private StatsType statsType;
    /**
     * ZonedDateTime ztdDateTime
     */
    private ZonedDateTime ztdDateTime;

    /**
     * The Constructor for Statistcs which takes in a double value, a string
     * representing stationId, an int representing the numberOfvalidstations, and a
     * StatType.
     * 
     * @param value
     * @param stid
     * @param dateTimeStr
     * @param numberOfValidStations
     * @param inStatType
     * @param utcDateTime
     */
    public Statistics(double value, String stid, GregorianCalendar dateTime, int numberOfValidStations,
            StatsType inStatType)
    {
        super(value, stid);
        this.utcDateTime = dateTime;
        this.numberOfReportingStations = numberOfValidStations;
        this.statsType = inStatType;

    }

    /**
     * The Constructor for Statistics class which takes in a double value, a string
     * representing stationId, a Gregoriancalendar, and an int value representing
     * numberOfvalidStations
     * 
     * @param value
     * @param stid
     * @param dateTime
     * @param numberOfValidStations
     * @param inStatType
     */

    public Statistics(double value, String stid, ZonedDateTime dateTime, int numberOfValidStations,
            StatsType inStatType)
    {
        super(value, stid);
        this.ztdDateTime = dateTime;
        this.numberOfReportingStations = numberOfValidStations;
        this.statsType = inStatType;

    }

    /**
     * Takes in a String with holding a date and formats it into a normal Date.
     * 
     * @param dateTimeStr
     * @return The converted String represantion into Date Format
     * @throws ParseException
     */

    public GregorianCalendar createDateFromString(String dateTimeStr) throws ParseException
    {
        SimpleDateFormat myFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        myFormat.parse(dateTimeStr);

        return (GregorianCalendar) myFormat.getCalendar();

    }

    /**
     * Create Dare from string method for ZonedDateTime
     * 
     * @retrun
     */
    public ZonedDateTime creatZDateFromString(String dateTimeStr) throws ParseException
    {
        format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        return ZonedDateTime.parse(dateTimeStr, format);

    }

    /**
     * Creates a String represantion from Date
     * 
     * @param calender
     * @return The converted Date represantion into a String
     */

    public String createStringFromDate(GregorianCalendar calender)
    {
        SimpleDateFormat myString = new SimpleDateFormat(DATE_TIME_FORMAT);
        return myString.format(calender);

    }

    /**
     * Creates A String Reperesantion from a Date.
     * 
     * @param ZonedDateTime
     * @return the Converted Date represantion into a string
     */
    public String createStringFromDate(ZonedDateTime calendar)
    {
        format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        return format.format(calendar);

    }

    /**
     * Gets the UtcDateTimeString
     * 
     * @retun the utcDateTimeString
     */
    public String getUTCDateTimeString()
    {
        return createStringFromDate(utcDateTime);

    }

    /**
     * Gets the numberOfRepeortingStations
     * 
     * @return the numberOfReportingStations
     */

    public int getNumberOfReportingStations()
    {
        return numberOfReportingStations;

    }

    /**
     * Compares two Date values, and returns true if the date is newerThan the
     * compared Date, or false if the Date is not newerThan
     * 
     * @return returns true if the date is newerThan false if the date is not
     *         newerThan
     */

    public boolean newerThan(GregorianCalendar inDateTime)
    {
        return utcDateTime.after(inDateTime);

    }

    /**
     * Compares two Date values, and returns true if the date is olderThan the
     * compared Date, or false if the Date is not olderThan
     * 
     * @return returns true if the date is olderThan false if the date is not
     *         odlerThan
     */

    public boolean olderThan(GregorianCalendar inDateTime)
    {
        return utcDateTime.before(inDateTime);
    }

    /**
     * Compares two Date values, and returns true if the dates are the same, or
     * false if the Dates are not the same.
     * 
     * @return true if the Dates are the same, false if the Dates are different
     */

    public boolean sameAs(GregorianCalendar inDateTime)
    {
        return utcDateTime.equals(inDateTime);

    }

    @Override
    public boolean newerThan(ZonedDateTime inDateTime)
    {
        return inDateTime.isAfter(ztdDateTime);
    }

    @Override
    public boolean olderThan(ZonedDateTime inDateTime)
    {
        // TODO Auto-generated method stub
        return inDateTime.isBefore(ztdDateTime);

    }

    @Override
    public boolean sameAs(ZonedDateTime inDateTime)
    {
        // TODO Auto-generated method stub
        return inDateTime.equals(ztdDateTime);
    }

    /**
     * Formats a string containing the value and the stationID for that value
     * 
     * @return a String
     */

    public String toString()
    {
        return String.format(getValue() + " " + getStid() + " " + this.statsType);

    }

}
