package aleks;

import aleks.controller.AccountControllerImpl;
import aleks.controller.UserController;
import aleks.controller.UserControllerImpl;
import aleks.entity.Account;
import aleks.entity.Budget;
import aleks.entity.Expense;
import aleks.entity.Income;
import aleks.service.PrintServiceImpl;

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
        PrintServiceImpl printService = new PrintServiceImpl();

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

        printService.getOverallInformation(userController.findUserByUsername("admin"));
    }
}
