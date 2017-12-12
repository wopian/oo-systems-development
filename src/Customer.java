import java.util.ArrayList;
/**
 * Class storing all  the customer's information and what they can do in the
 * system.
 * 
 * To implement: Purchase Ticket method
 *
 * @author Alex Costa
 * @version 1.00
 */
public class Customer extends User
{
    // instance variables - replace the example below with your own
    private String address;
    private int agentID;
    private ArrayList<Ticket> tickets;

    /**
     * Constructor for objects of class Customer
     * @param lastUserID The id of the last user to create the new ID
     * @param newName The name of the new user
     * @param newPwd The password for the new user
     * @param newEmail The email address of the new user
     * @param newAdd The address of the new user
     * @param agent The ID of the agent able to buy tickets for this customer. Null if no agent
     */
    public Customer(int lastUserID, String newName, String newPwd, String newEmail, String newAdd, Integer agent)
    {
        super(lastUserID, newName, newPwd, newEmail);
        address = newAdd;
        if(agent != null){
            agentID = agent;
        }
        else{
            agentID = 0;
        }
        tickets = new ArrayList<Ticket>();
    }
    
    public void viewTickets()
    {
        tickets.forEach(ticket -> ticket.getDetails());
    }
    
    public void viewDetails()
    {
        System.out.println(super.name);
        System.out.println(super.email);
        System.out.println(address);
        System.out.println(agentID);
    }
    
    
}
