import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DatetimeFormatter;

/**
* Class that contains all the data and methods for the a show
* TODO: Find a way to display the seats that aren't booked yet efficiently
* Display the prices for the different seats efficiently (aka show the range of seat with
* its price in a single Console line)
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
  //So that the compiler leaves me alone
  public Show(){}
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

  public Seat getSeat(int seatNumber)
  {
    return seats.get(seatNumber);
  }
  /**
   * Method that allows other classes to retrieve the end date and time of the show
   * @return The end date and time for the show
   */
  public LocalDateTime getEnd()
  {
      return end;
  }

  public Statuses getStatus()
  {
    return status;
  }

  /**
   * Method that allows to print the details of the show.
   * Prints all the details for the show in the console
   */
  public void getDetails()
  {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm a");
      System.out.println("Start Date and Time: " + start.format(format);
      System.out.println("End Date and Time: " + end.format(format);
      System.out.println("Maximum Seats that a customer can buy: " + MaxSeatsPerCustomer);
      System.out.println("Status of the show: " + status.toString());
      System.out.println();
  }

  /**
   * Method that allows to hold a seat for a show
   * @param seatNumber The number of the seat
   */
  public void holdSeat(int seatNumber)
  {
      Seat seat = seats.get(seatNumber);
      seat.setStatus(SeatStatuses.Held);
  }

  /**
   * Method that allows to unhold a seat for a show
   * @param seatNumber The number of the seat
   */
  public void unholdSeat(int seatNumber)
  {
      Seat seat = seats.get(seatNumber);
      seat.setStatus(SeatStatuses.Unheld);
  }

  /**
   * Method that allows to reserve a seat for a show
   * @param seatNumber The number of the seat
   */
  public void reserveSeat(int seatNumber)
  {
      Seat seat = seats.get(seatNumber);
      seat.setStatus(SeatStatuses.Reserved);
  }

  /**
  * Method that assigns a promotion to a seat
  * @param seatNum The number of the seat we are assigning the promotion to
  * @param promotionID The ID of the promotion we are assigning to the seat
  */
  public void assignPromotion(int seatNum, int promotionID)
  {
    seats.get(seatNum).setPromotion(promotionID);
  }
}
