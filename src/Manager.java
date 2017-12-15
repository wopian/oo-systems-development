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
        super.userType = UserTypes.Manager;
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
     * A method that allows the manager to assign a certain promotion to a range of seats
     * @param seats An array of integers containing the IDs of the seats to which the promotion
     * is being assigned
     * @param promotionID The ID of the promotion we wish to assign to the seats
     * @param show The show to whose seats we are assigning a promotion
     */
    public void assignPromotion(int promoID, Show show, int[] seats)
    {
      for(int seat : seats)
      {
        show.assignPromotion(seat, promoID);
      }
    }

    //TODO: Figure an easy way that doesn't require 1000 lines of code to
    //change only certain infos if we're not going to change all of them
    // Also, stop being lazy and think a little bit
    /**
     * A method that allows the manager to change the details of an agent
     * @param agent The agent which details need to be changed
     * @param newCom The new commisison for the agent
     * @param newDate The new start date for the agent's contract
     */
    public void changeContract(Agent agent, float newCom, LocalDate newDate)
    {
      if(newCom != -1)
      {
        agent.setCommission(newCom);
      }
      if(newDate != null)
      {
        agent.setNewStart(newDate);
      }
    }
}
