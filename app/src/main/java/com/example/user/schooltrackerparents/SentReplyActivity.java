package com.example.user.schooltrackerparents;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.schooltrackerparents.Adapter.TMsg_Adapter;
import com.example.user.schooltrackerparents.Retrofit.RetrofitHelper;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SentReplyActivity extends AppCompatActivity {

    TextView  titleTV,msgTV,teacherTV,dateTV;
    EditText edtRply;
    Button sentBtn;
    static  ProgressDialog dialog;
    String message_IdStr;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_reply);
        titleTV=findViewById(R.id.SR_title);
        teacherTV=findViewById(R.id.SR_teacher);
        dateTV=findViewById(R.id.SR_date);
        msgTV=findViewById(R.id.SR_message);
        edtRply=findViewById(R.id.SR_edtReply);
        sentBtn=findViewById(R.id.SR_btnSent);
        dialog=new ProgressDialog(this);
        dialog.show();
        Toolbar toolbar = findViewById(R.id.SR_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ViewMsg showMsg=new ViewMsg();
        showMsg.execute();

        message_IdStr=getIntent().getStringExtra("msg_id");

        Toast.makeText(this, message_IdStr, Toast.LENGTH_SHORT).show();
        sentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sentReplay();
            }
        });

    }

    String actionReply="send_reply";
    String messageId="1";
    String title="hii its reply";
    String message="its the message!";

    private void  sentReplay(){
        new RetrofitHelper(SentReplyActivity.this).getApi().sentReply(actionReply,messageId,title,message)
                .enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response.body().toString());
                            String status=jsonObject.getString("status");
                            String message=jsonObject.getString("message");
                            Toast.makeText(SentReplyActivity.this, message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                    }
                });
    }

    private  class  ViewMsg extends AsyncTask {
        String action="teacher_message";
        String user_id="1";
        @Override
        protected Object doInBackground(Object[] objects) {
            new RetrofitHelper(SentReplyActivity.this).getApi().getMessages(action, user_id)
                    .enqueue(new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().toString());
                                String status = jsonObject.getString("status");
                                JSONArray jsonArray = jsonObject.getJSONArray("message");
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                    String id=jsonObject1.getString("id");
                                    String title=jsonObject1.getString("title");
                                    String teacher=jsonObject1.getString("teacher");
                                    String message=jsonObject1.getString("message");
                                    String date=jsonObject1.getString("date");
                                    titleTV.setText(title);
                                    msgTV.setText(message);
                                    teacherTV.setText(teacher);
                                    dateTV.setText(date);


                                    dialog.dismiss();

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
        }}
}
