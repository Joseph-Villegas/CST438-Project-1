package edu.csumb.vill4031.cst438_project_1.RoomDatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    public static final String DB_NAME = "USER_DATABASE";
    public static final String USER_TABLE = "USER_TABLE";
    public abstract UserDao userDao();
    private static UserDatabase instance;

//    public static UserDatabase getDatabase(final Context context) {
//        if (instance == null) {
//            synchronized (UserDatabase.class) {
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                            context, UserDatabase.class, "USER_DATABASE")
//                            .fallbackToDestructiveMigration()
//                            .build();
//                }
//            }
//        }
//
//        return instance;
//    }

    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class, DB_NAME)
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
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userdao;

        private PopulateDbAsyncTask(UserDatabase db) {
            userdao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userdao.insertUser(new User());
            return null;
        }
    }

    //public abstract UserDao getUserDao();
}