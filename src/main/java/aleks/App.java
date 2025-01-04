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
        userController = jsonService.retrieve();
        while(true){
         try{
             ArrayList<User> users = userController.findAllUsers();
             for(User user: users){
                 System.out.println(user);
             }
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
                 System.out.println("Welcome, " + userController.getActiveUser().getUsername());

                     System.out.println(userController.getActiveUser() + "> " +
                             "\n 'overall' - to get overall data on account" +
                             "\n 'incomes' - to get incomes data on account" +
                             "\n 'budgets' - to get current budgets and expenses" +
                             "\n 'transfer' - to transfer to a different account" +
                             "\n 'logout' - to log out" +
                             "\n 'exit' - to exit the program");
                     String input = reader.readLine();
                     switch(input){
                         // Level budgets
                         case "budgets":
                             if(!userController.getActiveUser().getAccount().getBudgets().isEmpty()){
                                 printService.getPrettyBudgets(userController.getActiveUser());
                                 //case the user has budgets added
                                 System.out.println(userController.getActiveUser().getUsername() + "> " +
                                         "\n 'add' - to add a new budget to the account" +
                                         "\n 'edit' - to edit budget" +
                                         "\n 'remove' - to remove budget" +
                                         "\n 'exit' - to exit the program");
                                 input = reader.readLine();
                                 switch(input){

                                     // Level budget
                                     case "add":
                                         System.out.println("Please enter the budget name");
                                         input = reader.readLine();
                                         String budgetName = input;
                                         System.out.println("Please enter the budget amount");
                                         input = reader.readLine();
                                         String budgetAmount = input;
                                         if(accountController.addBudget(budgetName, budgetAmount, userController.getActiveUser())){
                                             System.out.println("Successfully added budget" + budgetName);
                                         } else{
                                             System.out.println("Failed to add budget" + budgetName);
                                         }
                                         break;
                                     case "edit":
                                         printService.getPrettyBudgets(userController.getActiveUser());
                                         System.out.println("Please choose a budget");
                                         input = reader.readLine();
                                         String chosenBudgetName = input;
                                         if(accountController.checkIfBudgetExists(input, userController.getActiveUser())){
                                             System.out.println("Please enter next action: " +
                                                     "\n'edit budget' - to edit budget" +
                                                     "\n'add expense' - to add expense to budget" +
                                                     "\n'edit expense' - to edit expense in budget" +
                                                     "\n'delete expense' - to delete expense from budget'");

                                             // Level expense
                                             input = reader.readLine();
                                             switch(input){
                                                 case "edit budget":
                                                     System.out.println("Please enter the new budget amount for " + input);
                                                     input = reader.readLine();
                                                     String newBudgetAmount = input;
                                                     if(accountController.editBudget(chosenBudgetName, newBudgetAmount,
                                                             userController.getActiveUser())){
                                                         System.out.println("Successfully updated budget" + chosenBudgetName);
                                                     } else {
                                                         System.out.println("Failed to update budget" + chosenBudgetName);
                                                     }
                                                     break;

                                                 case "add expense":
                                                     System.out.println("Please enter a new expense name: ");
                                                     input = reader.readLine();
                                                     String newExpenseName = input;
                                                     System.out.println("Please enter an expense amount: ");
                                                     input = reader.readLine();
                                                     String newExpenseAmount = input;
                                                     if(accountController.addExpenseToBudget(newExpenseName,
                                                             newExpenseAmount, chosenBudgetName,
                                                             userController.getActiveUser())){
                                                         System.out.println("Expense "  +  newExpenseName +
                                                                 " has been added to budget" + chosenBudgetName);
                                                     } else{
                                                         System.out.println("Expense hasn't been added");
                                                     }
                                                     break;

                                                 case "edit expense":
                                                     System.out.println("Please enter an expense to edit: ");
                                                     input = reader.readLine();
                                                     String existingExpenseName = input;
                                                     System.out.println("Please enter a new amount for expense");
                                                     input = reader.readLine();
                                                     String newExpenseValue = input;
                                                     if(accountController.editExpenseInBudget(existingExpenseName,
                                                             newExpenseValue, chosenBudgetName, userController.getActiveUser())){
                                                         System.out.println("Expense " + existingExpenseName + " in a " +
                                                                 "budget " + chosenBudgetName + " has been edited");
                                                     } else{
                                                         System.out.println("Expense hasn't been edited");
                                                     }
                                                     break;

                                                 case "delete expense":
                                                     System.out.println("Please enter an expense to delete:");
                                                     input = reader.readLine();
                                                     String expenseToDelete = input;
                                                     if(accountController.deleteExpenseFromBudget(expenseToDelete,
                                                             chosenBudgetName, userController.getActiveUser())){
                                                         System.out.println("Expense " + expenseToDelete + " was removed " +
                                                                 " from budget " + chosenBudgetName);
                                                     } else {
                                                         System.out.println("Expense hasn't been removed");
                                                     }
                                                     break;
                                                 default:
                                                     System.out.println("Incorrect input");
                                                     break;
                                             }
                                         }
                                         break;
                                     case "remove":
                                         System.out.println("Please enter the budget name to remove");
                                         input = reader.readLine();
                                         String budgetNameToRemove = input;
                                         if(accountController.deleteBudget(budgetNameToRemove, userController.getActiveUser())){
                                             System.out.println("Successfully removed budget" + budgetNameToRemove);
                                         } else{
                                             System.out.println("Failed to add budget" + budgetNameToRemove);
                                         }
                                         break;
                                     case "exit":
                                         System.exit(0);
                                     default:
                                         System.out.println("Incorrect input");
                                         break;
                                 }
                             } else{
                              //case the user doesn't have any expenses/budgets added
                                 System.out.println("No budgets added, please add a budget first");
                                 input = reader.readLine();
                                 String budgetName = input;
                                 System.out.println("Please enter the budget amount");
                                 input = reader.readLine();
                                 String budgetAmount = input;
                                 if(accountController.addBudget(budgetName, budgetAmount, userController.getActiveUser())){
                                     System.out.println("Successfully added budget" + budgetName);
                                 } else {
                                     System.out.println("Failed to add budget" + budgetName);
                                 }
                             }
                             break;
                         case "incomes":
                             printService.getPrettyIncomes(userController.getActiveUser());
                             System.out.println("Please enter next action: " +
                                     "\n'add' - to add income" +
                                     "\n'edit' - to edit existing income" +
                                     "\n'delete' - to delete income");
                             input = reader.readLine();
                             switch(input){
                                 case "add":
                                     System.out.println("Please enter a name of income: ");
                                     input = reader.readLine();
                                     String newIncomeName = input;
                                     System.out.println("Please enter an amount of income");
                                     input = reader.readLine();
                                     String newIncomeValue = input;
                                     if(accountController.addIncome(newIncomeName, newIncomeValue,
                                             userController.getActiveUser())){
                                         System.out.println("Income " + newIncomeName + "has been added");
                                     } else{
                                         System.out.println("Income hasn't been added");
                                     }
                                     break;

                                 case "edit":
                                     System.out.println("Please enter a name of income to edit: ");
                                     input = reader.readLine();
                                     String IncomeNameToEdit = input;
                                     System.out.println("Please enter a new amount of income " + IncomeNameToEdit);
                                     input = reader.readLine();
                                     String newIncomeValueToEdit = input;
                                     if(accountController.editIncome(IncomeNameToEdit, newIncomeValueToEdit,
                                             userController.getActiveUser())){
                                         System.out.println("Income " + IncomeNameToEdit + "has been edited");
                                     } else{
                                         System.out.println("Income hasn't been edited");
                                     }
                                     break;

                                 case "delete":
                                     System.out.println("Please enter a name of income to remove: ");
                                     input = reader.readLine();
                                     String IncomeNameToRemove = input;
                                     if(accountController.deleteIncome(IncomeNameToRemove,
                                             userController.getActiveUser())){
                                         System.out.println("Income " + IncomeNameToRemove + "has been removed");
                                     } else{
                                         System.out.println("Income hasn't been removed");
                                     }
                                     break;
                             }
                             break;
                         case "overall":
                             printService.getOverallInformation(userController.getActiveUser());
                             break;
                         case "transfer":
                             //getting list of available users for transfer
                             ArrayList<User> usersArray = new ArrayList<>();
                             for(User user:userController.findAllUsers()){
                                 if(!user.equals(userController.getActiveUser())){
                                     usersArray.add(user);
                                 }
                             }
                             System.out.println("Please enter a name of user you want to transfer money: ");
                             for(User user:usersArray){
                                 System.out.println(user.getUsername());
                             }
                             input = reader.readLine();
                             String recipient = input;
                             boolean correctInputUser = false;
                             for(User user:usersArray){
                                 if(user.getUsername().equals(input)){
                                     correctInputUser = true;
                                     break;
                                 }
                             }
                             if(correctInputUser){
                                 System.out.println("Please enter amount of money you want to transfer: ");
                                 input = reader.readLine();
                                 accountController.makeTransfer(recipient, input, userController);
                             } else {
                                 System.out.println("Something went wrong8");
                             }
                             break;
                         case "logout":
                             jsonService.flush(userController.findAllUsers());
                             logout();
                             break;
                         case "exit":
                             jsonService.flush(userController.findAllUsers());
                             System.exit(0);
                         default:
                             System.out.println("Something went wrong");
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
        String userName = "";
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
                    userName = user.getUsername();
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
                    if(user.getUsername().equals(userName) && user.getPassword().equals(input)){
                        userController.setActiveUser(user.getUsername());
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
