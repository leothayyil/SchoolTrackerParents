package com.example.user.schooltrackerparents;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.schooltrackerparents.Retrofit.RetrofitHelper;
import com.google.gson.JsonElement;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView forgotPassword;
    Button loginBtn;
    EditText userName,password;
    String user_name,passw,action;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public  static  String MY_PREFS_NAME="DATA_SHARED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        forgotPassword=findViewById(R.id.login_forgot);
        loginBtn=findViewById(R.id.login_btn);
        userName=findViewById(R.id.login_userName);
        password=findViewById(R.id.login_password);

        prefs= (SharedPreferences) getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        editor=(SharedPreferences.Editor)getSharedPreferences(MY_PREFS_NAME,MODE_PRIVATE).edit();

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,ForgotPassActivity.class);
                startActivity(intent);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                action="login";

                if (userName.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Enter your user name!", Toast.LENGTH_SHORT).show();
                }else if (password.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Enter your password!", Toast.LENGTH_SHORT).show();

                }else {
                    user_name=userName.getText().toString();
                    passw=password.getText().toString();
                    login();
                }

            }
        });
    }

    private void login() {
        new RetrofitHelper(LoginActivity.this).getApi().login(action,user_name,passw).enqueue(
                new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response.body().toString());
                            String status=jsonObject.getString("status");
                            String user_id=jsonObject.getString("user_id");
                            String name=jsonObject.getString("student");
//                            String classa=jsonObject.getString("class");
//                            String divi=jsonObject.getString("division");

                            if (status.equals("Success")){
                                editor.putString("name",name);
                                editor.putString("user_id",user_id);
                                editor.apply();

                                Intent intent=new Intent(LoginActivity.this,ProgressReportActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "Check login details", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "Check login details", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {

                    }
                }
        );
    }
}
