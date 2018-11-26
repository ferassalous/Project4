
/**
 * @author Feras salous
 * Interface class which holds methods for GregorianCalender to compare if Dates are newer than, older than, or the 
 * same. 
 */
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

public interface DateTimeComparble
{

    abstract boolean newerThan(GregorianCalendar inDateTimeUTC);

    abstract boolean olderThan(GregorianCalendar inDateTimeUTC);

    abstract boolean sameAs(GregorianCalendar inDateTimeUTC);

    abstract boolean newerThan(ZonedDateTime inDateTimeUTC);

    abstract boolean olderThan(ZonedDateTime inDateTimeUTC);

    abstract boolean sameAs(ZonedDateTime inDateTimeUTC);

}
