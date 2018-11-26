import org.junit.Assert;
import org.junit.Test;

/**
 * Class the Test Observation Class methods
 * 
 * @author Ferassalous
 * @version 2018-10-28
 */
public class ObservationTest
{

    /**
     * Test method for {@link Observation#isValid()}.
     */
    @Test
    public void testIsValid()
    {
        Observation myValue = new Observation(-999, "Feras");
        Assert.assertEquals(false, myValue.isValid());
        Observation myTrueValue = new Observation(50, "Feras");
        Assert.assertEquals(true, myTrueValue.isValid());

    }

    /**
     * Test method for {@link Observation#getValue()}.
     */
    @Test
    public void testGetValue()
    {
        Observation myValue = new Observation(3.0, "Feras");
        Assert.assertEquals("The value is not correct", 3.0, myValue.getValue(), 0.001);

    }

    /**
     * Test method for {@link Observation#getStid()}.
     */
    @Test
    public void testGetStid()
    {
        Observation myString = new Observation(50, "Feras");
        Assert.assertEquals("Feras", myString.getStid());

    }

    /**
     * Test method for {@link Observation#toString()}.
     */
    @Test
    public void testToString()
    {
        Observation myString = new Observation(50, "Feras");
        String expected = "50.00 C At Feras";
        Assert.assertEquals(expected, myString.toString());

    }

}
