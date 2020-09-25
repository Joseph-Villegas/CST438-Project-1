package edu.csumb.vill4031.cst438_project_1.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import edu.csumb.vill4031.cst438_project_1.R;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.User;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.UserDao;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.UserDatabase;

public class EditUserActivity extends AppCompatActivity {
    public static final String USERNAME =
            "edu.csumb.vill4031.cst438_project_1.USERNAME";
    private static final String USER_ID_KEY = "edu.csumb.vill4031.cst438_project_1.userIDKey";
    private static final String PREFERENCES_KEY = "edu.csumb.vill4031.cst438_project_1.PREFERENCES_KEY";
    private static int UserID = -1;

    EditText username;
    EditText password;
    EditText first_name;
    EditText last_name;
    Button update_account;

    private UserDao userDao;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        getDatabase();

        username = findViewById(R.id.edit_text_username);
        password = findViewById(R.id.edit_text_password);
        first_name = findViewById(R.id.edit_text_first_name);
        last_name = findViewById(R.id.edit_text_last_name);
        update_account = findViewById(R.id.button_update_account);

        user = userDao.getAccountById(UserID);

//        Intent intent = getIntent();

//        if (intent.hasExtra(USERNAME)) {
//
//            if (user == null) {
//                Toast.makeText(EditUserActivity.this, "No user found!", Toast.LENGTH_SHORT).show();
//                intent = new Intent(EditUserActivity.this, MainActivity.class);
//                startActivity(intent);
//                return;
//            }
//        } else {
//            Toast.makeText(EditUserActivity.this, "No user found!", Toast.LENGTH_SHORT).show();
//            intent = new Intent(EditUserActivity.this, MainActivity.class);
//            startActivity(intent);
//            return;
//        }

        username.setText(user.getUsername());
        password.setText(user.getPassword());
        first_name.setText(user.getFirst_name());
        last_name.setText(user.getLast_name());

        update_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!user.getUsername().equals(username.getText().toString())) {
                    user.setUsername(username.getText().toString());
                }
                if (!user.getPassword().equals(password.getText().toString())) {
                    user.setPassword(password.getText().toString());
                }
                if (!user.getFirst_name().equals(first_name.getText().toString())) {
                    user.setFirst_name(first_name.getText().toString());
                }
                if (!user.getLast_name().equals(last_name.getText().toString())) {
                    user.setLast_name(last_name.getText().toString());
                }

                userDao.update(user);

                Toast.makeText(EditUserActivity.this, "Changes saved", Toast.LENGTH_SHORT).show();
                Intent intent = LandingPage.intentFactory(getApplicationContext(), user.getUser_id());
                startActivity(intent);
                return;
            }
        });
    }

    /**
     * This method retrieves the user database
     */
    private void getDatabase() {
        userDao = Room.databaseBuilder(this, UserDatabase.class, UserDatabase.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .userDao();
    }

    /**
     * This method is used for switching to this intent
     * @param context the context from which we are switching
     * @param user_id the user ID to pass to the next context
     * @return This returns the intent we are moving to
     */
    public static Intent intentFactory(Context context, int user_id) {
        UserID = user_id;
        Intent intent = new Intent(context, EditUserActivity.class);
        return intent;
    }


}