package edu.csumb.vill4031.cst438_project_1.RoomDatabase;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = CourseDatabase.COURSE_TABLE)
public class Course {

    @PrimaryKey(autoGenerate = true)
    private int course_id;

    private String instructor;
    private String title;
    private String description;
    private String start_date;
    private String end_date;

    public Course(String instructor, String title, String description, String start_date, String end_date) {
        this.instructor = instructor;
        this.title = title;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public Course() {
        this.instructor = "instructor";
        this.title = "course title";
        this.description = "test course";
        this.start_date = "0";
        this.end_date = "1";
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
