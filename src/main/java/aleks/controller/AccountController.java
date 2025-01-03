package aleks.controller;

import aleks.entity.*;

import java.util.ArrayList;
import java.util.List;

public interface AccountController {

    void addAccount(int accountNumber, User user);

    void deleteAccount(Account account, User user);

    void updateAccount(Account account, User user);

    //TODO change return type to double to calculate amount of remaining budget
    boolean checkIfExpensesExceedBudget(Budget budget, User user);

    boolean checkIfBudgetExists(String budgetName, User user);

    boolean addBudget(String budgetName, String budgetAmount, User user);

    boolean deleteBudget(String budgetName, User user);

    boolean editBudget(String budgetName, String budgetAmount, User user);

    boolean addExpenseToBudget(String expenseName, String expenseValue, String budgetName, User user);

    boolean deleteExpenseFromBudget(String expenseName, String budgetName, User user);

    boolean editExpenseInBudget(String expenseName, String expenseValue, String budgetName, User user);

    boolean addIncome(String incomeName, String incomeValue, User user);

    boolean deleteIncome(String incomeName, User user);

    boolean editIncome(String incomeName, String incomeValue, User user);

    boolean isValidNumber(String inputNumber);

    //TODO add a method to make a transaction

    //TODO add a methods to get total expenses sum and sum of all incomes, available balance
}
