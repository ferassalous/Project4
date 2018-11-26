import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

/**
 * Class the holds the Mapdata Constructor and methods to preform on the
 * required Air Statistics.
 * 
 * @author Feras salous Lab section 13 In Collabration with Billy Lo
 * @version 2018-10-03
 */

public class MapData
{

    /**
     * Create a HashMap which holds a String and ArrayList Observation.
     * 
     */
    private HashMap<String, ArrayList<Observation>> dataCatalog;
    /**
     * Created an EnumMap which StatsType and TreeMap which contains String and
     * Statistics.
     */
    private EnumMap<StatsType, TreeMap<String, Statistics>> statistics;

    /**
     * Created A Tree Map which holds a string and an integer refering to
     * paramPostions.
     */
    private TreeMap<String, Integer> paramPostions;

    private static Integer numberOfStations = 0;
    /**
     * Number Of Missing Observations in the Data file
     */
    // private static final int NUMBER_OF_MISSING_OBSERVATIONS = 10;

    /**
     * String Referencing TA9m DATA
     */
    private static final String TA9M = "TA9M";
    /**
     * String Referencing TAIR DATA
     */
    private static final String TAIR = "TAIR";
    /**
     * String Referencing STID DATA
     */
    private static final String STID = "STID";
    /**
     * String Referencing SRAD DATA
     */
    private static final String SRAD = "SRAD";
    /**
     * Station Position in the Data File
     */

    private static final String MESONET = "Mesonet";
    /**
     * String to Reference directory
     */

    /**
     * String Representing fileName
     */
    private String fileName;
    /**
     * GregorianCalendar utcDateTime
     */
    private GregorianCalendar utcDateTime;

    /**
     * This is a Constructor for MapData Class which takes in a year,month, day,
     * hour, minute, and directory. Using this Constructor allows the user to
     * preform many operations, included in the MapData Class.
     * 
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param directory
     * @throws IOException
     */
    public MapData(int year, int month, int day, int hour, int minute, String directory) throws IOException
    {
        dataCatalog = new HashMap<String, ArrayList<Observation>>();
        statistics = new EnumMap<StatsType, TreeMap<String, Statistics>>(StatsType.class);
        paramPostions = new TreeMap<String, Integer>();

        fileName = createFileName(year, month, day, hour, minute, directory);

        utcDateTime = new GregorianCalendar(year, month, day, hour, minute);
        parseFile();

        calculateStatistics();

    }

    /**
     * The method createFileName takes in a year, month, day, hour, minute,and
     * directory This method allows the user to provide the specified parameters and
     * then return a Sting formatted in this manner:data//yearmonthdayhourminute.
     * 
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param directory
     * @return The string representation of the fileName.
     */

    public String createFileName(int year, int month, int day, int hour, int minute, String directory)
    {

        String fileFormat = String.format("%04d%02d%02d%02d%02d.mdf" , year, month, day, hour, minute);
        String fileName = directory + "/" + fileFormat;

        return fileName;

    }

    /**
     * This method takes in a String. This method is implemented in order to locate
     * the indices in the Data files in order to better sort the Data.
     * 
     * @param inParamStr
     */

    private void parseParamHeader(String inParamStr)
    {
        String[] header = inParamStr.split("\\s+");
        for (int index = 0; index < header.length; ++index)

        {
            if (!header[index].equals(""))
            {

                String coulmnName = header[index];

                paramPostions.put(coulmnName, index - 1);
            }

        }

    }

    /**
     * 
     * @param inParamStr
     *            The String passed into to reterive the Index Of Value inParamStr
     * @return The Integer value at String inParamStr
     */
    public Integer getIndexOf(String inParamStr)
    {

        return paramPostions.get(inParamStr);

    }

    /**
     * parseFile reads in the fileName passed into a BufferedReader and analyzes the
     * specified positions in the Data files in order convert the String
     * representations of the data which contain number values the user wishes to
     * preform operations on.
     * 
     * @throws IOException
     */

    public void parseFile() throws IOException

    {

        BufferedReader Br = new BufferedReader(new FileReader(fileName));

        {
            String output;
            output = Br.readLine();
            output = Br.readLine();
            output = Br.readLine();
            parseParamHeader(output);
            output = Br.readLine();
            prepareDataCatalog();

            while (output != null)
            {
                String[] dataTokens = output.trim().split("\\s+");
                int STIDIndex = paramPostions.get("STID");
                // int STIDIndex = getIndexOf(STID);

                for (String myCoulumnName : paramPostions.keySet())
                {

                    if (!myCoulumnName.equals(STID))
                    {

                        int myCoulmnIndex = getIndexOf(myCoulumnName);

                        Observation postions = new Observation(Double.parseDouble(dataTokens[myCoulmnIndex]),
                                dataTokens[STIDIndex]);

                        ArrayList<Observation> paramData = dataCatalog.get(myCoulumnName);
                        paramData.add(postions);
                    }
                }

                output = Br.readLine();

            }

            Br.close();

        }
    }

    // Br.close();

    /**
     * The CalculateAllStatistics is used to calculate all statistics of the Data,
     * such as average, minimum stat value, maximum stat values and store these
     * values first in a treeMap and then move them into an EnumMap.
     * 
     * 
     * 
     * 
     */

    private void calculateAllStatistics()

