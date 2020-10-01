package com.example.dev_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ActivityLogin extends AppCompatActivity {
    Button login;
    EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //hide the title bar.
        setContentView(R.layout.activity_main);

        login=findViewById(R.id.loginButton);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( TextUtils.isEmpty(email.getText()) && TextUtils.isEmpty(password.getText()) ){
                    Toast.makeText(ActivityLogin.this,"All Fields are Required",Toast.LENGTH_LONG).show();
                }
                else {
                    Intent i = new Intent(ActivityLogin.this, ActivityWeather.class);
                    startActivity(i);
                }
            }
        });

}
}