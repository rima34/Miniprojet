package com.example.miniprojet;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addValue {

    private DatabaseReference ref;

    public addValue()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        ref=db.getReference(QRvalue.class.getSimpleName());
    }

    public Task<Void> add(QRvalue value)
    {
        return ref.push().setValue(value);
    }
}
