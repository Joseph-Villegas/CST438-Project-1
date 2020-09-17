package edu.csumb.vill4031.cst438_project_1.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.csumb.vill4031.cst438_project_1.R;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<Integer> courseIdList;
    ArrayList<String> instructorList;
    ArrayList<String> titleList;
    ArrayList<String> descriptionList;
    ArrayList<String> endDateList;
    ArrayList<String> startDateList;

    public Adapter(Context ct, ArrayList<Integer> course_ids, ArrayList<String> instructors, ArrayList<String> titles, ArrayList<String> descriptions, ArrayList<String> endDates, ArrayList<String> startDates) {
        context = ct;
        courseIdList = course_ids;
        instructorList = instructors;
        titleList = titles;
        descriptionList = descriptions;
        endDateList = endDates;
        startDateList = startDates;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_row, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.courseId.setText(courseIdList.indexOf(position));
        holder.instructor.setText(instructorList.get(position));
        holder.title.setText(titleList.get(position));
        holder.description.setText(descriptionList.get(position));
        holder.endDate.setText(endDateList.get(position));
        holder.startDate.setText(startDateList.get(position));
    }

    @Override
    public int getItemCount() {
        return courseIdList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView courseId;
        TextView instructor;
        TextView title;
        TextView description;
        TextView endDate;
        TextView startDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseId = itemView.findViewById(R.id.recycler_courseId);
            instructor = itemView.findViewById(R.id.recycler_instructor);
            title = itemView.findViewById(R.id.recycler_title);
            description = itemView.findViewById(R.id.recycler_description);
            endDate = itemView.findViewById(R.id.recycler_endDate);
            startDate = itemView.findViewById(R.id.recycler_startDate);
        }
    }
}
