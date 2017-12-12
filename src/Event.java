import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * Class that describes what an event is and what it does
 *
 * @author Alex Costa
 * @version 1.00
 */
public class Event
{
    // instance variables - replace the example below with your own
    private ArrayList<Show> shows;
    private LocalDate startDate;
    private LocalDate endDate;
    private String eventName;
    private int eventID;
    private int status;

    /**
     * Constructor for objects of class Event
     * @param newName The name for the new event
     * @param start The start date for the new event
     * @param end The end date for the new event
     * @param lastEventID The ID of the last event in order to create to add the new event
     */
    public Event(String newName, LocalDate start, LocalDate end, int lastEventID)
    {
        eventName = newName;
        startDate = start;
        endDate = end;
        eventID = (lastEventID + 1);
        status = statuses.Confirmed;
    }
    
    /**
    * Method that creates a new show for this event
    * @param start The start time and date of the show
    * @param end The end time and date of the show
    * @param MSPC The max seats per customer value for the show
    */
    // TODO: figure out how to add the seats to the show
    public void addShow(LocalDateTime start, LocalDateTime end, int MSPC)
    {
      shows.add(new show(start, end, MSPC, shows.size()));
    }
}
