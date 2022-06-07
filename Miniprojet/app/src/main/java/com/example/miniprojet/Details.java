package com.example.miniprojet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;

import androidmads.library.qrgenearator.QRGEncoder;

public class Details extends AppCompatActivity {
    TextView date;
    TextView equipe1 ;
    TextView equipe2;
    TextView prix;
    TextView stade;
    TextView time;
    DatabaseReference database;
    private View decorView;
    ArrayList <Equipe>elist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        date = (TextView) findViewById(R.id.date);
        equipe1 = (TextView) findViewById(R.id.equipe1);
        equipe2 = (TextView) findViewById(R.id.equipe2);
        prix = (TextView) findViewById(R.id.prix);
        stade = (TextView) findViewById(R.id.stade);
        time = (TextView) findViewById(R.id.time);


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
        String d = "";
        String e1 = "";
        String e2 = "";
        String p = "";
        String s = "";
        String t = "";

        d = i.getStringExtra("Date");
        e1 = i.getStringExtra("Equipe1");
        e2 = i.getStringExtra("Equipe2");
        p = i.getStringExtra("Prix");
        s = i.getStringExtra("Stade");
        t = i.getStringExtra("Time");

        date.setText(d);
        equipe1.setText(e1);
        equipe2.setText(e2);
        prix.setText(p);
        stade.setText(s);
        time.setText(t);

        database = FirebaseDatabase.getInstance().getReference();
        database.child("matchs");
        database.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.exists()){
                    for (DataSnapshot dataSnapshot: snapshot.getChildren())
                    {
                        String key = dataSnapshot.getKey();
                        TextView tx = (TextView) findViewById(R.id.buy);
                        tx.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                    Intent intent = new Intent(Details.this,AchatTicket.class);
                                    intent.putExtra("keyMatch",key);
                                    intent.putExtra("dateMatch",i.getStringExtra("Date"));
                                    startActivity(intent);
                               // Toast.makeText(Details.this,key,Toast.LENGTH_LONG).show();
                            }
                        });  }





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