package aleks;


import aleks.controller.AccountControllerImpl;
import aleks.controller.UserController;
import aleks.controller.UserControllerImpl;
import aleks.entity.Account;
import aleks.entity.Budget;
import aleks.entity.Expense;
import aleks.entity.Income;
import aleks.service.JsonServiceImpl;
import aleks.service.PrintServiceImpl;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    public static void Test(){
        AccountControllerImpl accountController = new AccountControllerImpl();
        UserController userController = new UserControllerImpl();
        PrintServiceImpl printService = new PrintServiceImpl();
        JsonServiceImpl jsonService = new JsonServiceImpl();

        userController.createUser("admin", "admin1234");
        accountController.addAccount(1234, userController.findUserByUsername("admin"));
        Account account = userController.findUserByUsername("admin").getAccount();

        //creating a list of incomes
        Income income = new Income("salary", 25000);
        Income income2 = new Income("dividend", 20000);
        Income income3 = new Income("side gig", 2000);
        account.addIncome(income);
        account.addIncome(income2);
        account.addIncome(income3);

        //creating a list of budgets and expenses
        Budget budget = new Budget("Transport");
        budget.setBudgetAmount(2000);
        account.addBudget(budget);
        Budget budget2 = new Budget("Everyday Needs");
        budget2.setBudgetAmount(20000);
        account.addBudget(budget2);

        Expense expenseTripToHome = new Expense("Trip to home", 500);
        account.addExpenseInBudget(expenseTripToHome, budget);
        Expense expenseFood = new Expense("Food", 10000);
        account.addExpenseInBudget(expenseFood, budget2);

        //printService.getOverallInformation(userController.findUserByUsername("admin"));

        userController.createUser("admin2", "admin1234");
        accountController.addAccount(2345, userController.findUserByUsername("admin2"));
        Account account2 = userController.findUserByUsername("admin2").getAccount();

        //creating a list of incomes
        Income income4 = new Income("salary", 100000);
        Income income5 = new Income("dividend", 30000);
        Income income6 = new Income("side gig", 3000);
        account2.addIncome(income4);
        account2.addIncome(income5);
        account2.addIncome(income6);

        //creating a list of budgets and expenses
        Budget budget3 = new Budget("Transport");
        budget3.setBudgetAmount(2000);
        account2.addBudget(budget3);
        Budget budget4 = new Budget("Everyday Needs");
        budget4.setBudgetAmount(20000);
        account2.addBudget(budget4);

        Expense expenseTripToHome2 = new Expense("Trip to home", 500);
        account2.addExpenseInBudget(expenseTripToHome2, budget3);
        Expense expenseFood2 = new Expense("Food", 10000);
        account2.addExpenseInBudget(expenseFood2, budget4);

        //printService.getOverallInformation(userController.findUserByUsername("admin"));

        jsonService.flush(userController.findAllUsers());

        userController.deleteUser("admin");
        userController.deleteUser("admin2");
        boolean areUsersDeleted = userController.findAllUsers().isEmpty();
        if (areUsersDeleted) {
            System.out.println("Users were deleted!");
        } else {
            System.out.println("Users were not deleted!");
        }

        //retrieving data
        userController = jsonService.retrieve();
        printService.getOverallInformation(userController.findUserByUsername("admin"));
        printService.getOverallInformation(userController.findUserByUsername("admin2"));

        userController.findUserByUsername("admin").getAccount().transfer(
                1000, userController.findUserByUsername("admin"), userController.findUserByUsername("admin2"));

        printService.getOverallInformation(userController.findUserByUsername("admin"));
        printService.getOverallInformation(userController.findUserByUsername("admin2"));
    }
}
