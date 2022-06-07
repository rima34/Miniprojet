package com.example.miniprojet;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder>{

Context context;
ArrayList<Match> listMatch ;
ArrayList<Equipe>elist;
ArrayList<Stade>slist;
DatabaseReference database;
FirebaseDatabase firebaseDatabase;
TextView equipe1;




    public MatchAdapter(Context context, ArrayList<Match> listMatch) {
        this.context = context;
        this.listMatch = listMatch;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new MatchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {


        Match match = listMatch.get(position) ;

      //  holder.date.setText(match.getDate());
        holder.equipe1.setText(match.getEquipe1());
        holder.equipe2.setText(match.getEquipe2());
      //  holder.prix.setText(match.getPrix());
      //  holder.stade.setText(match.getStade());
      //  holder.time.setText(match.getTime());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(context, Details.class);
                i.putExtra("Date",match.getDate());
                i.putExtra("Equipe1",match.getEquipe1());
                i.putExtra("Equipe2",match.getEquipe2());
                i.putExtra("Prix",match.getPrix());
                i.putExtra("Stade",match.getStade());
                i.putExtra("Time",match.getTime());
                context.startActivity(i);
            }
        });

    }




    @Override
    public int getItemCount() {
        return listMatch.size();
    }

    public class MatchViewHolder extends RecyclerView.ViewHolder
    {
        TextView date;
        TextView equipe1 ;
        TextView equipe2;
        TextView prix;
        TextView stade;
        TextView time;
        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            equipe1=itemView.findViewById(R.id.equipe1);
            equipe2=itemView.findViewById(R.id.equipe2);
            prix=itemView.findViewById(R.id.prix);
            stade=itemView.findViewById(R.id.stade);
            time=itemView.findViewById(R.id.time);
        }
    }



}
