package aleks.service;

import aleks.controller.AccountController;
import aleks.controller.AccountControllerImpl;
import aleks.controller.UserController;
import aleks.controller.UserControllerImpl;
import aleks.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class JsonServiceImpl implements JsonService{


    @Override
    public void flush(ArrayList<User> users) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("user.json"), users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserController retrieve() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<User> users = new ArrayList<>();
        try {
            users = mapper.readValue(new File("user.json"), new TypeReference<ArrayList<User>>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UserController userController = new UserControllerImpl(users);
        return userController;
    }
}
