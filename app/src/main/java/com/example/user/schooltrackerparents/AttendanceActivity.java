package com.example.user.schooltrackerparents;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.user.schooltrackerparents.Adapter.AttendanceAdapter;
import com.example.user.schooltrackerparents.Pojo.Pojo_attReport;
import com.example.user.schooltrackerparents.Retrofit.RetrofitHelper;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.user.schooltrackerparents.LoginActivity.MY_PREFS_NAME;

public class AttendanceActivity extends AppCompatActivity {

    String actionDivision="division";
    String actionClass="class";
    String actionAttendance="mark_attendance";
    SharedPreferences preferences;
    RecyclerView recycAttendance;
    Button submit;
    TextView tv_classa,tv_name,tv_divi;
    Spinner yearSpinner,monthSpinner;
    String[] year={"Year","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026"};
    String[] month={"Month","01","02","03","04","05","06","07","08","09","10","11","12"};
    String yearS,monthS;
    String userIdd;
    ArrayList <Pojo_attReport>reportList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        recycAttendance=findViewById(R.id.recycAttendanceTeach);
        tv_classa=findViewById(R.id.TA_tv_class);
        tv_name=findViewById(R.id.TA_tv_name);
        yearSpinner=findViewById(R.id.AA_year_spinner);
        monthSpinner=findViewById(R.id.AA_month_spinner);
        recycAttendance.setHasFixedSize(true);
        recycAttendance.setLayoutManager(new LinearLayoutManager(this));
        monthSpinner.setClickable(false);
        yearSpinner.setSelected(false);

        preferences=getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE);
        final String restoredTexts=preferences.getString("name","0");
        if (restoredTexts !=null){
            String namee = preferences.getString("name", "0");
            String classa = preferences.getString("class", "0");
            String divi=preferences.getString("division","0");
             userIdd=preferences.getString("user_id","0");

            tv_name.setText("Name : "+namee);
            GetAttendance attendance=new GetAttendance(actionAttendance,userIdd,classa,divi);
            attendance.execute();
        }
        ArrayAdapter <String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,year);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(adapter);

        ArrayAdapter<String>adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,month);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(adapter1);

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                monthSpinner.setClickable(true);
                yearS=yearSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                 monthS=monthSpinner.getSelectedItem().toString();
                 getAbsenties();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void getAbsenties() {
        String actionReport="attendance_report";
        new RetrofitHelper(AttendanceActivity.this).getApi().getAttReport(actionReport,userIdd,yearS,monthS)
                .enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response.body().toString());
                            String status=jsonObject.getString("status");


                            JSONArray jsonArray=jsonObject.getJSONArray("attendance");
                            for (int i=0;i<=jsonArray.length();i++){

                                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                String date=jsonObject1.getString("date");
                                String remark=jsonObject1.getString("remark");
                                Pojo_attReport pojo=new Pojo_attReport();
                                pojo.setDate(date);
                                pojo.setRemark(remark);
                                reportList.add(pojo);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        AttendanceAdapter adapter=new AttendanceAdapter(reportList);
                        recycAttendance.setAdapter(adapter);
                    }


                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                    }
                });
    }
    /*
        private class  GetDivision extends AsyncTask{

            @Override
            protected Object doInBackground(Object[] objects) {
                new RetrofitHelper(Teach_attendance_Activity.this).getApi().getDivision(actionDivision,classa)
                        .enqueue(new Callback<JsonElement>() {
                            @Override
                            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                                try {
                                    JSONObject jsonObject=new JSONObject(response.body().toString());
                                    String status=jsonObject.getString("status");

                                    JSONArray jsonArray=jsonObject.getJSONArray("division");
                                    for (int i=0;i<jsonArray.length(); i++){
                                        JSONObject jsonObject1=jsonArray.getJSONObject(i);

                                        int id=jsonObject1.getInt("id");
                                        int classa=jsonObject1.getInt("class");
                                        int division=jsonObject1.getInt("division");


                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<JsonElement> call, Throwable t) {

                            }
                        });
                return null;
            }
        }
        */
    /*
    private class GetClass extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {

            new RetrofitHelper(Teach_attendance_Activity.this).getApi().getClass(actionClass).enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    try {
                        JSONObject jsonObject=new JSONObject(response.body().toString());
                        String status = jsonObject.getString("status");
                        JSONArray jsonArray = jsonObject.getJSONArray("class_name");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            int id = jsonObject1.getInt("id");
                            String classa = jsonObject1.getString("class");

                            Class_Pojo pojo=new Class_Pojo();
                            pojo.setId(id);
                            pojo.setClassa(classa);
                            class_arrayList.add(pojo);

                            Toast.makeText(Teach_attendance_Activity.this, classa, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {

                }
            });

            return null;
        }
    }
    */
    private  class  GetAttendance extends AsyncTask {

        String action,userId,classa,div;
        public GetAttendance(String actionAttendance, String userIdd, String classa, String divi) {
            this.action=actionAttendance;
            this.userId=userIdd;
            this.classa=classa;
            this.div=divi;
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            new RetrofitHelper(AttendanceActivity.this).getApi().getAttendance(actionAttendance,
                    userId).enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    try {
                        JSONObject jsonObject=new JSONObject(response.body().toString());
                        String status = jsonObject.getString("status");
                        String student = jsonObject.getString("student");
                        String classa = jsonObject.getString("class");
                        String division=jsonObject.getString("division");

                        tv_classa.setText("Class : "+classa+"/"+division);
                        tv_divi.setText(classa+"/"+division);





//                        adapter=new TeacherAttendanceAdapter(Teach_attendance_Activity.this,students_arrayList);
//                        recycAttendance.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {

                }
            });
            return null;
        }
    }
}
