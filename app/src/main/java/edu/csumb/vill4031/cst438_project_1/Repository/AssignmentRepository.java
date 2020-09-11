package edu.csumb.vill4031.cst438_project_1.Repository;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.Assignment;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.AssignmentDao;
import edu.csumb.vill4031.cst438_project_1.RoomDatabase.AssignmentDatabase;

public class AssignmentRepository {
    private AssignmentDao assignmentDao;
    private LiveData<List<Assignment>> allAssignments;

    public AssignmentRepository(Application application){
        AssignmentDatabase db = AssignmentDatabase.getInstance(application);
        assignmentDao = db.assignmentDao();
        allAssignments = assignmentDao.getAllAssignments();
    }

    public void insertAssignment(Assignment assignment){
        new AssignmentRepository.InsertAssignmentAsyncTask(assignmentDao).execute(assignment);
    }

    public void update(Assignment assignment){
        new AssignmentRepository.UpdateAssignmentAsyncTask(assignmentDao).execute(assignment);
    }

    public void delete(Assignment assignment){
        new AssignmentRepository.DeleteAssignmentAsyncTask(assignmentDao).execute(assignment);
    }

    public void deleteAllAssignments(){
        new AssignmentRepository.DeleteAllAssignmentsAsyncTask(assignmentDao).execute();
    }

    public LiveData<List<Assignment>> getAllAssignments(){
        return allAssignments;
    }

    private static class InsertAssignmentAsyncTask extends AsyncTask<Assignment, Void, Void> {
        private AssignmentDao assignmentDao;

        private InsertAssignmentAsyncTask(AssignmentDao assignmentDao){
            this.assignmentDao = assignmentDao;
        }

        @Override
        protected  Void doInBackground(Assignment... assignments){
            assignmentDao.insertAssignment(assignments[0]);
            return null;
        }
    }

    private static class UpdateAssignmentAsyncTask extends AsyncTask<Assignment, Void, Void>{
        private AssignmentDao assignmentDao;

        private UpdateAssignmentAsyncTask(AssignmentDao assignmentDao){
            this.assignmentDao = assignmentDao;
        }

        @Override
        protected Void doInBackground(Assignment... assignemnts){
            assignmentDao.update(assignemnts[0]);
            return null;
        }
    }

    private static class DeleteAssignmentAsyncTask extends AsyncTask<Assignment, Void, Void>{
        private AssignmentDao assignmentDao;

        private DeleteAssignmentAsyncTask(AssignmentDao assignmentDao){
            this.assignmentDao = assignmentDao;
        }

        @Override
        protected Void doInBackground(Assignment... assignments){
            assignmentDao.delete(assignments[0]);
            return null;
        }
    }

    private static class DeleteAllAssignmentsAsyncTask extends AsyncTask<Assignment, Void, Void>{
        private AssignmentDao assignmentDao;

        private DeleteAllAssignmentsAsyncTask(AssignmentDao assignmentDao){
            this.assignmentDao = assignmentDao;
        }

        @Override
        protected Void doInBackground(Assignment... assignments){
            assignmentDao.delete(assignments[0]);
            return null;
        }
    }
}
