package edu.csumb.vill4031.cst438_project_1.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import edu.csumb.vill4031.cst438_project_1.R;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.User;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.UserDao;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.UserDatabase;

public class UserInfo extends AppCompatActivity {
    static ArrayList<User> Users = new ArrayList<User>();
    private static final String USER_ID_KEY = "edu.csumb.vill4031.cst438_project_1.userIDKey";
    private static final String PREFERENCES_KEY = "edu.csumb.vill4031.cst438_project_1.PREFERENCES_KEY";
    private static int UserID = -1;

    private UserDao userDao;
    private User user;

    Button editUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        wireUpDisplay();
        getDatabase();
        getName();

    }

    private void wireUpDisplay() {
        editUser = findViewById(R.id.button_userInfo_editUser);
        //viewCourses = findViewById(R.id.button_landingPage_courses);
        //viewAssignments = findViewById(R.id.button_landingPage_logout);

        //initialize editUser button
        editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = EditUserActivity.intentFactory(getApplicationContext(), getUserID());
                startActivity(intent);
            }
        });

    }

    private int getUserID() {
        return user.getUser_id();
    }

    private User getName() {
        return user = userDao.getAccountById(UserID);
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
        Intent intent = new Intent(context, UserInfo.class);
        return intent;
    }

    //method used for creating toast messages
    public void toaster(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 64);
        toast.show();
    }
}