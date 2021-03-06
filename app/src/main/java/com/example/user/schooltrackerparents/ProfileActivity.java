package com.example.user.schooltrackerparents;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.user.schooltrackerparents.Retrofit.RetrofitHelper;
import com.google.gson.JsonElement;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by USER on 05-02-2018.
 */

public class ProfileActivity extends AppCompatActivity {
    String action="profile";
    int user_Id=1;
    TextView name,classa,userName,phoneNumber;
    ProgressDialog dialog;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=findViewById(R.id.profile_name);
        classa=findViewById(R.id.profile_class);
        userName=findViewById(R.id.profile_username);
        phoneNumber=findViewById(R.id.profile_phoneNumber);
        dialog=new ProgressDialog(this);
        dialog.show();
        Toolbar toolbar = (Toolbar) findViewById(R.id.Pro_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        new RetrofitHelper(ProfileActivity.this).getApi().getProfile(action,user_Id).enqueue(
                new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response.body().toString());
                            String parentNamee=jsonObject.getString("parent_name");
                            String studentNamee=jsonObject.getString("student_name");

                            String classaa=jsonObject.getString("class");
                            String division=jsonObject.getString("division");
                            String phone=jsonObject.getString("phone");
                            String email=jsonObject.getString("email");
                            String user_name=jsonObject.getString("user_name");


                            name.setText(studentNamee+"("+parentNamee+")");
                            classa.setText(classaa+"/"+division);
                            phoneNumber.setText(phone);
                            userName.setText(user_name);
                            dialog.dismiss();



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {

                    }
                }
        );
    }
}
