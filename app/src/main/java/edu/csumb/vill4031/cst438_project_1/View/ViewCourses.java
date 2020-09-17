package edu.csumb.vill4031.cst438_project_1.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import edu.csumb.vill4031.cst438_project_1.R;
import edu.csumb.vill4031.cst438_project_1.RecyclerView.Adapter;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.Course;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.CourseDao;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.CourseDatabase;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.User;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.UserDao;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.UserDatabase;

public class ViewCourses extends AppCompatActivity {
    static ArrayList<User> Users = new ArrayList<User>();
    private static final String USER_ID_KEY = "edu.csumb.vill4031.cst438_project_1.userIDKey";
    private static final String PREFERENCES_KEY = "edu.csumb.vill4031.cst438_project_1.PREFERENCES_KEY";
    private static int UserID = -1;

    private CourseDao courseDao;
    private Course course;

    RecyclerView recyclerView;

    ArrayList<Integer> courseIdList = new ArrayList<>();
    static ArrayList<String> instructorList = new ArrayList<>();
    static ArrayList<String> titleList = new ArrayList<>();
    static ArrayList<String> descriptionList = new ArrayList<>();
    static ArrayList<String> endDateList = new ArrayList<>();
    static ArrayList<String> startDateList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_courses);

        wireUpDisplay();
        getDatabase();
        getTitle();
    }

    private void wireUpDisplay() {
        recyclerView = findViewById(R.id.recycler_courses);

        //initialize recyclerview
        Adapter adapter = new Adapter(this, courseIdList, instructorList, titleList, descriptionList, endDateList, startDateList);
        recyclerView.setAdapter(adapter);;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void addCourseId(int course_id) {
        courseIdList.add(course_id);
    }

    public static void addInstructor(String instructor) {
        instructorList.add(instructor);
    }

    public static void addTitle(String title) {
        titleList.add(title);
    }

    public static void addDescription(String description) {
        descriptionList.add(description);
    }

    public static void addEndDates(String end_date) {
        endDateList.add(end_date);
    }

    public static void addStartDates(String start_date) {
        startDateList.add(start_date);
    }

//    private Course getCourseTitle() {
//        return course = courseDao.getCourseByTitle(courseTitle);
//    }

    //method for setting up database
    public void getDatabase() {
        courseDao = Room.databaseBuilder(this, CourseDatabase.class, CourseDatabase.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .courseDao();
    }

    //method for switching to this activity
    public static Intent intentFactory(Context context, int user_id) {
        UserID = user_id;
        Intent intent = new Intent(context, ViewCourses.class);
        return intent;
    }

    //method used for creating toast messages
    public void toaster(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, 64);
        toast.show();
    }
}