package edu.csumb.vill4031.cst438_project_1.RoomDatabase;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = AssignmentDatabase.ASSIGNMENT_TABLE)
public class Assignment {

    @PrimaryKey(autoGenerate = true)
    private int assignment_id;

    private String details;
    private int max_score;
    private int earned_score;
    private String assigned_date;
    private String due_date;
    private int category_id;
    private int course_id;

    public Assignment(String details, int max_score, int earned_score, String assigned_date, String due_date, int category_id, int course_id) {
        this.details = details;
        this.max_score = max_score;
        this.earned_score = earned_score;
        this.assigned_date = assigned_date;
        this.due_date = due_date;
        this.category_id = category_id;
        this.course_id = course_id;
    }

    public Assignment(){
        this.details = "Assignment details";
        this.max_score = 100;
        this.earned_score = 0;
        this.assigned_date = "Assigned data";
        this.due_date = "Due date";
        this.category_id = 1;
        this.course_id = 1;
    }
    public int getAssignment_id() {
        return assignment_id;
    }

    public void setAssignment_id(int assignment_id) {
        this.assignment_id = assignment_id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getMax_score() {
        return max_score;
    }

    public void setMax_score(int max_score) {
        this.max_score = max_score;
    }

    public int getEarned_score() {
        return earned_score;
    }

    public void setEarned_score(int earned_score) {
        this.earned_score = earned_score;
    }

    public String getAssigned_date() {
        return assigned_date;
    }

    public void setAssigned_date(String assigned_date) {
        this.assigned_date = assigned_date;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
