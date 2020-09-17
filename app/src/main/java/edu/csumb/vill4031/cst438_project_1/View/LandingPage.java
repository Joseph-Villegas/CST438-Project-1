package edu.csumb.vill4031.cst438_project_1.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.csumb.vill4031.cst438_project_1.R;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.User;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.UserDao;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.UserDatabase;

public class LandingPage extends AppCompatActivity {
    static ArrayList<User> Users = new ArrayList<User>();
    private static final String USER_ID_KEY = "edu.csumb.vill4031.cst438_project_1.userIDKey";
    private static final String PREFERENCES_KEY = "edu.csumb.vill4031.cst438_project_1.PREFERENCES_KEY";
    private static int UserID = -1;

    TextView welcomeMessage;
    Button userInfo;
    Button courses;
    Button logout;

    private UserDao userDao;
    private User user;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        wireUpDisplay();
        getDatabase();
        getName();
    }

    private void wireUpDisplay() {
        welcomeMessage = findViewById(R.id.textview_landingPage_welcomeText);
        userInfo = findViewById(R.id.button_landingPage_userInfo);
        courses = findViewById(R.id.button_landingPage_courses);
        logout = findViewById(R.id.button_landingPage_logout);

        //initialize userInfo button
        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = UserInfo.intentFactory(getApplicationContext(), user.getUser_id());
                startActivity(intent);
            }
        });

        //initialize courses button
        courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPage.this, AddCourse.class);
                startActivity(intent);
            }
        });

        //initialize logout button
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.intentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

//        userInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LandingPage.this, EditUserActivity.class);
//                User user = userDao.getAccountById(UserID);
//                intent.putExtra(EditUserActivity.USERNAME, user.getUsername());
//                startActivity(intent);
//                return;
//            }
//        });
    }

    private boolean getName() {
        user = userDao.getAccountById(UserID);
        if (user == null) {
            toaster("No user by ID " + UserID + " found.");
            return false;
        } else {
            name = user.getFirst_name() + " " + user.getLast_name();
            welcomeMessage.setText("Welcome, " + name + "!");
            return true;
        }
    }

    //method for setting up database
    public void getDatabase() {
        userDao = Room.databaseBuilder(this, UserDatabase.class, UserDatabase.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .userDao();
    }

    //method for switching to this activity
    public static Intent intentFactory(Context context, int user_id) {
        UserID = user_id;
        Intent intent = new Intent(context, LandingPage.class);
        return intent;
    }

    //method used for creating toast messages
    public void toaster(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 64);
        toast.show();
    }
}