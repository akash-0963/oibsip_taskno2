package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todoapp.Model.UserInfoModel;
import com.example.todoapp.Utils.DataBaseHelper;
import com.example.todoapp.Utils.UserDataHandler;

public class SignupActivity extends AppCompatActivity {

    private UserDataHandler myDb = new UserDataHandler(SignupActivity.this);
    private DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText EUserID = findViewById(R.id.editTextUserId);
        EditText EEnterPass = findViewById(R.id.editTextEnterPass);
        EditText EReEnterPass = findViewById(R.id.editTextrenterpass);

        Button login = findViewById(R.id.buttonLogin);
        Button signup = findViewById(R.id.button2);

        signup.setOnClickListener(v -> {
            String userId = EUserID.getText().toString();
            int enterPass = Integer.parseInt(EEnterPass.getText().toString());
            int reEnterPass = Integer.parseInt(EReEnterPass.getText().toString());

            if(enterPass == reEnterPass){
                db= new DataBaseHelper(SignupActivity.this,userId);
                UserInfoModel User = new UserInfoModel();
                User.setUserId(userId);
                User.setPass(enterPass);
                myDb.insertUser(User);
                db = new DataBaseHelper(SignupActivity.this,userId);

                Intent intent = new Intent(SignupActivity.this,MainActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            } else {
                Toast.makeText(SignupActivity.this, "Password not same",
                        Toast.LENGTH_LONG).show();
            }
        });

        login.setOnClickListener(v -> {
            startActivity(new Intent(SignupActivity.this,LoginActivity.class));
        });
    }

}