package edu.csumb.vill4031.cst438_project_1.Repository;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.User;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.UserDao;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.UserDatabase;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    public UserRepository(Application application) {
        UserDatabase db = UserDatabase.getDatabase(application);
        userDao = db.userDoa();
        allUsers = userDao.getAllUsers();

    }

    public void deleteUser() {
        userDao.deleteAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insertUser(User user) {
        new UserInsertion(userDao).execute(user);
    }

    private static class UserInsertion extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private UserInsertion(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.deleteAllUsers();
            userDao.insertUser(users[0]);
            return null;
        }
    }
}



