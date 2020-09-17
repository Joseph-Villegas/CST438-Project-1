package edu.csumb.vill4031.cst438_project_1.RoomDatabase;

import android.accounts.Account;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import edu.csumb.vill4031.cst438_project_1.RoomDatabase.User;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query(" SELECT * FROM " + UserDatabase.USER_TABLE)
        //LiveData<List<User>> getAllUsers();
    List<User> getAllUsers();

    @Query("SELECT * FROM " + UserDatabase.USER_TABLE + " WHERE username = :username")
    User getAccountByUsername(String username);

    @Query("SELECT * FROM " + UserDatabase.USER_TABLE + " WHERE user_id = :user_id")
    User getAccountById(int user_id);

    @Query("DELETE FROM " + UserDatabase.USER_TABLE)
    void deleteAllUsers();
}