package aleks.controller;

import aleks.entity.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserControllerImpl implements UserController {
    ArrayList<User> users;

    public UserControllerImpl() {
        users = new ArrayList<>();
    }

    public UserControllerImpl(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public void createUser(String username, String password) {
        User user = new User(username, password);
        users.add(user);
    }

    @Override
    public void createUser(User user) {
        users.add(user);
    }

    @Override
    public void updateUser(User user) {
        for(int i = 0; i<users.size(); i++) {
            if(users.get(i).getUsername().equals(user.getUsername())) {
                users.set(i, user);
            }
        }
    }

    @Override
    public void deleteUser(User user) {
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()) {
            if(iterator.next().getUsername().equals(user.getUsername())) {
                iterator.remove();
            }
        }
    }

    @Override
    public User findUserByUsername(String username) {
        User userToReturn = null;
        for(User user : users) {
            if(user.getUsername().equals(username)) {
                userToReturn = user;
            }
        }
        return userToReturn;
    }

    @Override
    public List<User> findAllUsers() {
        return users;
    }
}
