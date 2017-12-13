
/**
 * Class that stores all the data and behaviour of a discount tarif
 *
 * @author Alex Costa
 * @version 1.00
 */
public class Discount
{
    // instance variables
    private int discountID;
    private String discountName;
    private int minTickets;
    private int minSpent;
    private float discountPercent;

    /**
     * Constructor for objects of class Discount
     * @param lastDiscountID The ID of the last discount to create the ID for this one
     * @param name The name of the new discount tarif
     * @param tickets The minimum number of tickets to be bought (can be null)
     * @param spent The minimum amount of money that need to be spent to active the discount (can be null)
     * @param percent The percentage of discount that is given when the min is achieved
     */
    public Discount(int lastDiscountID, String name, Integer tickets, Integer spent, float percent)
    {
        discountID = (lastDiscountID + 1);
        discountName = name;
        if(tickets != null){minTickets = tickets;}else{ minTickets = -1;}
        if(spent != null){minSpent = spent;}else{spent = -1;}
        discountPercent = percent;
    }
    
    /**
     * Method that allows other classes to get the name of the discount
     * @return The name of the discount
     */
    public String getDiscountName()
    {
        return discountName;
    }
    
    /**
     * Method that allows other classes to get the minimum number of tickets to activate the discount
     * @return The minimum number of tickets to buy to activate the discount
     */
    public int getMinTickets()
    {
        return minTickets;
    }
    
    /**
     * Method that allows other classes to get the minimum to be spent to activate the discount
     * @return The minimum to be spent
     */
    public int getMinSpent()
    {
        return minSpent;
    }
    
    /**
     * Method that allows other classes to get the percentage of discount given
     * @return The percentage of discount
     */
    public float getDiscount()
    {
        return discountPercent;
    }
    
    /**
     * Method that allows to set a new name to this discount tarif
     * @param newName The new name for this discount tarif
     */
    public void setName(String newName)
    {
        discountName = newName;
    }
    
    /**
     * Method that allows to set a new minimum of tickets to be bought to active the discount
     * @param newMin The new minimum of tickets
     */
    public void setMinTickets(Integer newMin)
    {
        minTickets = newMin;
    }
    
    /**
     * Method that allows to set a new minimum amount of money to be spent to activate the discount
     * @param newMin The new minimum amount of money to be spent`
     */
    public void setMinSpent(Integer newMin)
    {
        minSpent = newMin;
    }
}
