package com.example.user.schooltrackerparents;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.user.schooltrackerparents.Adapter.TMsg_Adapter;
import com.example.user.schooltrackerparents.Pojo.Pojo_TMsgs;
import com.example.user.schooltrackerparents.Retrofit.RetrofitHelper;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherMsgsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String TAG="loggg";
    String msgId;
    ArrayList <Pojo_TMsgs>tMsgsList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_msgs);
        recyclerView=findViewById(R.id.TM_recycler);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        GetMessage getMessage=new GetMessage();
        getMessage.execute();

     recyclerView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

         }
     });
    }
    private class  GetMessage extends AsyncTask{

        String action="teacher_message";
        String user_id="1";
        @Override
        protected Object doInBackground(Object[] objects) {
            new RetrofitHelper(TeacherMsgsActivity.this).getApi().getMessages(action,user_id)
                    .enqueue(new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            try {
                                JSONObject jsonObject=new JSONObject(response.body().toString());
                                String status=jsonObject.getString("status");
                                JSONArray jsonArray=jsonObject.getJSONArray("message");
                                for (int i=0;i<jsonArray.length();i++){

                                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                    String id=jsonObject1.getString("id");
                                    String title=jsonObject1.getString("title");
                                    String teacher=jsonObject1.getString("teacher");

                                    Pojo_TMsgs pojo =new Pojo_TMsgs();
                                    pojo.setId(id);
                                    pojo.setTeacher(teacher);
                                    pojo.setTitle(title);
                                    tMsgsList.add(pojo);

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            TMsg_Adapter adapter=new TMsg_Adapter(tMsgsList,
                                    TeacherMsgsActivity.this,new ClickListner(){

                                @Override
                                public void onItemClick(String value) {
                                    String position=value;
                                    Pojo_TMsgs pojo=tMsgsList.get(Integer.parseInt(position));
                                    msgId=pojo.getId();
                                    
                                }
                            });

                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<JsonElement> call, Throwable t) {

                        }
                    });
            return null;
        }
    }
}
