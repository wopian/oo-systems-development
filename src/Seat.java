
/**
 * Class storing the data and behaviour of a seat in the
 * Online Ticket Sale system
 *
 * @author Alex Costa
 * @version 1.00
 */
public class Seat
{
    // instance variables
    private int seatNumber;
    private SeatStatuses status;
    private int promotionID;

    /**
     * Constructor for objects of class Seat
     * @param number The number of the seat (should correspond with its index in the
     * show's seats ArrayList)
     */
    public Seat(int number)
    {
        seatNumber = number;
        status = SeatStatuses.Unheld;
    }

    /**
     * A method that allows other methods to retrieve the number of the seat
     * @return The number of the seat
     */
    public int getNumber()
    {
        return seatNumber;
    }

    /**
     * Method that allows other classes to retrieve the status of the seat
     * @return The status of the seat (held, unheld, reserved)
     */
    public SeatStatuses getStatus()
    {
        return status;
    }

    /**
     * Method that allows to change the status of a seat
     * @param newStatus Contains the new status for the seat
     */
    public void setStatus(SeatStatuses newStatus)
    {
        status = newStatus;
    }

    /**
     * Method that allows to assign a promotion to a seat
     * @param promoID The ID of the promotion being assigned to the seat
     */
    public void setPromotion(int promoID)
    {
        promotionID = promoID;
    }

    public int getPromoID()
    {
      return promotionID;
    }
}
