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
    status = Statuses.confirmed;
    MaxSeatsPerCustomer = MSPC;
  }
}
