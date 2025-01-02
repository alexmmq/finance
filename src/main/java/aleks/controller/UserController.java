package aleks.controller;

import aleks.entity.User;

import java.util.ArrayList;

public interface UserController {
    void setActiveUser(User user);

    void resetActiveUser();

    User getActiveUser();

    void createUser(String username, String password);

    //case of creation out of records
    void createUser(User user);

    void updateUser(User user);

    void deleteUser(String userName);

    User findUserByUsername(String username);

    ArrayList<User> findAllUsers();
}
