package edu.csumb.vill4031.cst438_project_1.View;

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
    private int UserID = -1;

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

        Intent intent = getIntent();

        if (intent.hasExtra(USERNAME)) {
            user = userDao.getAccountByUsername(intent.getStringExtra(USERNAME));
            if (user == null) {
                Toast.makeText(EditUserActivity.this, "No user found!", Toast.LENGTH_SHORT).show();
                intent = new Intent(EditUserActivity.this, MainActivity.class);
                startActivity(intent);
                return;
            }
        } else {
            Toast.makeText(EditUserActivity.this, "No user found!", Toast.LENGTH_SHORT).show();
            intent = new Intent(EditUserActivity.this, MainActivity.class);
            startActivity(intent);
            return;
        }

        username.setText(user.getUsername());
        password.setText(user.getPassword());
        first_name.setText(user.getFirst_name());
        last_name.setText(user.getLast_name());

        update_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!user.getUsername().equals(username.toString().trim())) {
                    user.setUsername(username.toString().trim());
                }
                if (!user.getPassword().equals(password.toString().trim())) {
                    user.setPassword(password.toString().trim());
                }
                if (!user.getFirst_name().equals(first_name.toString().trim())) {
                    user.setFirst_name(first_name.toString().trim());
                }
                if (!user.getLast_name().equals(last_name.toString().trim())) {
                    user.setLast_name(last_name.toString().trim());
                }

                userDao.update(user);

                Toast.makeText(EditUserActivity.this, "Changes saved", Toast.LENGTH_SHORT).show();
                Intent intent = LandingPage.intentFactory(getApplicationContext(), user.getUser_id());
                startActivity(intent);
                return;
            }
        });
    }

    private void getDatabase() {
        userDao = Room.databaseBuilder(this, UserDatabase.class, UserDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .userDao();
    }


}