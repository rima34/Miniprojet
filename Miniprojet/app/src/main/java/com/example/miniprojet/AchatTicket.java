package com.example.miniprojet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class AchatTicket extends AppCompatActivity {
    private View decorView;
    EditText ncarte,code;
    Button achat;

    DatabaseReference database ;
    addValue addV = new addValue();
    ImageView qr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achat_ticket);
           ncarte=(EditText) findViewById(R.id.nc);
           code=(EditText) findViewById(R.id.code);


        achat = (Button) findViewById(R.id.buy);
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


        Intent i = getIntent();
        String key = "";
        String date = "";
        String q="";
        key = i.getStringExtra("keyMatch");
        date = i.getStringExtra("dateMatch");
       // q=i.getStringExtra("qrv");



        achat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String c=ncarte.getText().toString().trim();
                String nc=code.getText().toString().trim();


                if (c.isEmpty()){
                    ncarte.setError("Check your card number");
                    ncarte.requestFocus();
                }
                if (nc.length() <6)
                {
                    code.setError("Check your code number ");
                    code.requestFocus();
                    return;
                }




                database = FirebaseDatabase.getInstance().getReference();
                database.child("User");
                database.addChildEventListener(new ChildEventListener() {

                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        if (snapshot.exists()){
                            for (DataSnapshot dataSnapshot: snapshot.getChildren())
                            {
                                String key = dataSnapshot.getKey();
                                QRvalue val= new QRvalue(i.getStringExtra("keyMatch") +
                                        i.getStringExtra("dateMatch"));
                                addV.add(val).addOnSuccessListener(suc->
                                        {
                                           String value = i.getStringExtra("keyMatch") +
                                                    i.getStringExtra("dateMatch")+key;

                                            Intent j= new Intent(AchatTicket.this,CodeQr.class);
                                            j.putExtra("value",value);
                                            startActivity(j);

                                        }

                                );


                            }



                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
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