package aleks.controller;

import aleks.entity.*;

import java.util.ArrayList;
import java.util.Iterator;

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
    public boolean checkIfExpensesExceedBudget(Budget budget, User user) {
        ArrayList<Expense> expenses = budget.getExpenses();
        double sum = 0;
        if(!expenses.isEmpty()) {
            for(Expense expense : expenses) {
                sum = sum + expense.getExpenseValue();
            }
        }
        return budget.getBudgetAmount() < sum;
    }

    @Override
    public boolean checkIfBudgetExists(String budgetName, User user) {
        boolean result = false;
        //parsing through the list of existing budgets
        ArrayList<Budget> budgets =  user.getAccount().getBudgets();
        if(!budgets.isEmpty()){
            for(Budget budget : budgets){
                if(budget.getBudgetName().equals(budgetName)){
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public boolean addBudget(String budgetName, String budgetAmount, User user) {
        //check for number validity
        boolean result = false;
        if(isValidNumber(budgetAmount)){
            Budget budget = new Budget(budgetName);
            budget.setBudgetAmount(Double.parseDouble(budgetAmount));
            user.getAccount().addBudget(budget);
            result = true;
        }
        return result;
    }

    @Override
    public boolean deleteBudget(String budgetName, User user) {
        boolean result = false;
        //parsing through existing budgets
        ArrayList<Budget> budgets = user.getAccount().getBudgets();
        Iterator<Budget> iterator = budgets.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getBudgetName().equals(budgetName)){
                iterator.remove();
                result = true;
            }
        }
        user.getAccount().setBudgets(budgets);
        return result;
    }

    @Override
    public boolean editBudget(String budgetName, String budgetAmount, User user) {
        boolean result = false;
        ArrayList<Budget> budgets = user.getAccount().getBudgets();

        //checking if budget amount is correct, retrieving existing expenses
        if(isValidNumber(budgetAmount)){
            for(Budget budget : budgets){
                if(budget.getBudgetName().equals(budgetName)){
                    budget.setBudgetAmount(Double.parseDouble(budgetAmount));
                    budget.setBudgetName(budgetName);
                }
            }
            Budget budget = new Budget(budgetName);
            budget.setBudgetAmount(Double.parseDouble(budgetAmount));
            deleteBudget(budgetName, user);
            user.getAccount().addBudget(budget);
            result = true;
        }
        return result;
    }

    @Override
    public boolean addExpenseToBudget(String expenseName, String expenseValue, String budgetName, User user) {
        //check if budget exists
        boolean result = false;
        ArrayList<Budget> budgets = user.getAccount().getBudgets();
        if(isValidNumber(expenseValue)){
            for(Budget budget: budgets){
                if(budget.getBudgetName().equals(budgetName)){
                    ArrayList<Expense> expenses = budget.getExpenses();
                    Expense expense = new Expense();
                    expense.setExpenseName(expenseName);
                    expense.setExpenseValue(Double.parseDouble(expenseValue));
                    expenses.add(expense);
                    budget.setExpenses(expenses);
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public boolean deleteExpenseFromBudget(String expenseName, String budgetName, User user) {
        boolean result = false;
        //check if budget exists
        ArrayList<Budget> budgets = user.getAccount().getBudgets();
        for(Budget budget: budgets){
            if(budget.getBudgetName().equals(budgetName)){
                ArrayList<Expense> expenses = budget.getExpenses();
                //parsing through embedded expenses, looking for the certain expense
                Iterator<Expense> iterator = expenses.iterator();
                while(iterator.hasNext()){
                    if(iterator.next().getExpenseName().equals(expenseName)){
                        iterator.remove();
                    }
                }
                budget.setExpenses(expenses);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean editExpenseInBudget(String expenseName, String expenseValue, String budgetName, User user) {
        //checking if budget exists, but first checking validity of entered Value
        //TODO - add possibility of changing the name of the expense, not only value
        boolean result = false;
        ArrayList<Budget> budgets = user.getAccount().getBudgets();
        if(isValidNumber(expenseValue)){
            for(Budget budget: budgets){
                if(budget.getBudgetName().equals(budgetName)){
                    ArrayList<Expense> expenses = budget.getExpenses();
                    for(Expense expense: expenses){
                        if(expense.getExpenseName().equals(expenseName)){
                            expense.setExpenseValue(Double.parseDouble(expenseValue));
                            result = true;
                        }
                    }
                    budget.setExpenses(expenses);
                }
            }
        }

        return result;
    }

    @Override
    public boolean addIncome(String incomeName, String incomeValue, User user) {
        //checking if value is correct
        boolean result = false;
        if(isValidNumber(incomeValue)){
            Income income = new Income();
            income.setIncomeName(incomeName);
            income.setIncomeValue(Double.parseDouble(incomeValue));
            user.getAccount().addIncome(income);
            result = true;
        }
        return result;
    }

    @Override
    public boolean deleteIncome(String incomeName, User user) {
        boolean result = false;
        //checking if the income exists
        ArrayList<Income> incomes = user.getAccount().getIncomes();
        Iterator<Income> iterator = incomes.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getIncomeName().equals(incomeName)){
                iterator.remove();
                result = true;
            }
        }
        user.getAccount().setIncomes(incomes);
        return result;
    }

    @Override
    public boolean editIncome(String incomeName, String incomeValue, User user) {
        boolean result = false;
        //checking if income exists, correct digit
        ArrayList<Income> incomes = user.getAccount().getIncomes();
        if(isValidNumber(incomeValue)){
            for(Income income: incomes){
                if(income.getIncomeName().equals(incomeName)){
                    income.setIncomeValue(Double.parseDouble(incomeValue));
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public boolean isValidNumber(String inputNumber) {
        return Integer.parseInt(inputNumber) > 0;
    }
}
