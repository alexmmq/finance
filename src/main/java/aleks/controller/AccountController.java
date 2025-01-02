package aleks.controller;

import aleks.entity.*;

import java.util.ArrayList;
import java.util.List;

public interface AccountController {

    void addAccount(int accountNumber, User user);

    void deleteAccount(Account account, User user);

    void updateAccount(Account account, User user);

    boolean checkIfExpensesExceedBudget(User user);

    void addBudget(Budget budget, User user);

    void deleteBudget(Budget budget, User user);

    void editBudget(Budget budget, User user);

    void addExpenseToBudget(Expense expense, Budget budget, User user);

    void deleteExpenseFromBudget(Expense expense, Budget budget, User user);

    void editExpenseInBudget(Expense expense, Budget budget, User user);

    void addIncome(Income income, User user);

    void deleteIncome(Income income, User user);

    void editIncome(Income income, User user);
}
