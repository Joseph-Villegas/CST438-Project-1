package edu.csumb.vill4031.cst438_project_1.RoomDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = UserDatabase.USER_TABLE)
public class User {

    @PrimaryKey(autoGenerate = true)
    private int user_id;

    private String username;
    private String password;
    private String first_name;
    private String last_name;

    public User(String username, String password, String first_name, String last_name) {
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public User() {
        this.username = "admin";
        this.password = "pass";
        this.first_name = "John";
        this.last_name = "Doe";
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
