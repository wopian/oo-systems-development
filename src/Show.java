import java.util.ArrayList;
import java.time.LocalDateTime;

/**
* Class that contains all the data and methods for the a show
* 
* @author Alex Costa
* @version 1.00
*/

class Show
{
  //instance variables
  private LocalDateTime start;
  private LocalDateTime end;
  private int showID;
  private Statuses status;
  private ArrayList<Seat> seats;
  private int MaxSeatsPerCustomer;
  
  
  /**
  * Contructor for Show Objects
  * @param newStart The start date and time for the new show
  * @param newEnd The end date and time for the new show
  * @param lastShowID The ID of the last show created
  * @param MSPC The set max seats per customer for this show
  */
  public Show(LocalDateTime newStart, LocalDateTime newEnd, int lastShowID, int MSPC)
  {
    start = newStart;
    end = newEnd;
    showID = (lastShowID + 1);
    status = Statuses.Confirmed;
    MaxSeatsPerCustomer = MSPC;
    seats = new ArrayList<>();
  }
  
  /**
   * Method that allows to set a new start date and time to a show
   * @param newStart The new start date and time for the show
   */
  public void setStart(LocalDateTime newStart)
  {
      start = newStart;
  }
  
  /**
   * Method that allows to set a new end date and time to a show
   * @param newEnd The new end date and time for the show
   */
  public void setEnd(LocalDateTime newEnd)
  {
      end = newEnd;
  }
  
  /**
   * Method that allows to set a new status to the show
   * @param newStatus The new status for the show
   */
  public void setStatus(Statuses newStatus)
  {
      status = newStatus;
  }
  
  /**
   * Method that allows to assign a new MSPC for the show
   * @param newMSPC The new max seats per customer value for the show
   */
  public void setMSPC(int newMSPC)
  {
      MaxSeatsPerCustomer = newMSPC;
  }
  
  /**
   * Method that allows other classes to retrieve the value of the start date
   * @return The start date and time for the show
   */
  public LocalDateTime getStart()
  {
      return start;
  }
  
  /**
   * Method that allows other classes to retrieve the end date and time of the show
   * @return The end date and time for the show
   */
  public LocalDateTime getEnd()
  {
      return end;
  }
  
  /**
   * Method that allows to print the details of the show.
   * Prints all the details for the show in the console
   */
  public void getDetails()
  {
  }
}
