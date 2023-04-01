package com.example.cv_mate;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class shareAdapter extends FirebaseRecyclerAdapter<shareModel,shareAdapter.shareViewHolder> {

    Context context;
    Activity activity;
    FirebaseAuth mAuh;
    DatabaseReference db;
    public shareAdapter(@NonNull FirebaseRecyclerOptions<shareModel> options, Context context, Activity activity) {
        super(options);
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected void onBindViewHolder(@NonNull shareViewHolder holder, int position, @NonNull shareModel model) {
        Glide.with(context).load(model.getImageUrl()).into(holder.profilePage);
//        HashMap<String,Object> data = new HashMap<>();
//        data.put("useruid",FirebaseAuth.getInstance().getCurrentUser().getUid().toString());
        holder.fullName.setText(model.getAdmin_name());
        holder.email.setText(model.getEmail());
/*
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("adminAchievement").child(model.getUserUid()).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(context, "successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
*/
    }

    @NonNull
    @Override
    public shareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.share_layout,parent,false);
        return new shareAdapter.shareViewHolder(view);
    }

    class shareViewHolder extends RecyclerView.ViewHolder{

        ImageView profilePage;
        TextView fullName,email;
        AppCompatButton share;

        public shareViewHolder(@NonNull View itemView) {
            super(itemView);

            profilePage = itemView.findViewById(R.id.share_image);
            fullName = itemView.findViewById(R.id.share_fullname);
            email = itemView.findViewById(R.id.share_email);
            share = itemView.findViewById(R.id.share_button);
        }

    }

}
