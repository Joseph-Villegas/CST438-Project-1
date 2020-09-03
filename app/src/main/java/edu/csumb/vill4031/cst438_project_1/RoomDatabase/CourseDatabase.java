package edu.csumb.vill4031.cst438_project_1.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Course.class}, version = 1, exportSchema = false)
public abstract class CourseDatabase extends RoomDatabase {
    public abstract CourseDao courseDoa();

    private static CourseDatabase instance;

    public static CourseDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (CourseDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context, CourseDatabase.class, "COURSE_DATABASE")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return instance;
    }
}
