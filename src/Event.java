import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        shows = new ArrayList<>();
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
        shows.add(new Show(start, end, MSPC, shows.size()));
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
     * Method that allows other classes to get the name of the event
     * @return The name of the event
     */
    public String getName()
    {
        return eventName;
    }

    /**
     * Method that allows other classes to get the ID of the event
     * @return The ID of the event
     */
    public int getID()
    {
        return eventID;
    }

    /**
    * Method that allows other classes to get the status of the show
    * @return The status of the show
    */
    public Statuses getStatus()
    {
      return status;
    }

    /**
    * Method that allows to change the start date of the event
    * @param newDate The new start date for the event
    */
    public void setStartDate(LocalDate newDate)
    {
      startDate = newDate;
      setStatus(Statuses.Rescheduled);
    }

    /**
    * Method that allows to change the end date of the event
    * @
    */
    public void setEndDate(LocalDate newDate)
    {
      endDate = newDate;
      setStatus(Statuses.Rescheduled);
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

    /**
     * Method that allows other classes to get a Show object from this class
     * @param showID The ID of the show to retrieve from the ArrayList
     * @return A reference to an existing show object
     */
    public Show getShow(int showID)
    {
        return shows.get(showID);
    }

    /**Method that allows other classes to get a Show object form this event
    * @param showStart The date and time of the start of the show
    * @return The reference to the show object
    */
    public Show getShow(LocalDateTime showStart)
    {
      Show returnShow = new Show();
      for(Show show : shows)
      {
        if(show.getStart().equals(showStart))
        {
          returnShow = show;
        }
        else
        {
          System.out.println("That show does not exist in the system.");
          returnShow = null;
        }
      }
      return returnShow;
    }

    /**
    * Method that prints the details of the show in the system
    */
    public void viewEvent()
    {
      DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy")
      System.out.println("Name: " + eventName);
      System.out.println("Start date: " + startDate.format(format));
      System.out.println("End date: " + endDate.format(format));
      System.out.println("Status: " + status.toString());
      System.out.println("Number of shows: " + shows.stream().count().toString());
      System.out.println();
    }

    public void viewShows()
    {
      for(Show show : shows)
      {
        if(show.getStatus() != Statuses.Cancelled)
        {
          show.getDetails();
        }
      }
    }
}
