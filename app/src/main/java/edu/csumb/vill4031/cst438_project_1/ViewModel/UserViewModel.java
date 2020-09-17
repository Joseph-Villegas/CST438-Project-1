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
    //private LiveData<List<User>> allUsers;
    private List<User> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);

        repository = new UserRepository(application);
        allUsers = repository.getAllUsers();

    }

    public void insert(User user) {
        repository.insertUser(user);
    }

    public void update(User user) {
        repository.update(user);
    }
    public void delete(User user) {
        repository.deleteUser(user);
    }
    public void deleteAllUsers() {
        repository.deleteAllUsers();
    }

    //may need livedata
    public List<User> getAllUsers() {
        return allUsers;
    }
}