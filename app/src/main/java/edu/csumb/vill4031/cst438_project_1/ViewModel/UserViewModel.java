package edu.csumb.vill4031.cst438_project_1.ViewModel;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import edu.csumb.vill4031.cst438_project_1.Repository.UserRepository;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.User;

public class UserViewModel extends AndroidViewModel {
    private UserRepository repository;
    //private LiveData<List<User>> getAllUsers;
    private List<User> getAllUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);

        repository = new UserRepository(application);
        getAllUsers = repository.getAllUsers();

    }

    public void insert(User user) {
        repository.insertUser(user);
    }

    //may need livedata
    public List<User> getGetAllUsers() {
        return getAllUsers;
    }
}