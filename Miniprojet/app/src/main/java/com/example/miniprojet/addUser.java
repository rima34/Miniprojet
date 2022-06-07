package com.example.miniprojet;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addUser {
    private DatabaseReference ref;

    public addUser()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        ref=db.getReference(User.class.getSimpleName());
    }

    public Task<Void> add(User user)
    {
       return ref.push().setValue(user);
    }

}
