package edu.csumb.vill4031.cst438_project_1.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import java.util.ArrayList;

import edu.csumb.vill4031.cst438_project_1.R;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.User;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.UserDao;

public class UserInfo extends AppCompatActivity {
    static ArrayList<User> Users = new ArrayList<User>();
    private static final String USER_ID_KEY = "edu.csumb.vill4031.cst438_project_1.userIDKey";
    private static final String PREFERENCES_KEY = "edu.csumb.vill4031.cst438_project_1.PREFERENCES_KEY";
    private static int UserID = -1;

    private UserDao userDao;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
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