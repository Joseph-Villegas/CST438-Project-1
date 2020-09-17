package edu.csumb.vill4031.cst438_project_1.RoomDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CourseDao {
    @Insert
    void insertCourse(Course course);

    @Update
    void update(Course course);

    @Delete
    void delete(Course course);

    @Query("SELECT * FROM " + CourseDatabase.COURSE_TABLE + " WHERE title = :title")
    Course getCourseByTitle(String title);

    @Query(" DELETE FROM " + CourseDatabase.COURSE_TABLE)
    void deleteAllCourses();

    @Query("SELECT * FROM " + CourseDatabase.COURSE_TABLE)
    List<Course> getAllCourses();
}
