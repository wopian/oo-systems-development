import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * Class than defines the manager and what they can do in the system
 *
 * @author Alex Costa
 * @version 1.00
 */
public class Manager extends User
{
    /**
     * Constructor for objects of class Manager
     * @param lastUserID The id of the last user to create the new ID
     * @param newName The name of the new user
     * @param newPwd The password for the new user
     * @param newEmail The email of the new user
     */
    public Manager(int lastUserID, String newName, String newPwd, String newEmail)
    {
        super(lastUserID, newName, newPwd, newEmail);
    }
    
    /**
     * Method that allows the user to create a new event in the system
     * @param startDate The start date of the event
     * @param endDate The end date of the event
     * @param name The name of the event
     */
    public void addEvent(LocalDate startDate, LocalDate endDate, String name)
    {
        TicketSystem.events.add(new Event(name, startDate, endDate, events.size()));
    }
    
    //TODO: implement the enum for event statuses and finish the implementation
    /**
     * Allows the manager to reschedule an event in the system
     * @param event The event that the manager is working on
     * @param startDate The new start date for the event
     * @param endDate The new end date for the event
     */
    public void rescheduleEvent(Event event, LocalDate startDate, LocalDate endDate)
    {
        if(startDate != null)
        {
            event.setStartDate(startDate);
        }
        if(endDate != null)
        {
            event.setEndDate(endDate);
        }
    }
    
    //TODO: Implement the enum for event statuses and finish the implementation
    /**
     * A method that allows the manager to cancel an event in the system
     * @param event The event that the manager is working on
     */
    public void cancelEvent(Event event){
        event.setStatus(Statuses.Cancelled);
    }
    
    /**
     * A method that allows the manager to add a show to an event in the system
     * @param event The event for which the manager is adding a show
     * @param start The start time and date of the show
     * @param end The end time and date of the show
     * @param MSPC The maximum sets per customer value for this show
     */
    public void addShow(Event event, LocalDateTime start, LocalDateTime end, int MSPC){
        event.addShow(start, end, MSPC);
    }
    
    /**
     * A method that allows the manager to reschedule a show from the system
     * @param show The show that the manager is working on
     * @param newStart The new start date and time of the show
     * @param newEnd The new end date and time of the show
     */
    public void rescheduleShow(Show show, LocalDateTime newStart, LocalDateTime newEnd)
    {
        if(newStart != null)
        {
            show.setStart(newStart);
        }
        if(newEnd != null)
        {
            show.setEnd(newEnd);
        }
    }
    
    /**
     * A method that allows the manager to cancel a show on the system
     * @param show The show that the user is working on
     */
    public void cancelShow(Show show)
    {
        show.setStatus(Statuses.Cancelled);
    }
    
    /**
     * A method that allows the manager to change the MSPC of a show
     * @param show The show that the user is working on
     * @param newMSPC The new Maximum Seats Per Customer value for the show
     */
    public void changeMSPC(Show show, int newMSPC)
    {
        show.setMSPC(newMSPC);
    }
    
    //TODO: implement the Promotion constructor and write the method
    /**
     * A method that allows the manager to create a new Promotion on the system
     */
    public void addPromotion(){}
    
    /**
     * A method that allows the manager to assign a certain promotion to a range of seats
     * @param seats An array of integers containing the IDs of the seats to which the promotion
     * is being assigned
     * @param promotionID
     */
    public void assignPromotions(int[] seats, int promotionID)
    {
        for(int i = 0; i < seats.length; i++)
        {
            TicketSystem.seats.stream().filter(seat -> seat.getID() == seats[i])
                .forEach(seat -> seat.setPromotion(promotionID));
        }
    }
    
    //TODO: Implement the Promotion class to know what can be changes
    // and then implement the method please!
    /**
     * A method that allows the manager to change the details of a promotion
     * @param promotion The promotion that the manager wants to change
     */
    public void modifyPromotion(Promotion promotion){}
    
    /**
     * A method that allows the manager to remove a promotion from the system.
     * @param promoName The name of the promotion that the manager wants to delete
     */
    public void deletePromotion(String promoName)
    {
        int index = TicketSystem.promotions.stream().filter(promo -> promo.getName().equals(promoName))
        .forEach(promo -> promo.getID());
        
        TicketSystem.promotions.remove(index);
    }
    
    /**
     * Method that allows the manager to add a agent to the system
     * @param lastUserID The last ID assigned to generate the one for the new user
     * @param newName The name of the new user
     * @param newPwd The password of the new user
     * @param newEmail The email of the new user
     * @param newCom The commission for the new agent
     * @param newDate The start date of the contract with the new agent
     * @param dayDuration The duration of the contract in days
     */
    public void addContract(int lastUserID, String newName, String newPwd, String newEmail, float newCom, LocalDate newDate, long dayDuration){
        TicketSystem.users.add(
            new Agent(lastUserID, newName, newPwd, newEmail, newCom, newDate, dayDuration)
            );
    }
    
    //TODO: Figure an easy way that doesn't require 1000 lines of code to
    //change only certain infos if we're not going to change all of them
    // Also, stop being lazy and think a little bit
    /**
     * A method that allows the manager to change the details of an agent
     * @param agent The agent which details need to be changed
     */
    public void changeContract(Agent agent){}
    
    /**
     * Method that allows the manager to cancel a contract with an agent
     * and delete their access to the platform instantaniously.
     * @param agentName The name of the agent whose contract is being terminated
     */
    public void cancelContract(String agentName)
    {
        int index = TicketSystem.users.stream().filter(user -> user.getName().equals(agentName))
        .forEach(user -> user.getID());
        TicketSystem.users.remove(index);
    }
    
    /**
     * Method that allows the manager to renew the contract of one agent by 
     * a certain amount of days
     * @param agent The agent to whom the manager is renewing the contract
     * @param days The number of days the contract is to be renewed for
     */
    public void renewContract(Agent agent, long days)
    {
        agent.renewFor(days);
    }
}
