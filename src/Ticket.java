import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private DateTimeFormatter date = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    /**
     * Constructor for objects of class Ticket
     * @param lastTicketID The index of the last ticket sold to create the one for this ticket
     * @param show The ID of the show this ticket is for
     * @param event The ID of the event this ticket is for
     * @param seat The number of the seat this ticket is for
     * @param money The amount of money paid for the ticket
     * @param customer The name of the customer that bought the ticket
     */
    public Ticket(int lastTicketID, int show, int event, int seat, String customer)
    {
        ticketID = (lastTicketID + 1);
        showID = show;
        eventID = event;
        seatNumber = seat;
        int promoID = TicketSystem.events.get(eventID).getShow(eventID).getSeat(seat).getPromoID();
        price = TicketSystem.promotions.get(promoID).getAdultPrice();
        customerName = customer;
    }

    /**
     * Method that allows to print the details of the ticket
     */
    public void printTicket()
    {
        System.out.println("Event: " + TicketSystem.events.get(eventID).getName());
        System.out.println("Start: " + TicketSystem.events.get(eventID).getShow(showID).getStart().format(date));
        System.out.println("End: " + TicketSystem.events.get(eventID).getShow(showID).getEnd().format(date));
        System.out.println("Seat: " + seatNumber);
        System.out.println("Price: " + price.toString());
    }

    /**
     * Method that allows other classes to get the name of the event this ticket belongs to
     * @return The name of the event that this ticket is for
     */
    public String getEventName()
    {
        return TicketSystem.events.get(eventID).getName();
    }

    public LocalDate getDate()
    {
        return TicketSystem.events.get(eventID).getShow(showID).getStart().toLocalDate();
    }
}
