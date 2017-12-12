
/**
 * Defines a user of the Ticket System
 *
 * @author Alex Costa
 * @version 1.00
 */
public class User
{
    // instance variables - store the basic details of the user, including login info
    public int userID;
    private String password;
    protected String name;
    protected String email;
    private Statuses status;

    /**
     * Constructor for objects of class User
     * @param lastUserID The id of the last user to create the new ID
     * @param newName The name of the new user
     * @param newPwd The password for the new user
     * @param newEmail The email of the new user
     */
    public User(int lastUserID, String newName, String newPwd, String newEmail)
    {
        userID = (lastUserID + 1);
        password = newPwd;
        name = newName;
        email = newEmail;
    }
    
    /**
     * A method to allow the user to change their password
     * @param newPwd The new password for the user
     */
    public void changePassword(String newPwd)
    {
        if(newPwd != null){
            password = newPwd;
        }
    }
    /**
     * A method to allow the user to do their login
     * @param logPwd The password entered for the login
     */
    public void login(String logPwd){
        if (status == status.SignedOff){
            if(logPwd== password){
                status = status.LoggedIn;
                System.out.println("Welcome back, " + name + "!");
            }
            else{
                System.out.println("You didn't enter the right password");
            }
        }
        else{
            System.out.println("You are already logged in.");
        }
    }
    
    /**
     * A method that allows the user to log off
     */
    public void logOff(){
        status = status.SignedOff;
        System.out.println("You are now logged off.");
    }
    
    /**
     * A method that allows to get the name of the user
     */
    public String getName(){
        return name;
    }
}
