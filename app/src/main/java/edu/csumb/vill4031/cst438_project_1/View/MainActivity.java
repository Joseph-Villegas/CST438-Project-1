package edu.csumb.vill4031.cst438_project_1.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import edu.csumb.vill4031.cst438_project_1.R;
import edu.csumb.vill4031.cst438_project_1.RecyclerView.Adapter;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.User;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.UserDao;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.UserDatabase;
import edu.csumb.vill4031.cst438_project_1.ViewModel.UserViewModel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class MainActivity extends AppCompatActivity {

    static ArrayList<User> Users = new ArrayList<User>();
    private UserViewModel userViewModel;
    private static final String USER_ID_KEY = "edu.csumb.vill4031.cst438_project_1.userIDKey";
    private static final String PREFERENCES_KEY = "edu.csumb.vill4031.cst438_project_1.PREFERENCES_KEY";
    private int UserID = -1;

    EditText usernameField;
    EditText passwordField;
    Button login;
    Button createAccount;

    private UserDao userDao;
    private String usernameString;
    private String passwordString;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireUpDisplay();
        getDatabase();
        checkForUser();
    }

    /**
     * This method connects all buttons, TextViews, etc. to the activity and sets up click listeners
     */
    private void wireUpDisplay() {
        usernameField = findViewById(R.id.login_username);
        passwordField = findViewById(R.id.login_password);
        login = findViewById(R.id.button_login);
        createAccount = findViewById(R.id.button_createAccount);

        //initialize "login" button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValuesFromDisplay();
                if (checkForUserInDatabase()) {
                    if (!validatePassword()) {
                        toaster("Error: either bad username/password or account does not exist");
                    } else {
                        Intent intent = LandingPage.intentFactory(getApplicationContext(), user.getUser_id());
                        startActivity(intent);
                    }
                }
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * This method retrieves the user's input values from the username and password fields
     */
    private void getValuesFromDisplay() {
        usernameString = usernameField.getText().toString();
        passwordString = passwordField.getText().toString();
    }


    /**
     * This method retrieves the user database
     */
    public void getDatabase() {
        userDao = Room.databaseBuilder(this, UserDatabase.class, UserDatabase.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .userDao();
    }

    /**
     * This method checks for a user in the intent, and creates a default user if one doesn't exist
     */
    private void checkForUser() {
        //do we have a user in the intent?
        UserID = getIntent().getIntExtra(USER_ID_KEY, -1);

        //do we have a user in the preferences?
        if(UserID != -1) {
            return;
        }

        SharedPreferences preferences = this.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        UserID = preferences.getInt(USER_ID_KEY, -1);

        if (UserID != -1) {
            return;
        }

        //do we have any users at all?
        List<User> users = userDao.getAllUsers();
        if (users.isEmpty()) {
            User defaultUser = new User("admin", "password", "John", "Doe");
            userDao.insertUser(defaultUser);
        }
    }

    /**
     * This method checks if the username the user is trying to login with exists in the database
     * @return false if user doesn't exist, otherwise true
     */
    private boolean checkForUserInDatabase() {
        user = userDao.getAccountByUsername(usernameString);
        if (user == null) {
            toaster("No user " + usernameString + " found.");
            return false;
        }
        return true;
    }

    /**
     * This method checks if the user entered the correct password
     * @return boolean of whether the actual password matches what the user entered
     */
    private boolean validatePassword() {
        return user.getPassword().equals(passwordString);
    }

    /**
     * This method is used for switching to this intent
     * @param context the context from which we are switching
     * @return This returns the intent we are moving to
     */
    public static Intent intentFactory(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    /**
     * This method creates toast messages
     * @param message the message to be made into a toast
     */
    public void toaster(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 64);
        toast.show();
    }
}
