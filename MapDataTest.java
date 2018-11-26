
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Feras salous Class thaat Tests MapData Methods
 * @version 2018-10-26
 */
public class MapDataTest
{
    /**
     * Tests getIndex of method in MapData, to retrieve the Index where a passed in
     * String is located at
     * 
     * @throws IOException
     */
    @Test
    public void getIndexOf() throws IOException
    {
        final int YEAR = 2018;
        final int MONTH = 8;
        final int DAY = 30;
        final int HOUR = 17;
        final int MINUTE = 45;
        final String directory = "Data";
        MapData mapData = new MapData(YEAR, MONTH, DAY, HOUR, MINUTE, directory);
        mapData.parseFile();

        Integer expected = null;
        Integer actual = mapData.getIndexOf(directory);

        Assert.assertEquals(expected, actual);

    }

    /**
     * Tests createFileName in MapData to ensure it creates the file, from the
     * specified file format.
     * 
     * @throws IOException
     */

    @Test
    public void createFileName() throws IOException
    {
        MapData myHour = new MapData(2018, 8, 30, 17, 45, "data");
        String expected = "data/201808301745.mdf";
        String actual = myHour.createFileName(2018, 8, 30, 17, 45, "data");
        Assert.assertEquals(expected, actual);

    }

    /**
     * Tests getStatisctics in MapData.
     * 
     * @throws IOException
     */

    @Test
    public void testGetStatistics() throws IOException
    {
        MapData myHour = new MapData(2018, 8, 30, 17, 45, "data");
        String actual = myHour.getStatistics(StatsType.AVERAGE, "TAIR").toString();

        String expected = "31.344166666666673 Mesonet AVERAGE";

        Assert.assertEquals(expected, actual);

    }

    /**
     * Tests the toString method in MapData, to ensure that it returns the
     * implemeted String.
     * 
     * @throws IOException
     */

    @Test
    public void testTostring() throws IOException
    {
        final int YEAR = 2018;
        final int MONTH = 8;
        final int DAY = 30;
        final int HOUR = 17;
        final int MINUTE = 45;
        final String directory = "Data";
        MapData mapData = new MapData(YEAR, MONTH, DAY, HOUR, MINUTE, directory);
        mapData.parseFile();
        String actual = mapData.toString();
        String expected = "========================================================\n" + "=== 2018-08-30 05:45 === \n"
                + " ========================================================\n"
                + "Maximum Air Temperature[1.5m] = 36.5 C at HOOK\n"
                + "Minimum Air Temperature[1.5m] = 20.8 C at MIAM\n"
                + "Average Air Temperature[1.5m] = 31.3 C at Mesonet\n"
                + "========================================================\n"
                + "========================================================\n"
                + "Maximum Air Temperature[9.0m] = 34.9 C at HOOK\n"
                + "Minimum Air Temperature[9.0m] = 20.7 C at MIAM\n"
                + "Average Air Temeprature[9.0m] = 31.0 C at Mesonet\n"
                + "========================================================\n"
                + "Maximum Solar Radiation[1.5m] = 968.0 W/m^2 at SLAP\n"
                + "Minimum Solar Radiation[1.5m] = 163.0 W/m^2 at MIAM\n"
                + "Average Solar Radiation[1.5m] = 814.3 W/m^2 at Mesonet\n" + "";
        Assert.assertEquals(actual, expected);

    }

}
