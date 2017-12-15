import java.time.LocalDate;
import java.time.LocalTime;
import java.math.BigDecimal;
/**
 * Class that stores all the data and behaviour of a promotion
 * 
 * TODO: Work on a way to assign a new discount tarif to the promotion
 *
 * @author Alex Costa
 * @version 1.00
 */
public class Promotion
{
    // instance variables
    private int promotionID;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private WeekDay day;
    private LocalTime startTime;
    private LocalTime endTime;
    private BigDecimal priceChild;
    private BigDecimal priceStudent;
    private BigDecimal priceAdult;
    private BigDecimal priceSenior;
    private int discountID;
    private DiscountTypes typeDiscount;
    private int minAmount;
    private float discount;

    /**
     * Constructor for objects of class Promotion
     * @param lastPromoID The ID of the last promotion to create the one for this promo
     * @param promoName The name of the new promotion
     * @param start The start date of the promotion
     * @param end The end date of the promotion
     * @param promoDay The day that this promotion runs
     * @param startT The time that the promotion starts
     * @param endT The time at which the promotion stops running
     * @param child The price for a child's ticket
     * @param student The price for a student's ticket
     * @param adult The price for an adult's ticket
     * @param senior The price for a senior's ticket
     * @param discount The ID of the type of discount to be applied to this promotion
     */
    public Promotion(int lastPromoID, String promoName, LocalDate start, LocalDate end, WeekDay promoDay, LocalTime startT, LocalTime endT
    , BigDecimal child, BigDecimal student, BigDecimal adult, BigDecimal senior, int discount)
    {
        promotionID = (lastPromoID + 1);
        name = promoName;
        startDate = start;
        endDate = end;
        day = promoDay;
        startTime = startT;
        endTime = endT;
        priceChild = child;
        priceStudent = student;
        priceAdult = adult;
        priceSenior = senior;
        discountID = discount;
        getDiscountDetails();
    }
    
    /**
     * Method that allows other classes to retrieve the day of the week that this promotion runs on
     * @return The day of the week that this promotion runs in
     */
    public WeekDay getday()
    {
        return day;
    }
    
    /**
     * Method that sets a new week day for the promotion to run on
     * @param newDay The new day that the promotion will run on
     */
    public void setDay(WeekDay newDay)
    {
        day = newDay;
    }
    
    /**
     * Method that allows other classes to retrieve the time at which the promotion starts
     * @return The time at which the promotion starts
     */
    public LocalTime getStartTime()
    {
        return startTime;
    }
    
    /**
     * Method that allows other classes to retrieve the time at which the promotion ends
     * @return The time at which the promotion stops running
     */
    public LocalTime getEndTime()
    {
        return endTime;
    }
    
    /**
     * Method that allows to set a new start time for the promotion
     * @param newStart The new start time for the promotion
     */
    public void setStartTime(LocalTime newStart)
    {
        startTime = newStart;
    }
    /**
     * Method that allows to set a new end time for the promotion
     * @param newEnd The new end time for the promotion
     */
    public void setEndTime(LocalTime newEnd)
    {
        endTime = newEnd;
    }
    
    /**
     * Method that allows other methods to get the start date of the promotion
     * @return The start date of the promotion
     */
    public LocalDate getStartDate()
    {
        return startDate;
    }
    
    /**
     * Method that allows other methods to get the end date of the promotion
     * @return The end date of the promotion
     */
    public LocalDate getEndDate()
    {
        return endDate;
    }
    
    /**
     * Method that allows other methods to get the price of a child ticket
     * @return The price of a child ticket
     */
    public BigDecimal getChildPrice()
    {
        return priceChild;
    }
    
    /**
     * Method that allows other methods to get the price of a student ticket
     * @return The price of a student ticket
     */
    public BigDecimal getStudentPrice()
    {
        return priceStudent;
    }
    
    /**
     * Method that allows other methods to get the price of an adult ticket
     * @return The price of an adult ticket
     */
    public BigDecimal getAdultPrice()
    {
        return priceAdult;
    }
    
    /**
     * Method that allows other methods to get the price of a senior ticket
     * @return The price of a senior ticket
     */
    public BigDecimal getSeniorPrice()
    {
        return priceSenior;
    }
    
    /**
     * Method that allows to change the price of a child's ticket
     * @param child The new price for a child's ticket
     */
    public void setChildPrice(BigDecimal child)
    {
        priceChild = child;
    }
    
    /**
     * Method that allows to change the price of a student's ticket
     * @param child The new price for a student's ticket
     */
    public void setStudentPrice(BigDecimal student)
    {
        priceStudent = student;
    }
    
    /**
     * Method that allows to change the price of an adult's ticket
     * @param child The new price for an adult's ticket
     */
    public void setAdultPrice(BigDecimal adult)
    {
        priceAdult = adult;
    }
    
    /**
     * Method that allows to change the price of a senior's ticket
     * @param child The new price for a senior's ticket
     */
    public void setSeniorPrice(BigDecimal senior)
    {
        priceSenior = senior;
    }
    
    /**
     * Method that allows to set a new discount to a promotion
     * @param newDiscount The ID of the new discount applied to the promotion
     */
    public void setDiscount(int newDiscount)
    {
        discountID = newDiscount;
        getDiscountDetails();
    }
    
    /**
     * Method that allows to get the details of the discount tarif that is applied in the promotion
     */
    public void getDiscountDetails()
    {
        Discount thisDiscount = TicketSystem.discounts.get(discountID);
        if(thisDiscount.getMinTickets() == -1)
        {
            typeDiscount = typeDiscount.Spend;
            minAmount = thisDiscount.getMinSpent();
        }
        else{
            typeDiscount = typeDiscount.Tickets;
            minAmount = thisDiscount.getMinTickets();
        }
        
        discount = thisDiscount.getDiscount();
    }
    
    /**
     * Method that allows other classes to get the name of the promotion
     * @return The name of the promotion in the String format
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Method that allows other classes to retrieve the ID of the promotion
     * @return The ID of the promotion in the integer type
     */
    public int getID()
    {
        return promotionID;
    }
}
