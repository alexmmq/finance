package aleks.entity;

import lombok.Data;

@Data
public class User {

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //holds all the data required for finance operation - starting from login information to account details
    private String username;
    private String password;
    private Account account;
}
