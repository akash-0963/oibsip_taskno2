package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todoapp.Utils.DataBaseHelper;
import com.example.todoapp.Utils.UserDataHandler;

public class LoginActivity extends AppCompatActivity {

    private UserDataHandler myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText EUserId = findViewById(R.id.editTextUserId);
        EditText EUserPass = findViewById(R.id.editTextEnterPass);
        Button login = findViewById(R.id.button);
        Button signup = findViewById(R.id.buttonSignup);



        login.setOnClickListener(v -> {
            String userId = EUserId.getText().toString();
            String p = EUserPass.getText().toString();
            myDb = new UserDataHandler(LoginActivity.this);
            int userPass = Integer.parseInt(p);
            int Pass = myDb.getUserPass(userId);

            try {
                if (userPass == Pass){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("userId", userId);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Please Enter a Correct UserId/Password",
                            Toast.LENGTH_LONG).show();
                }

            }catch(NullPointerException e){
                Toast.makeText(LoginActivity.this, "No such user",
                        Toast.LENGTH_LONG).show();
            }

        });

        signup.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this,SignupActivity.class));
        });
    }
}