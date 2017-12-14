import java.util.ArrayList;
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
        return null;
      }
      else
      {
        return events.get(index);
      }
    }

    public Promotion findPromotion(String promoName)
    {
      int index = -1;
      for(Promotion promo : promotions)
      {
        if(promo.getName().equals(promoName))
        {
          index = promo.getID;
        }
      }

      if(index == -1)
      {
        return null;
      }
      else
      {
        return promotions.get(index);
      }
    }
}
