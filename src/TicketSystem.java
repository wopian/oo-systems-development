import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

/**
 * Class containing all the information and behaviour for the TicketSystem
 *
 * @author Alex Costa
 * @version 1.00
 */
public class TicketSystem
{
    // instance variables - replace the example below with your own
    public static ArrayList<Event> events = new ArrayList<>();
    public static ArrayList<Promotion> promotions = new ArrayList<>();
    public static ArrayList<Discount> discounts = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();
    private int lastPromoID;
    private int lastUserID;
    private int lastEventID;
    private int lastDiscountID;
    private UserTypes currentUserType;
    private int userID;

    /**
     * Constructor for objects of class TicketSystem
     */
    public TicketSystem()
    {
      lastPromoID = promotions.size() - 1;
      lastUserID = users.size() - 1;
      lastEventID = events.size() - 1;
      lastDiscountID = discounts.size() - 1;
    }

    /**
    * Method that allows other methods to retrieve an Event from
    * the events ArrayList based on its name
    * @param eventName The name of the event that we are looking for
    * @return The event object. If no event corresponds to eventName,
    * null will be sent out as an answer
    */
    public Event findEvent(String eventName)
    {
      int index = -1;
      for(Event event : events)
      {
        if(event.getName().equals(eventName))
        {
          index = event.getID();
        }
      }

      if(index == -1)
      {
        System.out.println("There is no event named " + eventName + " in the system.");
        System.out.println();
        return null;
      }
      else
      {
        return events.get(index);
      }
    }

    /**Method that allows other methods to find a particular show
    * @param eventName The name of the event that is being looked for
    * @param showStart The date and time of the start of the show
    * in the dd.MM.yyyy HH:mm format
    * @return A reference to the show object that is being worked on
    */
    public Show findShow(String eventName, String showStart)
    {
      if(findEvent(eventName) != null)
      {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime startDate = LocalDateTime.parse(showStart, format);
        Show show = findEvent(eventName).getShow(startDate);
        return show;
      }
      else
      {
          return null;
      }
    }

    /**
     * Method that allows to find a promotion based on its name
     * @param promoName The name of the promotion that the user is looking for
     * @return The instance of the Promotion class that corresponds to that promotion
     */
    public Promotion findPromotion(String promoName)
    {
      int index = -1;
      for(Promotion promo : promotions)
      {
        if(promo.getName().equals(promoName))
        {
          index = promotions.indexOf(promo);
        }
      }

      if(index == -1)
      {
        System.out.println("There is no promotion named " + promoName + " in the system.");
        System.out.println();
        return null;
      }
      else
      {
        return promotions.get(index);
      }
    }

    /**
     * Method that allows other methods to find a user by their name
     * @param userName The name of the user that is being looked for
     * @return The user object that corresponds to that user
     */
    public User findUser(String userName)
    {
        int index = -1;
        for(User user : users)
        {
            if(user.getName().equals(userName))
            {
                index = users.indexOf(user);
            }
        }

        if(index == -1)
        {
            return null;
        }
        else
        {
            return users.get(index);
        }
    }

    public Discount findDiscount(String discountName)
    {
        int index = -1;
        for(Discount discount : discounts)
        {
            if(discount.getName().equals(discountName))
            {
                index = discounts.indexOf(discount);
            }
        }

        if(index == -1)
        {
            System.out.println("There is no discount tarif with the name " + discountName + " in the system");
            System.out.println();
            return null;
        }
        else
        {
            return discounts.get(index);
        }
    }

    /**
     * Method that checks if the logged in user is a manager
     * @return a boolean that says if the manager is logged in or not
     */
    public boolean isManager()
    {
        if(currentUserType.equals(UserTypes.Manager))
        {
            return true;
        }
        else
        {
            System.out.println("You are not authorized to perform this operation.");
            System.out.println("You have to be a manager to access this function.");
            System.out.println();
            return false;
        }
    }

    public boolean isAgent()
    {
        if(currentUserType.equals(UserTypes.Agent))
        {
            return true;
        }
        else
        {
            System.out.println("You are not authorized to perform this operation.");
            System.out.println("You have to be an agent to access this function.");
            System.out.println();
            return false;
        }
    }

