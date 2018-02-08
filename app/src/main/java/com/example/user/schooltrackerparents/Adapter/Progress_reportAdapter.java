package com.example.user.schooltrackerparents.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.schooltrackerparents.Pojo.Pojo_ProgressReport;
import com.example.user.schooltrackerparents.R;

import java.util.ArrayList;

/**
 * Created by USER on 08-02-2018.
 */

public class Progress_reportAdapter extends RecyclerView.Adapter<Progress_reportAdapter.MyViewHolder>{

    ArrayList<Pojo_ProgressReport> reportList;
    public Progress_reportAdapter(ArrayList<Pojo_ProgressReport> reportList) {
        this.reportList=reportList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_progress,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Pojo_ProgressReport pojo=reportList.get(position);
        holder.subject.setText(pojo.getSubject());
        holder.mark.setText(pojo.getMark());
        holder.status.setText(pojo.getStatus());
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView subject,mark,status;
        public MyViewHolder(View itemView) {
            super(itemView);
            subject=itemView.findViewById(R.id.progress_subject);
            mark=itemView.findViewById(R.id.progress_mark);
            status=itemView.findViewById(R.id.progress_status);

        }
    }
}
