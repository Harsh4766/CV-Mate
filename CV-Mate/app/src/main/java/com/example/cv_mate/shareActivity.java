package com.example.cv_mate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class shareActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    shareAdapter adapter;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        db = FirebaseDatabase.getInstance().getReference().child("Admin").child("gN733mvQUsTChUq0r0Vzu5BZDE2");

        recyclerView = findViewById(R.id.share_activity_recyclerview);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(shareActivity.this);
        mLayoutManager.setReverseLayout(true);

        recyclerView.setLayoutManager(mLayoutManager);
        FirebaseRecyclerOptions<shareModel> options
                = new FirebaseRecyclerOptions.Builder<shareModel>()
                .setQuery(db,shareModel.class)
                .build();
        adapter = new shareAdapter(options,getApplicationContext(),shareActivity.this);

        adapter.startListening();

        recyclerView.setAdapter(adapter);
    }
}