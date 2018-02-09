package com.example.user.schooltrackerparents;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;

import com.example.user.schooltrackerparents.Adapter.GM_Adapter;
import com.example.user.schooltrackerparents.Retrofit.RetrofitHelper;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralMsgsActivity extends AppCompatActivity {
    ExpandableListView expandable;
    GM_Adapter listAdapter;
    ProgressDialog dialog;
    List<String> listDataHeader= new ArrayList<String>();
    List<String> listDataMsg= new ArrayList<String>();
    HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_msgs);
        expandable=findViewById(R.id.gm_expandable);
        dialog=new ProgressDialog(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.GM_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dialog.setTitle("Loading Messages...");
        dialog.show();

        GeneralBox genBox=new GeneralBox();
        genBox.execute();

        expandable.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

    }
    private  class  GeneralBox extends AsyncTask {

         String action="general_message";
        String user_id="1";
        String classa="7";
        String division="7";

        @Override
        protected Object doInBackground(Object[] objects) {
            new RetrofitHelper(GeneralMsgsActivity.this).getApi().getGeneralMsg(action,user_id,classa,division).enqueue(
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

                                    listDataHeader.add(title);
                                    listDataMsg.add(message);

                                    listDataChild.put(listDataHeader.get(i),listDataMsg);

                                    listAdapter = new GM_Adapter(GeneralMsgsActivity.this, listDataHeader,
                                            listDataChild);
                                    expandable.setAdapter(listAdapter);
                                    dialog.dismiss();
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
