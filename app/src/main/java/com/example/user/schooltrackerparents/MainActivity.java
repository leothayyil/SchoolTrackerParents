package com.example.user.schooltrackerparents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout prof_lin,attRepo,teacherMsgs,genMsgs_linea,progRepo,trackVehicle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prof_lin=findViewById(R.id.MA_linea_prof);
        attRepo=findViewById(R.id.MA_linea_attendanceRepo);
        teacherMsgs=findViewById(R.id.MA_linea_teacherMsgs);
        genMsgs_linea=findViewById(R.id.MA_linea_generalMsgs);
        progRepo=findViewById(R.id.MA_linea_progressRepo);
        trackVehicle=findViewById(R.id.MA_linea_track);
        prof_lin.setOnClickListener(this);
        attRepo.setOnClickListener(this);
        teacherMsgs.setOnClickListener(this);
        genMsgs_linea.setOnClickListener(this);
        progRepo.setOnClickListener(this);
        trackVehicle.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.MA_linea_prof:

                Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.MA_linea_attendanceRepo:
                Intent intent1=new Intent(MainActivity.this,AttendanceActivity.class);
                startActivity(intent1);
                break;
            case R.id.MA_linea_generalMsgs:
                Intent intent2=new Intent(MainActivity.this,GeneralMsgsActivity.class);
                startActivity(intent2);
                break;
            case R.id.MA_linea_progressRepo:
                Intent intent3=new Intent(MainActivity.this,ProgressReportActivity.class);
                startActivity(intent3);
                break;
            case R.id.MA_linea_track:
//                Intent intent4=new Intent(MainActivity.this,ProgressReportActivity.class);
//                startActivity(intent4);
                break;
                case R.id.MA_linea_teacherMsgs:
                Intent intent5=new Intent(MainActivity.this,TeacherMsgsActivity.class);
                startActivity(intent5);
                break;
        }
    }
}
