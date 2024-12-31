package aleks.service;

import aleks.entity.User;

public interface PrintService {
    void getPrettyBudgets(User user);
    void getPrettyIncomes(User user);
    void getOverallInformation(User user);
}
