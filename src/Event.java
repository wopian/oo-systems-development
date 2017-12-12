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
    private Statuses status;

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
        status = Statuses.Confirmed;
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
      TicketSystem.shows.add(new Show(start, end, MSPC, shows.size()));
    }
    
    /**
    * Method that allows to retrieve all the details for the shows of an event
    */
    public void getShows()
    {
      shows.stream().forEach(show -> show.getDetails());
    }
  
    /**
    * Method that allows to change the name of an event
    * @param newName The new name for the event
    */
    public void setName(String newName)
    {
      eventName = newName;
    }
  
    /**
    * Method that allows to change the start date of the event
    * @param newDate The new start date for the event
    */
    public void setStartDate(LocalDate newDate)
    {
      startDate = newDate;
    }
  
    /**
    * Method that allows to change the end date of the event
    * @
    */
    public void setEndDate(LocalDate newDate)
    {
      endDate = newDate;
    }
  
    /**
    * Method that allows to set a new status to the event
    * @param newStatus The new status of the event
    */
    public void setStatus(Statuses newStatus)
    {
      status = newStatus;
    }
  
    /**
    * Method that allows to get the shows for a specific date
    * @param showDate The date for which we are looking if the event has shows
    */
    public void getShows(LocalDateTime showDate)
    {
      shows.stream().filter(show -> show.getStart().getYear() == showDate.getYear() && show.getStart().getDayOfYear() == showDate.getDayOfYear())
        .forEach(show -> show.getDetails());
    }
  
    /**
    * Method that displays the details of all shows that are in a range
    * @param startDate The date from which to start to look for shows (exclusive)
    * @param endDate The date from which to end looking for shows (exclusive)
    */
    public void getShows(LocalDateTime startDate, LocalDateTime endDate)
    {
      shows.stream().filter(show -> show.getStart().isAfter(startDate) && show.getEnd().isBefore(endDate))
        .forEach(show -> show.getDetails());
    }
}
