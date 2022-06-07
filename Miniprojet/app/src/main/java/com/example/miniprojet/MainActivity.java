package com.example.miniprojet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private View decorView;
private Button connecter;
private EditText email;
    private EditText password;
    private FirebaseAuth auth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView)findViewById(R.id.text)).setOnClickListener(this);
        ((Button)findViewById(R.id.con)).setOnClickListener(this);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.mdp);

        auth=FirebaseAuth.getInstance();
        decorView= getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility==0)
                {
                    decorView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });
    }

    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus)
        {
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }

    private int hideSystemBars() {
      return  View.SYSTEM_UI_FLAG_LAYOUT_STABLE
              | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
              | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
              | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
              | View.SYSTEM_UI_FLAG_FULLSCREEN
              | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }


    @Override
    public void onClick(View v) {

        String Email = email.getText().toString().trim();
        String Pasword = password.getText().toString().trim();

        SharedPreferences prefs = getSharedPreferences("Login",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Email",Email);
        editor.putString("Password",Pasword);
        editor.putBoolean("isLoged",true);
        editor.apply();



        switch (v.getId())
        {
            case R.id.text:
                startActivity(new Intent(MainActivity.this,SignUp.class));
                break;

            case R.id.con:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String Email = email.getText().toString().trim();
        String Pasword = password.getText().toString().trim();

        if (Email.isEmpty()){
            email.setError("E-mail is required");
            email.requestFocus();
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
        {
            email.setError("Please provide a valid email");
            email.requestFocus();
            return;
        }
        if (Pasword.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
        }
        if (Pasword.length() <6)
        {
            password.setError("Min password length is 6 characters ");
            password.requestFocus();
            return;
        }
        auth.signInWithEmailAndPassword(Email,Pasword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                        startActivity(new Intent(MainActivity.this,ListeMatchs.class));
                }else {
                    Toast.makeText(MainActivity.this,
                            "Failed to login! Please check your credentials",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }}


