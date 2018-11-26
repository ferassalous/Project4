/**
 * This is an Abstract Class which holds an isValid method to check if values are valid in the Data Provided.
 * @author Ferassalous
 * @version 2018-10-28
 *
 */
public abstract class AbstractObservation
{
    protected boolean valid; // UML SAID PRIVATE BUT WEIRD ERROR

    public AbstractObservation()
    {
        // This is abstract
    }

    protected abstract boolean isValid();

}
