import java.time.LocalDate;
import java.util.HashMap;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
/**
 * Class storing all the information related to an agent of the Ticket System.
 * This class also specifies all the actions that an agent can do and how they
 * are performed.
 *
 * @author Alex Costa
 * @version 1.00
 */
public class Agent extends User
{
    // instance variables - hold information about the seats that are allocated
    //to the agent as well as other contract information
    private float commission;
    private LocalDate startDate;
    private LocalDate endDate;
    private HashMap<String, Integer> customersManaged;
    private ArrayList<Ticket> ticketsSold;
    

    /**
     * Constructor for objects of class Agent
     * @param lastUserID The ID of the last user to create the ID for this user
     * @param newName The name of the new user
     * @param newPwd The password for the new user
     * @param newEmail The e-mail of the new user
     * @param newCom The commission for the new agent
     * @param newDate The start date of the agent's contract
     * @param dayDuration The duration of the contract counted as days
     */
    public Agent(int lastUserID, String newName, String newPwd, String newEmail, float newCom, LocalDate newDate, long dayDuration)
    {
        super(lastUserID, newName, newPwd, newEmail);
        commission = newCom;
        startDate = newDate;
        endDate = startDate.plusDays(dayDuration);
        customersManaged = new HashMap<String,Integer>();
        ticketsSold = new ArrayList<Ticket>();
    }
    
    /**
     * A method to create new customers managed by the currently logged in Agent
     * @param users The ArrayList containing all the users of the system, in order to add the new customer
     * @param lastUserID The ID of the last created user to create the ID for the new user
     * @param newName The name of the new user
     * @param newPwd The password for the new user
     * @param newEmail The email of the new user
     */
    public void createCustomer(int lastUserID, String newName, String newPwd, String newEmail, String newAdd){
        TicketSystem.users.add(new Customer(lastUserID, newName, newPwd, newEmail, newAdd, super.userID));
        customersManaged.put(newName, (lastUserID + 1));
    }
    
    /**
     * A method that allows the agent to see the tickets that they have sold for a specified event
     * @param eventName The name of the event that the agent is looking for
     */
    public void viewTickets(String eventName){
        ticketsSold.Stream().filter(ticket -> ticket.getEventName().equals(eventName))
        .forEach(ticket -> ticket.printDetails());
        System.out.println("Total: " +
        ticketsSold.Stream().filter(ticket -> ticket.getEventName().equals(eventName))
        .count() + " tickets");
    }
    
    /**
     * A method that allows the agent to see the tickets that they have sold in a date range
     * @param startRange The date from which to look for tickets (exclusive)
     * @param endRange The date from which to look for tickets (exclusive)
     */
    public void viewTickets(LocalDate startRange, LocalDate endRange){
        ticketsSold.stream().filter(ticket -> ticket.getDate().isAfter(startRange) && ticket.getDate().isBefore(endRange))
        .forEach(ticket -> ticket.printDetails());
        System.out.println("Total " +
        ticketsSold.stream().filter(ticket -> ticket.getDate().isAfter(startRange) && ticket.getDate().isBefore(endRange))
        .count() + " tickets between " + startRange.format(ISO_LOCAL_DATE) + " and " +
        endRange.format(ISO_LOCAL_DATE));
    }
    
    /**
     * Method allowing the agent to see the details of a customer
     * @param custName The name of the customer
     */
    public void viewCustomer(String custName){
        int index = customersManaged.get(custName);
        Customer customer = TicketSystem.users.get(index);
        customer.viewDetails();
    }
    
    /**
     * Method allowing the agent to modify certain aspects of the customer's data
     * @param custName The name of the customer to look up
     * @param newName The new name of the customer, can be null
     * @param newAdd The new address of the customer, can be null
     * @param newEmail The new email of the customer, can be null
     */
    public void modifyCustomer(String custName, String newName, String newAdd, String newEmail)
    {
        int index = customersManaged.getOrDefault(custName, -1);
        if (index != -1)
        {
            Customer customer = TicketSystem.users.get(index);
            
            if(newName != null){
                customer.setName(newName);
                customersManaged.remove(custName);
                customersManaged.put(newName, index);
            }
            if(newAdd != null)
            {
                customer.setAddress(newAdd);
            }
            if(newEmail != null)
            {
                customer.setEmail(newEmail);
            }
        }
    }
    
    public void renewFor(long days){
        endDate = endDate.plusDays(days);
    }
}