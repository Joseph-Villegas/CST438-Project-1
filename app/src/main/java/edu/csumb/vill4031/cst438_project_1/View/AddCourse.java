package edu.csumb.vill4031.cst438_project_1.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Dao;
import androidx.room.Room;
import edu.csumb.vill4031.cst438_project_1.R;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.Assignment;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.AssignmentDao;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.AssignmentDatabase;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.Course;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.CourseDao;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.CourseDatabase;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.User;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.UserDao;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.UserDatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddCourse extends AppCompatActivity {
    static ArrayList<User> Users = new ArrayList<User>();
    private static final String USER_ID_KEY = "edu.csumb.vill4031.cst438_project_1.userIDKey";
    private static final String PREFERENCES_KEY = "edu.csumb.vill4031.cst438_project_1.PREFERENCES_KEY";
    private static int UserID = -1;

    private UserDao userDao;
    private CourseDao courseDao;
    private User user;

    // TODO
    // new instance of course and add to the data base with all the values of the fields
    // add courses to a list

    EditText instructor;
    EditText title;
    EditText description;
    EditText end_date;
    EditText start_date;
    Button add_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        wireUpDisplay();
        getUserDatabase();
        getCourseDatabase();
        getName();

    }

    public void wireUpDisplay() {
        instructor = findViewById(R.id.addInstructor);
        title = findViewById(R.id.addTitle);
        description = findViewById(R.id.addDescription);
        end_date = findViewById(R.id.addEndDate);
        start_date = findViewById(R.id.addStartDate);
        add_course = findViewById(R.id.addCourse);

        //initialize add course button
        add_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String instructor_input_value = instructor.getText().toString().trim();
                String title_input_value = title.getText().toString().trim();
                String description_input_value = description.getText().toString().trim();
                String end_date_input_value = end_date.getText().toString().trim();
                String start_date_input_value = start_date.getText().toString().trim();

                if (emptyFields(instructor_input_value, title_input_value, description_input_value, end_date_input_value, start_date_input_value)) {
                    toaster("Please fill out all fields");
                    return;
                }

                Course new_course = new Course(instructor_input_value, title_input_value, description_input_value, end_date_input_value, start_date_input_value);

                if (courseDao.getAllCourses().isEmpty() || (courseDao.getCourseByTitle(title_input_value) == null)) {
                    addFieldsToLists();
                    courseDao.insertCourse(new_course);
                    Toast.makeText(AddCourse.this, "Course added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddCourse.this, LandingPage.class);
                    startActivity(intent);
                    return;
                }
                Toast.makeText(AddCourse.this, "This course already exists", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void addFieldsToLists() {
        ViewCourses.addInstructor(instructor.getText().toString());
        ViewCourses.addTitle(title.getText().toString());
        ViewCourses.addDescription(description.getText().toString());
        ViewCourses.addEndDates(end_date.getText().toString());
        ViewCourses.addStartDates(start_date.getText().toString());
    }






    private boolean emptyFields(String instructor, String title, String description, String end_date, String start_date) {
        return (instructor.isEmpty() || title.isEmpty() || description.isEmpty() || end_date.isEmpty() || start_date.isEmpty());
    }


    //method for setting up user database
    public void getUserDatabase() {
        userDao = Room.databaseBuilder(this, UserDatabase.class, UserDatabase.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .userDao();
    }

    //method for setting up course database
    private void getCourseDatabase() {
        courseDao = Room.databaseBuilder(this, CourseDatabase.class, CourseDatabase.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .courseDao();
    }

    private User getName() {
        return user = userDao.getAccountById(UserID);
    }

    //method for switching to this activity
    public static Intent intentFactory(Context context, int user_id) {
        UserID = user_id;
        Intent intent = new Intent(context, AddCourse.class);
        return intent;
    }

    //method used for creating toast messages
    public void toaster(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 64);
        toast.show();
    }

}