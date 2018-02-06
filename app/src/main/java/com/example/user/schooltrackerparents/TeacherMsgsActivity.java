package com.example.user.schooltrackerparents;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.schooltrackerparents.Retrofit.RetrofitHelper;

public class TeacherMsgsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_msgs);
        recyclerView=findViewById(R.id.TM_recycler);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
    private class  GetMessage extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
//            new RetrofitHelper(TeacherMsgsActivity.this).getApi().
            return null;
        }
    }
}
