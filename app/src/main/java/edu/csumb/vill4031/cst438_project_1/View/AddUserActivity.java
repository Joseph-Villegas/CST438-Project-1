package edu.csumb.vill4031.cst438_project_1.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import edu.csumb.vill4031.cst438_project_1.R;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.User;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.UserDao;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.UserDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUserActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText first_name;
    EditText last_name;

    Button create_account;

    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        getDatabase();

        username = findViewById(R.id.edit_text_username);
        password = findViewById(R.id.edit_text_password);
        first_name = findViewById(R.id.edit_text_first_name);
        last_name = findViewById(R.id.edit_text_last_name);
        create_account = findViewById(R.id.button_create_account);

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_input_value = username.getText().toString().trim();
                String password_input_value = password.getText().toString().trim();
                String first_name_input_value = first_name.getText().toString().trim();
                String last_name_input_value = last_name.getText().toString().trim();

                if (fieldsAreEmpty(username_input_value, password_input_value, first_name_input_value, last_name_input_value)) {
                    Toast.makeText(AddUserActivity.this, "Please fill out all fields", Toast.LENGTH_LONG).show();
                    return;
                }

                User new_user = new User(username_input_value, password_input_value, first_name_input_value, last_name_input_value);

                if (userDao.getAllUsers().isEmpty() || (userDao.getAccountByUsername(username_input_value ) == null)) {
                    userDao.insertUser(new_user);
                    Toast.makeText(AddUserActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddUserActivity.this, MainActivity.class);
                    startActivity(intent);
                    return;
                }

                Toast.makeText(AddUserActivity.this, "Account with selected username already exists", Toast.LENGTH_LONG).show();

            }
        });
    }

    private boolean fieldsAreEmpty(String username, String password, String first_name, String last_name) {
        return (username.isEmpty() || password.isEmpty() || first_name.isEmpty() || last_name.isEmpty());
    }

    private void getDatabase() {
        userDao = Room.databaseBuilder(this, UserDatabase.class, UserDatabase.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .userDao();
    }
}
