package com.example.user.schooltrackerparents;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.user.schooltrackerparents.Retrofit.RetrofitHelper;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralMsgsActivity extends AppCompatActivity {

    String TAG="loggg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_msgs);


        GeneralMsgs  generalMsgs=new GeneralMsgs();
        generalMsgs.execute();
    }
    public class GeneralMsgs extends AsyncTask{

        String action="general_message";
        String user_id="1";
        String classa="7";
        String division="7";

        @Override
        protected Object doInBackground(Object[] objects) {
            new RetrofitHelper(GeneralMsgs.this).getApi().getGeneralMsg(action,user_id,classa,division).enqueue(
                    new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            try {

                                JSONObject jsonObject=new JSONObject(response.body().toString());
                                String status=jsonObject.getString("status");
                                JSONArray jsonArray=jsonObject.getJSONArray("general_message");
                                for (int i=0;i<jsonArray.length();i++){
                                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                    String title=jsonObject1.getString("title");
                                    String message=jsonObject1.getString("message");

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonElement> call, Throwable t) {

                        }
                    }
            );
            return null;
        }
    }
}
