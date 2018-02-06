package com.example.user.schooltrackerparents.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.schooltrackerparents.ClickListner;
import com.example.user.schooltrackerparents.Pojo.Pojo_TMsgs;
import com.example.user.schooltrackerparents.R;
import com.example.user.schooltrackerparents.TeacherMsgsActivity;

import java.util.ArrayList;

/**
 * Created by USER on 06-02-2018.
 */

public class TMsg_Adapter extends RecyclerView.Adapter<TMsg_Adapter.MyViewHolder> {

    ArrayList<Pojo_TMsgs>arrayList=new ArrayList<>();
    Context context;
    ClickListner listner;
    public TMsg_Adapter(ArrayList<Pojo_TMsgs> tMsgsList, TeacherMsgsActivity teacherMsgsActivity,
                        ClickListner clickListner) {
        this.arrayList=tMsgsList;
        this.context=teacherMsgsActivity;
        this.listner=clickListner;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_tmsgs,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Pojo_TMsgs pojo=arrayList.get(position);

        holder.date.setText(pojo.getDate());
        holder.from.setText(pojo.getTeacher());
        holder.title.setText(pojo.getTitle());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title,from,date;

        public MyViewHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.TM_mod_tit);
            from=itemView.findViewById(R.id.TM_mod_from);
            date=itemView.findViewById(R.id.TM_mod_date);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos=getLayoutPosition()+1;
                    listner.onItemClick(String.valueOf(pos));
                }
            });
        }
    }
}
