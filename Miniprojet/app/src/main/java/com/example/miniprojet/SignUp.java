package com.example.miniprojet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity  {
private FirebaseAuth mauth;
    private EditText nom;
    private EditText prenom;
    private EditText email;
    private EditText pass;
    private EditText cpass;
    private Button sign;
    private View decorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

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

        mauth=FirebaseAuth.getInstance();

        addUser addU = new addUser();

        nom=(EditText)findViewById(R.id.nom);
        prenom=(EditText)findViewById(R.id.prénom);
        email=(EditText)findViewById(R.id.email);
        //tel=(EditText)findViewById(R.id.tel);
        pass=(EditText)findViewById(R.id.mdp);
        cpass=(EditText)findViewById(R.id.cmdp);
        sign=(Button)findViewById(R.id.signup);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nom = nom.getText().toString().trim();
                String Prenom = prenom.getText().toString().trim();
                String Email = email.getText().toString().trim();
                String Mdp = pass.getText().toString().trim();
                String cmdp = cpass.getText().toString().trim();

                if (Nom.isEmpty())
                {
                    nom.setError("Name is required");
                }
                if (Prenom.isEmpty())
                {
                    prenom.setError("Name is required");
                }
                if (Email.isEmpty())
                {
                    email.setError("Name is required");
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
                {
                    email.setError("Please provide a valid email");
                    email.requestFocus();
                    return;
                }
                if (Mdp.isEmpty()){
                    pass.setError("Password is required");
                    pass.requestFocus();
                }
                if (Mdp.length() <6)
                {
                    pass.setError("Min password length is 6 characters ");
                    pass.requestFocus();
                    return;
                }


                User u= new User(Nom,Prenom,Email,Mdp,cmdp);
                addU.add(u).addOnSuccessListener(suc->
                        {
                            Intent i = new Intent(SignUp.this,ListeMatchs.class);
                            startActivity(i);
                        }).addOnFailureListener(er->
                {

                    Toast.makeText(SignUp.this,
                            "check your information"+er.getMessage(),Toast.LENGTH_LONG).show();
                });

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




}