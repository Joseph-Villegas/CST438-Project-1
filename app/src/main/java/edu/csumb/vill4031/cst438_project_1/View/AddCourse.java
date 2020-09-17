package edu.csumb.vill4031.cst438_project_1.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import edu.csumb.vill4031.cst438_project_1.R;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.Assignment;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.AssignmentDao;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.AssignmentDatabase;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.Course;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.CourseDao;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.CourseDatabase;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.User;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.UserDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCourse extends AppCompatActivity {
    // TODO
    // new instance of course and add to the data base with all the values of the fields
    // add courses to a list

    EditText instructor;
    EditText title;
    EditText description;
    EditText end_date;
    EditText start_date;
    EditText course_id;

    Button add_course;

    private CourseDao courseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        getDatabase();

        instructor = findViewById(R.id.addInstructor);
        title = findViewById(R.id.addTitle);
        description = findViewById(R.id.addDescription);
        end_date = findViewById(R.id.addEndDate);
        start_date = findViewById(R.id.addStartDate);
        course_id = findViewById(R.id.addCourseID);
        add_course = findViewById(R.id.addCourse);

        add_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String instructor_input_value = instructor.getText().toString().trim();
                String title_input_value = title.getText().toString().trim();
                String description_input_value = description.getText().toString().trim();
                String end_date_input_value = end_date.getText().toString().trim();
                String start_date_input_value = start_date.getText().toString().trim();
                //String course_id_input_value = course_id.getText().toString().trim();
                //int course_id_input_value = course_id.getId().toString().trim();

                // Convert the string value to integer to pass the course id in the database
                String course_id_input_value = course_id.getText().toString();
                int convert_course_id = Integer.parseInt(course_id_input_value);

                if (emptyFields(instructor_input_value, title_input_value, description_input_value, end_date_input_value, start_date_input_value, course_id_input_value)) {
                    Toast.makeText(AddCourse.this, "Filled out all the fields", Toast.LENGTH_LONG).show();
                    return;
                }

                Course new_course = new Course(instructor_input_value, title_input_value, description_input_value, end_date_input_value, start_date_input_value, convert_course_id);

                if (courseDao.getAllCourses() == null) {
                    courseDao.insertCourse(new_course);
                    Toast.makeText(AddCourse.this, "Course added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddCourse.this, MainActivity.class);
                    startActivity(intent);
                    return;
                }
                Toast.makeText(AddCourse.this, "This course already exists", Toast.LENGTH_LONG).show();
            }
        });

    }

    private boolean emptyFields(String instructor, String title, String description, String end_date,
                                String start_date, String course_id) {
        return (instructor.isEmpty() || title.isEmpty() || description.isEmpty() || end_date.isEmpty() || start_date.isEmpty() || course_id.isEmpty());
    }

    private void getDatabase() {
        courseDao = Room.databaseBuilder(this, CourseDatabase.class, CourseDatabase.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .courseDao();
    }

}