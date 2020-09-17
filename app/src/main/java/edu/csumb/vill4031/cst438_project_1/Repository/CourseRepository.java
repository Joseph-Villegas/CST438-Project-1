package edu.csumb.vill4031.cst438_project_1.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;

import java.util.List;

import edu.csumb.vill4031.cst438_project_1.RoomDatabase.Course;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.CourseDao;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.CourseDatabase;

public class CourseRepository {
    private CourseDao courseDao;
    //private LiveData<List<Course>> allCourses;
    private List<Course> allCourses;

    public CourseRepository(Application application) {
        CourseDatabase db = CourseDatabase.getInstance(application);
        courseDao = db.courseDao();
        allCourses = courseDao.getAllCourses();

    }

    public void insertCourse(Course course) {
        new CourseRepository.InsertCourseAsyncTask(courseDao).execute(course);
    }

    public void update(Course course) {
        new CourseRepository.UpdateCourseAsyncTask(courseDao).execute(course);
    }

    public void delete(Course course) {
        new CourseRepository.DeleteCourseAsyncTask(courseDao).execute(course);
    }

    public void deleteAllCourses() {
        new CourseRepository.DeleteAllCoursesAsyncTask(courseDao).execute();
    }

    public List<Course> getAllCourses() {
        return allCourses;
    }



    private static class InsertCourseAsyncTask extends AsyncTask<Course, Void, Void> {
        private CourseDao courseDao;

        private InsertCourseAsyncTask(CourseDao courseDao) {
            this.courseDao = courseDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            //userDao.deleteAllUsers();
            courseDao.insertCourse(courses[0]);
            return null;
        }
    }

    private static class UpdateCourseAsyncTask extends AsyncTask<Course, Void, Void> {
        private CourseDao courseDao;

        private UpdateCourseAsyncTask(CourseDao courseDao) {
            this.courseDao = courseDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            courseDao.update(courses[0]);
            return null;
        }
    }

    private static class DeleteCourseAsyncTask extends AsyncTask<Course, Void, Void> {
        private CourseDao courseDao;

        private DeleteCourseAsyncTask(CourseDao courseDao) {
            this.courseDao = courseDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            courseDao.delete(courses[0]);
            return null;
        }
    }

    private static class DeleteAllCoursesAsyncTask extends AsyncTask<Course, Void, Void> {
        private CourseDao courseDao;

        private DeleteAllCoursesAsyncTask(CourseDao courseDao) {
            this.courseDao = courseDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            courseDao.deleteAllCourses();
            return null;
        }
    }
}
