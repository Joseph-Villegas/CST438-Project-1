package edu.csumb.vill4031.cst438_project_1.RoomDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface AssignmentDao {
    @Insert
    void insertAssignment(Assignment assignment);

    @Update
    void update(Assignment assignment);

    @Delete
    void delete(Assignment assignment);

    @Query(" DELETE FROM " + AssignmentDatabase.ASSIGNMENT_TABLE)
    void deleteAllAssignments();

    @Query(" SELECT * FROM " + AssignmentDatabase.ASSIGNMENT_TABLE)
    LiveData<List<Assignment>> getAllAssignments();
}