    {

        Set<String> parameterIds = dataCatalog.keySet();
        TreeMap<String, Statistics> myTotal = new TreeMap<String, Statistics>();
        TreeMap<String, Statistics> myAverage = new TreeMap<String, Statistics>();
        TreeMap<String, Statistics> myMin = new TreeMap<String, Statistics>();
        TreeMap<String, Statistics> myMax = new TreeMap<String, Statistics>();
        for (String paramId : parameterIds)
        {
            double total = 0;
            int count = 0;
            double MIN_VALUE = Double.POSITIVE_INFINITY;
            double MAX_VALUE = Double.NEGATIVE_INFINITY;
            String MinStationID = "";
            String MaxStationID = "";
            double average = 0;
            ArrayList<Observation> data = dataCatalog.get(paramId);
            for (Observation obs : data)
            {

                if (obs.isValid())
                {
                    total += obs.getValue();

                    double value = obs.getValue();

                    if (MIN_VALUE > value)
                    {
                        MIN_VALUE = value;
                        MinStationID = obs.getStid();

                    }
                    if (MAX_VALUE < value)
                    {
                        MAX_VALUE = value;
                        MaxStationID = obs.getStid();

                    }

                }
                count++;

            }
            average = total / count;
            Statistics averageValues = new Statistics(average, MESONET, utcDateTime, numberOfStations,
                    StatsType.AVERAGE);
            Statistics minimumValues = new Statistics(MIN_VALUE, MinStationID, utcDateTime, numberOfStations,
                    StatsType.MINIMUM);
            Statistics maximumValues = new Statistics(MAX_VALUE, MaxStationID, utcDateTime, numberOfStations,
                    StatsType.MAXIMU);
            Statistics Total = new Statistics(total, MESONET, utcDateTime, numberOfStations, StatsType.TOTAL);

            myAverage.put(paramId, averageValues);
            myMin.put(paramId, minimumValues);
            myMax.put(paramId, maximumValues);
            myTotal.put(paramId, Total);

        }
        statistics.put(StatsType.AVERAGE, myAverage);
        statistics.put(StatsType.MINIMUM, myMin);
        statistics.put(StatsType.MAXIMU, myMax);
        statistics.put(StatsType.TOTAL, myTotal);

    }

    private void prepareDataCatalog()
    {
        dataCatalog = new HashMap<String, ArrayList<Observation>>();
        for (String name : paramPostions.keySet())
        {
            ArrayList<Observation> paramData = new ArrayList<Observation>();
            dataCatalog.put(name, paramData);

        }

    }

    /**
     * Calls the calculateAllStatistics method.
     */
    private void calculateStatistics()
    {
        calculateAllStatistics();

    }

    public Statistics getStatistics(StatsType type, String paramId)
    {
        return statistics.get(type).get(paramId);

    }

    /**
     * Formats a String in order to print: the year, month, day, hour,minute of the
     * desired fileCalled in the driver Formats a String in order to print: Maximum
     * Air Temperature at 1.5m, Minimum Air Temperature at 1.5m, and Average Air
     * Temperature at 1.5m. Formats a String in order to print: Maximum Air
     * Temperature at 9.0m, Minimum Air Temperature at 9.0m, and Average Air
     * Temperature at 9.0m.
     */

    public String toString()
    {

        String header = String.format(
                "========================================================\n" + "=== %4d-%02d-%02d %02d:%02d === \n"
                        + " ========================================================\n",
                utcDateTime.get(Calendar.YEAR), utcDateTime.get(Calendar.MONTH), utcDateTime.get(Calendar.DAY_OF_MONTH),
                utcDateTime.get(Calendar.HOUR), utcDateTime.get(Calendar.MINUTE));

        String myFormat1 = String.format(
                "Maximum Air Temperature[1.5m] = %.1f C at %s\n" + "Minimum Air Temperature[1.5m] = %.1f C at " + "%s\n"
                        + "Average Air Temperature[1.5m] = %.1f C at %s\n"
                        + "========================================================\n",
                statistics.get(StatsType.MAXIMU).get(TAIR).getValue(),
                statistics.get(StatsType.MAXIMU).get(TAIR).getStid(),
                statistics.get(StatsType.MINIMUM).get(TAIR).getValue(),
                statistics.get(StatsType.MINIMUM).get(TAIR).getStid(),
                statistics.get(StatsType.AVERAGE).get(TAIR).getValue(),
                statistics.get(StatsType.AVERAGE).get(TAIR).getStid());

        String myFormat2 = String.format(
                "========================================================\n"
                        + "Maximum Air Temperature[9.0m] = %.1f C at %s\n" + "Minimum Air Temperature[9.0m] = %.1f C at"
                        + " %s\n" + "Average Air Temeprature[9.0m] = %.1f C at %s\n"
                        + "========================================================\n",
                statistics.get(StatsType.MAXIMU).get(TA9M).getValue(),
                statistics.get(StatsType.MAXIMU).get(TA9M).getStid(),
                statistics.get(StatsType.MINIMUM).get(TA9M).getValue(),
                statistics.get(StatsType.MINIMUM).get(TA9M).getStid(),
                statistics.get(StatsType.AVERAGE).get(TA9M).getValue(),
                statistics.get(StatsType.AVERAGE).get(TA9M).getStid());

        String myFormat3 = String.format(
                "Maximum Solar Radiation[1.5m] = %.1f W/m^2 at %s\n"
                        + "Minimum Solar Radiation[1.5m] = %.1f W/m^2 at %s\n"
                        + "Average Solar Radiation[1.5m] = %.1f W/m^2 at %s\n",
                statistics.get(StatsType.MAXIMU).get(SRAD).getValue(),
                statistics.get(StatsType.MAXIMU).get(SRAD).getStid(),
                statistics.get(StatsType.MINIMUM).get(SRAD).getValue(),
                statistics.get(StatsType.MINIMUM).get(SRAD).getStid(),
                statistics.get(StatsType.AVERAGE).get(SRAD).getValue(),
                statistics.get(StatsType.AVERAGE).get(SRAD).getStid());

        // System.out.println(header + myFormat1 + myFormat2 + myFormat3);

        return header + myFormat1 + myFormat2 + myFormat3;

    }

}
