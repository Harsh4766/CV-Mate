package com.example.cv_mate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Profile extends AppCompatActivity {

    TextView personalDetail,profession,skills,jobexp,achievements;
    TextInputEditText phone,dob,hobbies,description;
    AppCompatButton personalSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        personalDetail = findViewById(R.id.profile_personal_detail);
        profession = findViewById(R.id.profile_profession);
        skills = findViewById(R.id.profile_skills);
        jobexp = findViewById(R.id.profile_jobexp);
        achievements = findViewById(R.id.profile_achievements);

        DialogMenus();

    }

    private void DialogMenus()
    {
        Dialog dialog=new Dialog(Profile.this);
        personalDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.personal_detail_popup);
                phone = dialog.findViewById(R.id.personal_phone);
                dob = dialog.findViewById(R.id.personal_dob);
                hobbies = dialog.findViewById(R.id.personal_hobbies);
                description = dialog.findViewById(R.id.personal_description);
                personalSubmitButton = dialog.findViewById(R.id.personal_submit);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                personalDetails();
                dialog.show();
            }
        });

        profession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.profession_popup);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });

        skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.skills_popup);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });

        jobexp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.job_exp_popup);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });

        achievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.achievements_popup);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });
    }

    private void personalDetails()
    {
        personalSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(phone.getText()))
                {
                    Toast.makeText(Profile.this, "Please Enter Your Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(dob.getText()))
                {
                    Toast.makeText(Profile.this, "Please Enter Your Date of Birth", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(hobbies.getText()))
                {
                    Toast.makeText(Profile.this, "Please Enter Your hobbies", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(description.getText()))
                {
                    Toast.makeText(Profile.this, "Please Enter Your description", Toast.LENGTH_SHORT).show();
                    return;
                }

                HashMap<String,Object> data = new HashMap<>();
                data.put("phone",phone.getText().toString());
                data.put("dob",dob.getText().toString());
                data.put("hobbies",hobbies.getText().toString());
                data.put("description",description.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Profile.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Profile.this, "error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}