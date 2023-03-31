package com.example.cv_mate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Profile extends AppCompatActivity {

    TextView personalDetail, profession, skills, jobexp, achievements;
    TextInputEditText phone, dob, hobbies, description, education, college, extra_course, title, s_description, experience, achievement;
    AppCompatButton personalSubmitButton, professionSubmitButton, skillSubmitButton, jobSubmitButton, achieveSubmitButton;
    FirebaseAuth mAuth;
    DatabaseReference db;
    TextView profileEmail,profileName,profilePhone,profileDob,profileHobbies,profileDescription,profileEducation,profileCollege,profileExtra,profileSkills,profileFresher,profileAchievements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        personalDetail = findViewById(R.id.profile_personal_detail);
        profession = findViewById(R.id.profile_profession);
        skills = findViewById(R.id.profile_skills);
        jobexp = findViewById(R.id.profile_jobexp);
        achievements = findViewById(R.id.profile_achievements);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference().child("User");

        profilePhone = findViewById(R.id.profile_page_phoneNumber);
        profileName=findViewById(R.id.profile_page_name);
        profileEmail=findViewById(R.id.profile_page_email);
        profileDob = findViewById(R.id.profile_page_dob);
        profileHobbies = findViewById(R.id.profile_page_hobbies);
        profileDescription = findViewById(R.id.profile_page_description);
        profileEducation = findViewById(R.id.profile_page_eduction);
        profileCollege = findViewById(R.id.profile_page_college);
        profileExtra = findViewById(R.id.profile_page_extraCourse);
        profileSkills = findViewById(R.id.profile_page_mainSkills);
        profileFresher = findViewById(R.id.profile_page_job_xp);
        profileAchievements = findViewById(R.id.profile_page_achievement);

        DialogMenus();

        FetchData();
    }

    private void FetchData()
    {
        db.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren())
                {
                    if(snapshot1.getKey().toString().equals("phone"))
                    {
                        profilePhone.setText("Phone \n - "+snapshot1.getValue().toString());
                    }
                    else if(snapshot1.getKey().toString().equals("Email"))
                    {
                        profileEmail.setText(snapshot1.getValue().toString());
                    }
                    else if(snapshot1.getKey().toString().equals("Username"))
                    {
                        profileName.setText(snapshot1.getValue().toString());
                    }
                    else if(snapshot1.getKey().toString().equals("dob"))
                    {
                        profileDob.setText("Date Of Birth \n - "+snapshot1.getValue().toString());
                    }
                    else if(snapshot1.getKey().toString().equals("hobbies"))
                    {
                        profileHobbies.setText("Hobbies \n - "+snapshot1.getValue().toString());
                    }
                    else if(snapshot1.getKey().toString().equals("description"))
                    {
                        profileDescription.setText("Description  \n -"+snapshot1.getValue().toString());
                    }
                    else if(snapshot1.getKey().toString().equals("Education"))
                    {
                        profileEducation.setText("Education  \n - "+snapshot1.getValue().toString());
                    }
                    else if(snapshot1.getKey().toString().equals("College"))
                    {
                        profileCollege.setText("College  \n -"+snapshot1.getValue().toString());
                    }
                    else if(snapshot1.getKey().toString().equals("Extra_course"))
                    {
                        profileExtra.setText("Extra Course  \n -"+snapshot1.getValue().toString());
                    }
                    else if(snapshot1.getKey().toString().equals("Skills"))
                    {
                        profileSkills.setText(snapshot1.getValue().toString());
                    }
                    else if(snapshot1.getKey().toString().equals("Experience"))
                    {
                        profileFresher.setText(snapshot1.getValue().toString());
                    }
                    else if(snapshot1.getKey().toString().equals("Achievements"))
                    {
                        profileAchievements.setText(snapshot1.getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void DialogMenus() {
        Dialog dialog = new Dialog(Profile.this);
        personalDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.personal_detail_popup);
                phone = dialog.findViewById(R.id.personal_phone);
                dob = dialog.findViewById(R.id.personal_dob);
                hobbies = dialog.findViewById(R.id.personal_hobbies);
                description = dialog.findViewById(R.id.personal_description);
                personalSubmitButton = dialog.findViewById(R.id.personal_submit);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                personalDetails();
                dialog.show();
            }
        });


        profession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.profession_popup);
                education = dialog.findViewById(R.id.edu);
                college = dialog.findViewById(R.id.college);
                extra_course = dialog.findViewById(R.id.extra_course);
                professionSubmitButton = dialog.findViewById(R.id.pro_submit);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                professionDetails();
                dialog.show();
            }
        });

        skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.skills_popup);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                title = dialog.findViewById(R.id.skill_title);
                s_description = dialog.findViewById(R.id.skills_desc);
                skillSubmitButton = dialog.findViewById(R.id.skills_submit);
                skillDetails();
                dialog.show();
            }
        });

        jobexp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.job_exp_popup);
                experience = dialog.findViewById(R.id.exp);
                jobSubmitButton = dialog.findViewById(R.id.job_exp_submit);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                jobExpDetails();
                dialog.show();
            }
        });

        achievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.achievements_popup);
                achievement = dialog.findViewById(R.id.achieve);
                achieveSubmitButton = dialog.findViewById(R.id.achieve_submit);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                achievementsDetails();
                dialog.show();
            }
        });
    }

    private void personalDetails() {
        personalSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(phone.getText())) {
                    Toast.makeText(Profile.this, "Please Enter Your Phone Number", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(dob.getText())) {
                    Toast.makeText(Profile.this, "Please Enter Your Date of Birth", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(hobbies.getText())) {
                    Toast.makeText(Profile.this, "Please Enter Your hobbies", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(description.getText())) {
                    Toast.makeText(Profile.this, "Please Enter Your description", Toast.LENGTH_SHORT).show();
                    return;
                }

                HashMap<String, Object> data = new HashMap<>();
                data.put("phone", phone.getText().toString());
                data.put("dob", dob.getText().toString());
                data.put("hobbies", hobbies.getText().toString());
                data.put("description", description.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Profile.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Profile.this, "error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


    private void professionDetails() {
        professionSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(education.getText())) {
                    Toast.makeText(Profile.this, "Please Enter Your Education details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(college.getText())) {
                    Toast.makeText(Profile.this, "Please Enter Your College name", Toast.LENGTH_SHORT).show();
                    return;
                }


                HashMap<String, Object> data = new HashMap<>();
                data.put("Education", education.getText().toString());
                data.put("College", college.getText().toString());
                data.put("Extra_course", extra_course.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Profile.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Profile.this, "error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }


    private void skillDetails() {
        skillSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(title.getText())) {
                    Toast.makeText(Profile.this, "Please Enter Your Skill title", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(s_description.getText())) {
                    Toast.makeText(Profile.this, "Please Enter Your Skill description", Toast.LENGTH_SHORT).show();
                    return;
                }

                HashMap<String, Object> data = new HashMap<>();
                data.put("Skills", title.getText().toString() + "\n - " + s_description.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Profile.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Profile.this, "error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void jobExpDetails() {
        jobSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(experience.getText())) {
                    Toast.makeText(Profile.this, "Please Enter Your Job experience", Toast.LENGTH_SHORT).show();
                    return;
                }

                HashMap<String, Object> data = new HashMap<>();
                data.put("Experience", jobexp.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Profile.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Profile.this, "error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void achievementsDetails() {
        achievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(achievement.getText())) {
                    Toast.makeText(Profile.this, "Please Enter Your Achievements details", Toast.LENGTH_SHORT).show();
                    return;
                }


                HashMap<String, Object> data = new HashMap<>();
                data.put("Achievements", achievement.getText().toString());

                FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Profile.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Profile.this, "error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}


