package com.example.user.schooltrackerparents.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.schooltrackerparents.Pojo.Pojo_attReport;
import com.example.user.schooltrackerparents.R;

import java.util.ArrayList;

/**
 * Created by USER on 06-02-2018.
 */

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.MyViewHolder> {
    ArrayList<Pojo_attReport>reportList=new ArrayList<>();

    public AttendanceAdapter(ArrayList<Pojo_attReport> reportList) {
        this.reportList = reportList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_report,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pojo_attReport pojo=reportList.get(position);

        holder.date.setText(pojo.getDate());
        holder.remark.setText(pojo.getRemark());

    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date,remark;
        public MyViewHolder(View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.AR_mod_date);
            remark=itemView.findViewById(R.id.AR_mod_remark);

        }
    }
}
