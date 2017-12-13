import java.math.BigDecimal;
/**
 * Write a description of class Ticket here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Ticket
{
    // instance variables - replace the example below with your own
    private int ticketID;
    private int showID;
    private int eventID;
    private int seatNumber;
    private BigDecimal price;
    private String customerName;

    /**
     * Constructor for objects of class Ticket
     * @param lastTicketID The index of the last ticket sold to create the one for this ticket
     * @param show The ID of the show this ticket is for
     * @param event The ID of the event this ticket is for
     * @param seat The number of the seat this ticket is for
     * @param money The amount of money paid for the ticket
     * @param customer The name of the customer that bought the ticket
     */
    public Ticket(int lastTicketID, int show, int event, int seat, BigDecimal money, String customer)
    {
        ticketID = (lastTicketID + 1);
        showID = show;
        eventID = event;
        seatNumber = seat;
        price = money;
        customerName = customer;
    }

    public void printTicket()
    {
        System.out.println("Event: " + TicketSystem.events.get(eventID).getName());
        System.out.println("Start: " + TicketSystem.events.get(eventID).getShow(showID).getStart().toString());
        System.out.println("End: " + TicketSystem.events.get(eventID).getShow(showID).getEnd().toString());
        System.out.println("Seat: " + seatNumber);
    }
}
