package aleks;

import aleks.controller.AccountControllerImpl;
import aleks.controller.UserController;
import aleks.controller.UserControllerImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AccountControllerImpl accountController = new AccountControllerImpl();
        UserController userController = new UserControllerImpl();
        userController.createUser("admin", "admin1234");
        accountController.addAccount(1234, userController.findUserByUsername("admin"));

    }
}
