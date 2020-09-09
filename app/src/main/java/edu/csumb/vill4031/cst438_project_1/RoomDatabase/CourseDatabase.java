package edu.csumb.vill4031.cst438_project_1.RoomDatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Course.class}, version = 1, exportSchema = false)
public abstract class CourseDatabase extends RoomDatabase {

    public static final String DB_NAME = "COURSE_DATABASE";
    public static final String COURSE_TABLE = "COURSE_TABLE";
    public abstract CourseDao courseDao();
    private static CourseDatabase instance;

//    public static CourseDatabase getDatabase(final Context context) {
//        if (instance == null) {
//            synchronized (CourseDatabase.class) {
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                            context, CourseDatabase.class, "COURSE_DATABASE")
//                            .fallbackToDestructiveMigration()
//                            .build();
//                }
//            }
//        }
//
//        return instance;
//    }

    public static synchronized CourseDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CourseDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new CourseDatabase.PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private CourseDao courseDao;

        private PopulateDbAsyncTask(CourseDatabase db) {
            courseDao = db.courseDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            courseDao.insertCourse(new Course());
            return null;
        }
    }

}
