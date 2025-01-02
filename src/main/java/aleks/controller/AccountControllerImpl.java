package aleks.controller;

import aleks.entity.*;

import java.util.ArrayList;

public class AccountControllerImpl implements AccountController{
    @Override
    public void addAccount(int accountNumber, User user) {
        user.setAccount(new Account(accountNumber));
    }

    @Override
    public void deleteAccount(Account account, User user) {
        user.setAccount(null);
    }

    @Override
    public void updateAccount(Account account, User user) {
        user.setAccount(account);
    }


    @Override
    public boolean checkIfExpensesExceedBudget(User user) {

        return false;
    }

    @Override
    public void addBudget(Budget budget, User user) {
        user.getAccount().addBudget(budget);
    }

    @Override
    public void deleteBudget(Budget budget, User user) {
        user.getAccount().removeBudget(budget);
    }

    @Override
    public void editBudget(Budget budget, User user) {
        user.getAccount().updateBudget(budget);
    }

    @Override
    public void addExpenseToBudget(Expense expense, Budget budget, User user) {
        user.getAccount().addExpenseInBudget(expense, budget);
    }

    @Override
    public void deleteExpenseFromBudget(Expense expense, Budget budget, User user) {
        user.getAccount().removeExpenseInBudget(expense, budget);
    }

    @Override
    public void editExpenseInBudget(Expense expense, Budget budget, User user) {
        user.getAccount().editExpenseInBudget(expense, budget);
    }

    @Override
    public void addIncome(Income income, User user) {
        user.getAccount().addIncome(income);
    }

    @Override
    public void deleteIncome(Income income, User user) {
        user.getAccount().removeIncome(income);
    }

    @Override
    public void editIncome(Income income, User user) {
        user.getAccount().updateIncome(income);
    }
}
