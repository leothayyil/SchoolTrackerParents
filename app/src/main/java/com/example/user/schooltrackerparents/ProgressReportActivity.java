package com.example.user.schooltrackerparents;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.user.schooltrackerparents.Adapter.Progress_reportAdapter;
import com.example.user.schooltrackerparents.Pojo.Pojo_ProgressReport;
import com.example.user.schooltrackerparents.Retrofit.RetrofitHelper;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgressReportActivity extends AppCompatActivity {

    TextView name,classa;
    RecyclerView recyclerView;
    Spinner yearSpinner,examSpinner;
    Progress_reportAdapter adapter;
    ArrayList<Pojo_ProgressReport>reportList=new ArrayList<>();

    String[] year={"Year","2015","2016","2017","2018","2019","2020","2021",
            "2022","2023","2024","2025","2026"};
    String[] month={"Month","01","02","03","04","05","06","07",
            "08","09","10","11","12"};
    String TAG="loggg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_report);

        name=findViewById(R.id.PR_tv_name);
        classa=findViewById(R.id.PR_tv_class);
        recyclerView=findViewById(R.id.PR_recycler);
        yearSpinner=findViewById(R.id.PR_year_spinner);
        examSpinner=findViewById(R.id.PR_month_spinner);

        ArrayAdapter adapter=new ArrayAdapter(ProgressReportActivity.this,android.R.layout.
                simple_spinner_dropdown_item,year);
        yearSpinner.setAdapter(adapter);
        ArrayAdapter adapter2=new ArrayAdapter(ProgressReportActivity.this,android.R.layout.
                simple_spinner_dropdown_item,month);
        examSpinner.setAdapter(adapter2);

        getExams();
        getProgressReport();

        GetProgrsPage progrsPage=new GetProgrsPage();
        progrsPage.execute();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void getExams() {
        String action="exams";
        new RetrofitHelper(ProgressReportActivity.this).getApi().getExams(action)
                .enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                try {
                    JSONObject  jsonObject=new JSONObject(response.body().toString());
                    String status=jsonObject.getString("status");
                    JSONArray jsonArray=jsonObject.getJSONArray("exam_name");
                     for (int i=0;i<jsonArray.length();i++){
                         JSONObject jsonObject1=jsonArray.getJSONObject(i);
                         String exam_name=jsonObject1.getString("exam");
                         String id=jsonObject1.getString("id");

                     }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

            }
        });
    }

    private class GetProgrsPage extends AsyncTask {

        String action="progress_page";
        String userId="1";
        @Override
        protected Object doInBackground(Object[] objects) {

            new RetrofitHelper(ProgressReportActivity.this).getApi().getProgress_page(action,userId)
                    .enqueue(new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            try {
                                JSONObject jsonObject=new JSONObject(response.body().toString());
                                String status=jsonObject.getString("status");
                                String student=jsonObject.getString("student");
                                String classa=jsonObject.getString("class");
                                String division=jsonObject.getString("division");

//
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

    private void getProgressReport() {
        String action="progress_report";
        String exam="1";
        String year="2018";
        String userId="1";

        new RetrofitHelper(ProgressReportActivity.this).getApi().getProgressReport(action,userId,exam,year)
                .enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                        try {
                            JSONObject jsonObject=new JSONObject(response.body().toString());
                            String status=jsonObject.getString("status");
                            JSONArray jsonArray=jsonObject.getJSONArray("marks");
                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                String subject=jsonObject1.getString("subject");
                                String mark=jsonObject1.getString("mark");
                                String state=jsonObject1.getString("status");
                                Pojo_ProgressReport pojo=new Pojo_ProgressReport();
                                pojo.setMark(mark);
                                pojo.setStatus(state);
                                pojo.setSubject(subject);
                                reportList.add(pojo);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter=new Progress_reportAdapter(reportList);
                        recyclerView.setAdapter(adapter);
                    }
                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {

                    }
                });
    }
}
