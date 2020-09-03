package edu.csumb.vill4031.cst438_project_1.RoomDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "course_table")
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
}
