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
    public ArrayList<Income> getIncomes(User user) {
        return user.getAccount().getIncomes();
    }

    @Override
    public void setIncomes(ArrayList<Income> incomes, User user) {
        user.getAccount().setIncomes(incomes);
    }

    @Override
    public ArrayList<Budget> getBudgets(User user) {
        return user.getAccount().getBudgets();
    }

    @Override
    public void setBudgets(ArrayList<Budget> budgets, User user) {
        user.getAccount().setBudgets(budgets);
    }

    @Override
    public boolean checkIfExpensesExceedBudget(User user) {

        return false;
    }
}
