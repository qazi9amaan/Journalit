package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText userName;
    private EditText password;
    private Button login;
    private int counter=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(userName.getText().toString(),password.getText().toString());
            }
        });

    }
    private void validate(String userName,String userPassword){
        if((userName.equals("admin"))&&(userPassword.equals("abcd")))
        {
            Intent intent=new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
        else{
            counter--;
            Toast toast =Toast.makeText(getApplicationContext(),
                    "Try again "+counter+"attempts remaining",
                    Toast.LENGTH_SHORT);
            toast.show();
            if(counter==0)
            {
                login.setEnabled(false);
            }

        }


    }

}
