package com.example.user.schooltrackerparents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ForgotPassActivity extends AppCompatActivity {

    TextView alreadyLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        alreadyLogin=findViewById(R.id.forgotPass_signIn);

        alreadyLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ForgotPassActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
