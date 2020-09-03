package edu.csumb.vill4031.cst438_project_1.RoomDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.User;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User user);

    @Query("select * from user_table")
    LiveData<List<User>> getAllUsers();

    @Query("delete from LoginDetails")
    void deleteAllUsers();
}
