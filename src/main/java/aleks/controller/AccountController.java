package aleks.controller;

import aleks.entity.*;

import java.util.ArrayList;
import java.util.List;

public interface AccountController {

    void addAccount(int accountNumber, User user);

    void deleteAccount(Account account, User user);

    void updateAccount(Account account, User user);

    ArrayList<Income> getIncomes(User user);

    void setIncomes(ArrayList<Income> incomes, User user);

    ArrayList<Budget> getBudgets(User user);

    void setBudgets(ArrayList<Budget> budgets, User user);

    boolean checkIfExpensesExceedBudget(User user);
}
