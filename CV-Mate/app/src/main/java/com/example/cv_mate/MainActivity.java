package com.example.cv_mate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextView login;

    private TextInputEditText emailTextView, passwordTextView, userNameTextView;
    private AppCompatButton signIn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        login = findViewById(R.id.login);


        emailTextView = findViewById(R.id.sign_in_email);
        passwordTextView = findViewById(R.id.sign_in_pass);
        signIn = findViewById(R.id.sign_in);
        mAuth = FirebaseAuth.getInstance();
        userNameTextView = findViewById(R.id.sign_in_full_name);

        LoginPage();
        SignIn();
        AutoLogin();
    }

    private void registerNewUser()
    {
        String email, password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();

        // Validations for input email and password

        if(TextUtils.isEmpty(userNameTextView.getText()))
        {
            Toast.makeText(getApplicationContext(),"Please enter Full Name!!",Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),"Please enter email!!",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),"Please enter password!!",Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "Register Successful!", Toast.LENGTH_SHORT).show();
                    ProfilePage();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "error: " + task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void LoginPage()
    {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Login.class);
                startActivity(i);
            }
        });
    }

    private void SignIn()
    {
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });
    }

    private void ProfilePage()
    {
        Intent init = new Intent(getApplicationContext(),Profile.class);
        startActivity(init);
        finish();
    }

    private void AutoLogin()
    {
        if(mAuth.getCurrentUser() != null)
        {
            ProfilePage();
        }
    }
}