    public boolean isCustomer()
    {
        if(currentUserType.equals(UserTypes.Customer))
        {
            return true;
        }
        else
        {
            System.out.println("You are not authorized to perform this operation.");
            System.out.println("You have to be a customer to access this function.");
            System.out.println();
            return false;
        }
    }

    /**
     * Method that allows the manager to delete an agent from the system,
     * which in practicality means cancelling their contract
     * @param agentName The name of the agent
     */
    public void cancelContract(String agentName)
    {
        if(isManager())
        {
            User possibleAgent = findUser(agentName);
            if(possibleAgent != null && possibleAgent.getType().equals(UserTypes.Agent))
            {
                users.remove(users.indexOf(possibleAgent));
                System.out.println("The agent " + agentName + " has been removed from the system");
                System.out.println();
            }
            else
            {
                System.out.println("There is no agent named " + agentName + " in the system.");
                System.out.println();
            }
        }
    }


    /**
     * Method that allows a logged in Manager to create a new event in the system
     * @param startDate The start date of the event in format dd.MM.yyyy
     * @param endDate The end date of the event in format dd.MM.yyyy
     * @param name The name of the event
     */
    public void addEvent(String startDate, String endDate, String name)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        if(isManager())
        {
            events.add(new Event(name, LocalDate.parse(startDate, format), LocalDate.parse(endDate, format), lastEventID));
            lastEventID++;
        }
    }

    /**
     * Method that allows a logged in Manager to reschedule a previously created event
     * @param eventName The event that the manager wants to change
     * @param startDate The new start date for the event (if that date is not to be changed
     * then send a null) in format dd.MM.yyyy
     * @param endDate The new end date for the event (if that date is not to be changed
     * then send a null) in format dd.MM.yyyy
     */
    public void rescheduleEvent(String eventName, String startDate, String endDate)
    {
      DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        if(isManager())
        {
            if(findEvent(eventName) != null)
            {
                Manager manager = (Manager) users.get(userID);
                manager.rescheduleEvent(findEvent(eventName), LocalDate.parse(startDate, format), LocalDate.parse(endDate, format));
            }
        }
    }

    /**
    * Method that allows a logged in manager to cancel an event
    * @param eventName The name of the event that the manager wants to cancel
    */
    public void cancelEvent(String eventName)
    {
      if(isManager())
      {
        if(findEvent(eventName) != null)
        {
          findEvent(eventName).setStatus(Statuses.Cancelled);
        }
      }
    }

    /**
    * Method that allows the manager to add a show to an event in the system
    * @param event The event for which the manager is adding the show
    * @param start The start time and date for the show in format dd.MM.yyyy HH:mm
    * @param end The end time and date for the show in format dd.MM.yyyy HH:mm
    * @param mspc The maximum seats per customer value for this show
    */
    public void addShow(String eventName, String start, String end, int mspc)
    {
      DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
      if(isManager())
      {
        if(findEvent(eventName) != null)
        {
          findEvent(eventName).addShow(LocalDateTime.parse(start, format), LocalDateTime.parse(end, format), mspc);
        }
      }
    }

    /**
    * Method that allows the manager to reschedule a show
    * @param eventName The name of the show's event
    * @param start The current start time and date of the show
    * @param newStart The new start date and time for the show (can be null if there is no change)
    * in the pattern dd.MM.yyyy HH:mm
    * @param newEnd The end date and time for the show (can be null if there is no change)
    * in the format dd.MM.yyyy HH:mm
    */
    public void rescheduleShow(String eventName, String start, String newStart, String newEnd)
    {
      DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
      if(isManager())
      {
        Show show = findShow(eventName, start);
        if(show != null)
        {
          Manager manager = (Manager) users.get(userID);
          manager.rescheduleShow(show, LocalDateTime.parse(newStart, format), LocalDateTime.parse(newEnd, format));
        }
      }
    }

    /**
    * Method that allows the manager to cancel a show on the system
    * @param eventName The name of the show's event
    * @param start The start date and time of the show in the format dd.MM.yyyy HH:mm
    */
    public void cancelShow(String eventName, String start)
    {
      if(isManager())
      {
        Show show = findShow(eventName, start);
        if(show !=null)
        {
          show.setStatus(Statuses.Cancelled);
        }
      }
    }

    /**
    * A method that allows the manager to change the max seats per customer
    * of a show.
    * @param eventName The name of the show's event
    * @param start The start date and time of the show in the format dd.MM.yyyy HH:mm
    * @param newMSPC The new max seats per customer value for the show
    */
    public void changeMSPC(String eventName, String start, int newMSPC)
    {
      if(isManager())
      {
        Show show = findShow(eventName, start);
        if(show !=null)
        {
          show.setMSPC(newMSPC);
        }
      }
    }

    /**
    * Method that allows a manager to add a promotion to the system
    * @param promoName Name of the new promotion
    * @param start The start date of the promotion in the format dd.MM.yyyy
    * @param end The end date of the promotion in the format dd.MM.yyyy
    * @param promoDay The day that this promotion runs
    * @param startT The time that the promotion starts in the format HH:mm
    * @param endT The time at which the promotion stops running in the format HH:mm
    * @param child The price for a child's ticket
    * @param student The price for a student's ticket
    * @param adult The price for an adult's ticket
    * @param senior The price for a senior's ticket
    * @param discountName The name of the discount tarif to be applied to this promotion
    */
    public void addPromotion(String promoName, String start, String end, WeekDay promoDay, String startTime, String endTime, BigDecimal child, BigDecimal student, BigDecimal adult, BigDecimal senior, String discountName)
    {
      DateTimeFormatter date = DateTimeFormatter.ofPattern("dd.MM.yyyy");
      DateTimeFormatter hour = DateTimeFormatter.ofPattern("HH:mm");
      if(isManager())
      {
        if(findDiscount(discountName) != null)
        {
          int discountID = findDiscount(discountName).getID();
          promotions.add(new Promotion(lastPromoID, promoName, LocalDate.parse(start, date), LocalDate.parse(end, date), promoDay, LocalTime.parse(startTime, hour), LocalTime.parse(endTime, hour), child, student, adult, senior, discountID));
          lastPromoID++;
        }
      }
    }

    /**
    * Method that allows a manager to assign a promotion to a specific seat in a show
    * @param promotionName The name of the promotion that is being assigned
    * @param eventName The name of the show's event for which the promotion is being assigned
    * @param start The start date and time of the show for which seats are being assigned a promotion
    * @param seats Array containinng the numbers of these to which the selected promotion is being assigned.
    */
    public void assignPromotion(String promotionName, String eventName, String start, int[] seats)
    {
      if(isManager())
      {
        if(findPromotion(promotionName) != null)
        {
          int promoID = findPromotion(promotionName).getID();
          Show show = findShow(eventName, start);
          if(show != null)
          {
            Manager manager = (Manager) users.get(userID);
            manager.assignPromotion(promoID, show, seats);
          }
        }
      }
    }

    /**
    * Method that allows the manager to delete a promotion from the system
    * @param promotionName The name of the promotion to be deleted
    */
    public void deletePromotion(String promotionName)
    {
      if(isManager())
      {
        if(findPromotion(promotionName) != null)
        {
          promotions.remove(findPromotion(promotionName));
        }
      }
    }

    /**
    * Method that allows a manager to add a new agent to the system with their contract all set up
    * @param name The name of the new agent
    * @param newPwd The password of the new agent
    * @param newEmail The email of the new agent
    * @param newCom The commission of the new agent
    * @param newDate The start date of the agent's contract in format dd.MM.yyyy
    * @param dayDuration The duration of the contract measured in days
    */
    public void addContract(String name, String newPwd, String newEmail, float newCom, String newDate, long dayDuration)
    {
      DateTimeFormatter date = DateTimeFormatter.ofPattern("dd.MM.yyyy");
      if(isManager())
      {
        users.add(new Agent(lastUserID, name, newPwd, newEmail, newCom, LocalDate.parse(newDate, date), dayDuration));
        lastUserID++;
      }
    }

    /**
    * Method that allows a manager to change the contract that it has with an agent
    * @param agentName The name of the agent for which the manager is changing the contract
    * @param newCom The new commission for the agent (set to -1 means no change)
    * @param newDate The new startDate for the contract (set null for no changes) in the format
    * dd.MM.yyyy
    */
    public void changeContract(String agentName, float newCom, String newDate)
    {
      if(isManager())
        {
            User possibleAgent = findUser(agentName);
            if(possibleAgent != null && possibleAgent.getType().equals(UserTypes.Agent))
            {
                DateTimeFormatter date = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                Manager thisOne = (Manager) users.get(userID);
                thisOne.changeContract((Agent) possibleAgent, newCom, LocalDate.parse(newDate, date));
            }
            else
            {
                System.out.println("There is no agent named " + agentName + " in the system.");
                System.out.println();
            }
        }
    }

    /**
    * Method that allows to renew a contract for a certain amount of days
    * after the normal end of the contract
    * @param agentName The name of the agent for which the contract is being extended
    * @param days The number of days that the contract should be extended for
    */
    public void renewContract(String agentName, long days)
    {
      if(isManager())
      {
        Agent possibleAgent = (Agent) findUser(agentName);
        if(possibleAgent != null && possibleAgent.getType().equals(UserTypes.Agent))
        {
          possibleAgent.renewFor(days);
        }
        else
        {
            System.out.println("There is no agent named " + agentName + " in the system.");
            System.out.println();
        }
      }
    }

    /**
    * Method that allows for new users to register to the system
    * @param newName The new name for the user
    * @param newPwd The password for the new user
    * @param newEmail The email of the new user
    * @param userKind The type of user (Customer, Manager)
    */
    public void register(String newName, String newPwd, String newEmail, UserTypes userKind)
    {
      if(userKind == UserTypes.Customer)
      {
        users.add(new Customer(lastUserID, newName, newPwd, newEmail));
        lastUserID++;
      }
      else if(userKind == UserTypes.Manager)
      {
        users.add(new Manager(lastUserID, newName, newPwd, newEmail));
      }
    }

    /**
    * Method that allows users to login into the system
    * @param email The email of the user that is logging in
    * @param pwd The password provided by the user
    */
    public void login(String email, String pwd)
    {
      int index = -1;
      for(User user : users)
      {
          if(user.getEmail().equals(email))
          {
              index = users.indexOf(user);
              userID = index;
          }
      }

      if(index == -1)
      {
          System.out.println("The email that you are using isn't registered with any account");
      }
      else
      {
          users.get(index).login(pwd);
      }

      currentUserType = users.get(index).getType();
    }

    /**
    * Method that displays all the events that aren't cancelled
    */
    public void viewEvents()
    {
      for(Event event : events)
      {
        if(event.getStatus() != Statuses.Cancelled)
        {
          event.viewEvent();
        }
      }
    }

    /**
    * Method that allows to see the shows from an event
    */
    public void viewShows(String eventName)
    {
      Event event = findEvent(eventName);
      if(event != null)
      {
        event.viewShows();
      }
    }

    public void buyTicket(String eventName, String start, int seatTicket)
    {
      if(isCustomer())
      {
        Show show = findShow(eventName, start);
        if(show != null)
        {
          show.reserveSeat(seatTicket);
          Customer cust = (Customer) users.get(userID);
          cust.addTicket(show.getID(), findEvent(eventName).getID(), seatTicket);
          System.out.println("Your ticket has been bought");
        }
        else
        {
          System.out.println("Sorry, but there was a problem buying your ticket.");
        }
      }
    }

    public void viewTickets()
    {
      if(isCustomer())
      {
        Customer customer = (Customer) users.get(userID);
        customer.viewTickets();
      }
    }

    public void viewSoldTickets()
    {
      if(isAgent())
      {
        Agent agent = (Agent) users.get(userID);
        agent.viewTickets();
      }
    }
}
