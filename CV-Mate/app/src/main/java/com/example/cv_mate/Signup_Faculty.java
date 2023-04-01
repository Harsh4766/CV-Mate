package com.example.cv_mate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.UUID;

public class Signup_Faculty extends AppCompatActivity {

    TextView login;
    TextInputEditText emailTextView, passwordTextView, adminNameTextView;
    AppCompatButton sub;
    ImageView profile,plus;

    public Uri imageuri;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    private FirebaseAuth mAuth;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_faculty);

        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();

        login = findViewById(R.id.login);

        profile=findViewById(R.id.faculty_profile);
        plus=findViewById(R.id.faculty_plus);
        emailTextView = findViewById(R.id.sign_in_email_faculty);
        passwordTextView = findViewById(R.id.sign_in_pass_faculty);
        sub=findViewById(R.id.sign_in_faculty_sub);
        mAuth = FirebaseAuth.getInstance();
        adminNameTextView = findViewById(R.id.sign_in_faculty_name);
        db = FirebaseDatabase.getInstance().getReference().child("Admin");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Signup_Faculty.this,MainActivity.class);
                startActivity(i);
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePicture();
            }
        });

        SignIn();


    }

    private void choosePicture()
    {
        Intent i=new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            imageuri=data.getData();
            profile.setImageURI(imageuri);

        }
    }

    private void uploadPicture()
    {
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();

        final String randomKey= UUID.randomUUID().toString();
        StorageReference ref=storageReference.child("images/"+randomKey);

        ref.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        HashMap<String,Object> data = new HashMap<>();
                        data.put("imageUrl",uri.toString());
                        db.child(mAuth.getCurrentUser().getUid()).updateChildren(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    pd.dismiss();
                                    ProfilePage();
                                    Toast.makeText(getApplicationContext(),"Image uploaded",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    pd.dismiss();
                                }
                            }
                        });
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "Failed to upload", Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double progressPercent=(100.00 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                pd.setMessage("Progress "+(int) progressPercent+"%");
            }
        });

    }

    private void registerNewAdmin() {
        String email, password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();

        // Validations for input email and password

        if (TextUtils.isEmpty(adminNameTextView.getText())) {
            Toast.makeText(getApplicationContext(), "Please enter Full Name!!", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email!!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    HashMap<String, String> data = new HashMap();
                    data.put("userUid",mAuth.getCurrentUser().getUid().toString());
                    data.put("admin_name", adminNameTextView.getText().toString());
                    data.put("Email", email);

                    db.child(mAuth.getCurrentUser().getUid()).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Signup_Faculty.this, "Register Successful!", Toast.LENGTH_SHORT).show();
                                if(imageuri != null)
                                {
                                    uploadPicture();
                                }
                                else
                                {
                                    Toast.makeText(Signup_Faculty.this, "Please upload image", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(Signup_Faculty.this, "error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(Signup_Faculty.this, "error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void SignIn() {
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewAdmin();
            }
        });
    }

    private void ProfilePage() {
        Intent i=new Intent(getApplicationContext(),facultyActivity.class);
        startActivity(i);
        finish();
    }

}