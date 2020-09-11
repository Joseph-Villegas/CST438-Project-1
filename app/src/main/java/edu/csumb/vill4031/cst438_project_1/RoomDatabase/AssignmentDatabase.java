package edu.csumb.vill4031.cst438_project_1.RoomDatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Assignment.class}, version = 1, exportSchema = false)
public abstract class AssignmentDatabase extends RoomDatabase {

    public static final String DB_NAME ="ASSIGNMENT_DATABASE";
    public static final String ASSIGNMENT_TABLE = "ASSIGNMENT_TABLE";
    public abstract AssignmentDao assignmentDao();
    private static AssignmentDatabase instance;

    public static synchronized AssignmentDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AssignmentDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            new AssignmentDatabase.PopulateDbAsyncTask(instance).execute();
        }

    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private AssignmentDao assignmentDao;

        private PopulateDbAsyncTask(AssignmentDatabase db){
            assignmentDao = db.assignmentDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            assignmentDao.insertAssignment(new Assignment());
            return null;
        }
    }
}
