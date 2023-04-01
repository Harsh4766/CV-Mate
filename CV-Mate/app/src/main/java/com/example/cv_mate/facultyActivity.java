package com.example.cv_mate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class facultyActivity extends AppCompatActivity {

    TextView fullName,email;
    FirebaseAuth mAuth;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        fullName = findViewById(R.id.faculty_activity_name);
        email = findViewById(R.id.faculty_activity_email);
        db = FirebaseDatabase.getInstance().getReference().child("Admin");
        mAuth = FirebaseAuth.getInstance();
        db.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1: snapshot.getChildren())
                {
                    if(snapshot1.getKey().toString().equals("admin_name"))
                    {
                        fullName.setText(snapshot1.getValue().toString());
                    }
                    else if(snapshot1.getKey().toString().equals("Email"))
                    {
                        email.setText(snapshot1.getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}