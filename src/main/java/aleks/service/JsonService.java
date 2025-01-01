package aleks.service;

import aleks.controller.UserController;
import aleks.entity.User;

import java.util.ArrayList;

public interface JsonService {
    void flush(ArrayList <User> users);
    UserController retrieve();
}
