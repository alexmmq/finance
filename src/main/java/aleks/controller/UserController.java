package aleks.controller;

import aleks.entity.User;

import java.util.List;

public interface UserController {
    //case of creation without records
    void createUser(String username, String password);

    //case of creation out of records
    void createUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    User findUserByUsername(String username);

    List<User> findAllUsers();
}
