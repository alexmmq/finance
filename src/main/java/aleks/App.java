package aleks;

import aleks.controller.AccountControllerImpl;
import aleks.controller.UserController;
import aleks.controller.UserControllerImpl;
import aleks.entity.*;
import aleks.service.JsonServiceImpl;
import aleks.service.PrintServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static AccountControllerImpl accountController = new AccountControllerImpl();
    static UserController userController = new UserControllerImpl();
    static PrintServiceImpl printService = new PrintServiceImpl();
    static JsonServiceImpl jsonService = new JsonServiceImpl();
    static boolean isLoggedIn = false;
    public static void main( String[] args ) {
        while(true){
         try{
             if(!isLoggedIn){
                 System.out.println("Please enter following commands to proceed: "
                         + "\n'init' - to create a profile"
                         + "\n'login' - to log in using existing profile"
                         + "\n'exit' - to exit the program");
                 String input = reader.readLine();
                 switch(input){
                     case "init":
                         signUp();
                         break;
                     case "login":
                         login();
                             break;
                     case "exit":
                         System.exit(0);
                     default:
                         System.out.println("Invalid command");
                 }
             } else{
                 System.out.println("Welcome, " + userController.getActiveUser());

                     System.out.println(userController.getActiveUser() + "> " +
                             "\n 'overall' - to get overall data on account" +
                             "\n 'incomes' - to get incomes data on account" +
                             "\n 'expenses' - to get current data and expenses" +
                             "\n 'logout' - to log out" +
                             "\n 'exit' - to exit the program");
                     String input = reader.readLine();
                     switch(input){
                         case "expenses":
                             if(!userController.getActiveUser().getAccount().getBudgets().isEmpty()){
                                 printService.getPrettyBudgets(userController.getActiveUser());
                             } else{
                              //case the user doesn't have any expenses/budgets added
                                 System.out.println("No expenses and budgets added, please add a budget first");
                                 input = reader.readLine();
                                 Budget budget = new Budget(input);
                                 System.out.println("Please enter a size of budget");
                                 input = reader.readLine();
                                 if(Double.parseDouble(input)>0){
                                     budget.setBudgetAmount(Double.parseDouble(input));
                                 } else{
                                     System.out.println("Invalid budget");
                                 }
                                 userController.getActiveUser().getAccount().addBudget(budget);
                                 System.out.println("Added budget " + budget);
                             }
                             break;
                         case "incomes":
                             printService.getPrettyIncomes(userController.getActiveUser());
                             break;
                         case "overall":
                             printService.getOverallInformation(userController.getActiveUser());
                             break;
                     }

             }
         } catch (Exception E){
             System.out.println(E.getMessage());
         }
        }
    }

    public static void signUp(){
        String input = "";
        String name = "";
        String password = "";
        ArrayList<User> users = userController.findAllUsers();
        System.out.println("Please enter a username to be used in system:");


            try {
                input = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            boolean nameInUse = false;
        if(!users.isEmpty()) {
            for (User user : users) {
                //case if we have the same username already, recursive call to the function
                if (user.getUsername().equals(input)) {
                    nameInUse = true;
                    break;
                }
            }
        }
            if(nameInUse){
                System.out.println("Username is already in use!");
                signUp();
            } else{
                //positive case, proceeding with password
                name = input.trim();

                System.out.println("Please enter a password to be used in system:");
                //#TODO check of the password (Special sign, Alphanumeric Chars)
                try {
                    input = reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                password = input.trim();
                userController.createUser(name, password);
                //assigning account to the user
                int accountNumber = (int)(Math.random()*1000);
                accountController.addAccount(accountNumber, userController.findUserByUsername(name));
            }
    }

    public static void login(){
        String input = "";
        ArrayList<User> users = userController.findAllUsers();
        System.out.println("Please enter your username:");
        if(!users.isEmpty()){
            try {
                input = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //parsing through existing users to find a match
            boolean nameFound = false;
            for(User user : users){
                if(user.getUsername().equals(input)){
                    nameFound = true;
                    break;
                }
            }
            if(nameFound){
                System.out.println("Please enter your password:");
                try {
                    input = reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                for(User user : users){
                    if(user.getPassword().equals(input)){
                        userController.setActiveUser(user);
                        isLoggedIn = true;
                        break;
                    }
                }
            } else{
                System.out.println("Username is incorrect!");
                login();
            }
        }
    }

    public static void logout(){
        isLoggedIn = false;
        userController.resetActiveUser();
    }
}
