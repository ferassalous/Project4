import java.text.ParseException;

import java.time.LocalDateTime;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Ferassalous Class that Test Statistics Methods
 * @version 2018-10-26
 */
public class StatisticsTest
{

    /**
     * Test for creating date from string for a ZonedDateTime Object
     * 
     * @throws ParseException
     */
    @Test
    public void testCreateZDateFromString() throws ParseException
    {
        LocalDateTime myLocTime = LocalDateTime.of(2018, 1, 12, 2, 59);
        ZonedDateTime myTime = ZonedDateTime.of(myLocTime, ZoneId.of("UTC+1"));
        Statistics myZDate = new Statistics(1.0, "Roos", myTime, 2, StatsType.AVERAGE);

        String dateTimeStr = "2010-01-04T01:32:27 CST";
        ZonedDateTime expected = myZDate.creatZDateFromString(dateTimeStr);
        ZonedDateTime actual = ZonedDateTime.of(2010, 1, 04, 01, 32, 27, 0, ZoneId.of("America/Chicago"));

        Assert.assertEquals(expected, actual);

    }

    /**
     * Test numberOfReportingStations
     */

    @Test
    public void testNumberOfReportingStations()
    {
        LocalDateTime myLocTime = LocalDateTime.of(2018, 1, 12, 2, 59);
        ZonedDateTime myTime = ZonedDateTime.of(myLocTime, ZoneId.of("UTC+1"));
        Statistics myStatTime = new Statistics(1.0, "Roos", myTime, 2, StatsType.AVERAGE);

        int expected = myStatTime.getNumberOfReportingStations();
        int actual = 2;

        Assert.assertEquals(expected, actual);

    }

    /**
     * Compares two time values and checks if this.ZonedDatetime > ZonedDateTime if
     * the time is newerThan
     * 
     */
    @Test
    public void testNewerThanZonedDateTime()
    {

        LocalDateTime myLocTime = LocalDateTime.of(2018, 1, 12, 2, 59);
        ZonedDateTime myTime = ZonedDateTime.of(myLocTime, ZoneId.of("UTC+1"));

        ZonedDateTime myTime2 = ZonedDateTime.of(myLocTime, ZoneId.of("UTC+2"));

        Statistics myStatTime = new Statistics(1.0, "Roos", myTime, 2, StatsType.AVERAGE);

        Boolean expected = myStatTime.newerThan(myTime2);

        Assert.assertFalse(expected);

    }

    @Test
    public void testSameAsZonedDateTime()
    {
        ZonedDateTime sameTime = ZonedDateTime.now();
        ZonedDateTime sameTime2 = ZonedDateTime.now();

        Statistics myStatTime = new Statistics(1.0, "Roos", sameTime, 2, StatsType.AVERAGE);

        Boolean expected = myStatTime.sameAs(sameTime2);

        Assert.assertFalse(expected);

    }

    @Test
    public void testolderThanZonedDateTime()
    {
        LocalDateTime myLocTime = LocalDateTime.of(2018, 1, 12, 2, 59);
        ZonedDateTime myTime = ZonedDateTime.of(myLocTime, ZoneId.of("UTC+1"));
        Statistics myStatTime = new Statistics(1.0, "Roos", myTime, 2, StatsType.AVERAGE);

        ZonedDateTime myTime2 = ZonedDateTime.of(myLocTime, ZoneId.of("UTC+4"));

        Boolean expected = myStatTime.olderThan(myTime2);

        Assert.assertTrue(expected);

    }

    /**
     * Compares two time values and checks if they are this.test > comapredTest.
     * 
     */

    @Test
    public void testNewerThanGregorian()
    {
        GregorianCalendar myTest = new GregorianCalendar(2050, 8, 22, 22, 22);
        Statistics myTest1 = new Statistics(20.0, "Roos", myTest, 100, StatsType.MINIMUM);
        GregorianCalendar newer = new GregorianCalendar(2018, 03, 22, 22, 22);
        boolean test = myTest1.newerThan(newer);
        Assert.assertTrue(test);

    }

    /**
     * Compares two time values and checks if they are this.test < comapredTest.
     */
    @Test
    public void testOlderThanGregorian()
    {
        GregorianCalendar myTest = new GregorianCalendar(2018, 8, 22, 22, 22);
        Statistics myTest1 = new Statistics(20.0, "Roos", myTest, 100, StatsType.MINIMUM);
        GregorianCalendar newer = new GregorianCalendar(2050, 03, 22, 22, 22);
        boolean test = myTest1.olderThan(newer);
        Assert.assertTrue(test);

    }

    /**
     * Compares two time values and checks if they are this.test = comapredTest. .
     */
    @Test
    public void testSameAsGreogorian()
    {
        GregorianCalendar myTest = new GregorianCalendar(2050, 03, 22, 22, 22);
        Statistics myTest1 = new Statistics(20.0, "Roos", myTest, 100, StatsType.MINIMUM);
        GregorianCalendar newer = new GregorianCalendar(2050, 03, 22, 22, 22);
        boolean test = myTest1.sameAs(newer);
        Assert.assertTrue(test);

    }

    /**
     * Test method for
     * {@link Statistics#Statistics(double, java.lang.String, java.util.GregorianCalendar, int, StatsType)}.
     */
    @Test
    public void testStatisticsDoubleStringGregorianCalendarIntStatsType()
    {
        GregorianCalendar myTest = new GregorianCalendar(2018, 8, 30, 17, 45);
        Statistics myTest1 = new Statistics(20.0, "Roos", myTest, 100, StatsType.MINIMUM);
        String actual = myTest1.getStid();
        Assert.assertEquals("Roos", actual);

    }

    /**
     * Test for creating date from string for a Gregorian Calendar Object.
     * 
     * @throws ParseException
     */

    @Test
    public void testGregorianCalendatCreateDateFromString() throws ParseException
    {
        GregorianCalendar myTest = new GregorianCalendar(2018, 8, 30, 17, 45);
        Statistics myTest1 = new Statistics(15.0, "Test Station", myTest, 200, StatsType.AVERAGE);
        GregorianCalendar myStringFromDate = myTest1.createDateFromString("2010-01-04T01:32:27 CST");
        GregorianCalendar expected = new GregorianCalendar(2010, 0, 04, 01, 32, 27);

        Assert.assertEquals(expected, myStringFromDate);

    }

    /**
     * Test Statistics toString
     */
    @Test
    public void testToString()
    {
        GregorianCalendar myTest = new GregorianCalendar(2050, 03, 22, 22, 22);
        Statistics myTest1 = new Statistics(20.0, "Roos", myTest, 100, StatsType.MINIMUM);
        String actual = myTest1.toString();
        Assert.assertEquals(actual, "20.0 Roos MINIMUM");

    }

}
