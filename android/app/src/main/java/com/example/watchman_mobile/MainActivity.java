package com.example.watchman_mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    private EditText mEmailEntered, mPasswordEntered, mEtName;
    private Button mLoginButton, mRegisterButton;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.frameLayout);
        linearLayout.setAnimation(AnimationUtils.loadAnimation(this,R.anim.animation));

        mFirebaseAuth = FirebaseAuth.getInstance();

        mEmailEntered = findViewById(R.id.username);
        mEmailEntered.setAnimation(AnimationUtils.loadAnimation(this,R.anim.animation));
        mPasswordEntered = findViewById(R.id.password);
        mPasswordEntered.setAnimation(AnimationUtils.loadAnimation(this,R.anim.animation));

        mLoginButton = findViewById(R.id.login_button);
        mLoginButton.setAnimation(AnimationUtils.loadAnimation(this,R.anim.animation));
        //Login button clicked
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        mRegisterButton = findViewById(R.id.register_button);
        mRegisterButton.setAnimation(AnimationUtils.loadAnimation(this,R.anim.animation));
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null)
                {
                    String id = firebaseAuth.getCurrentUser().getUid();
                    Log.d("gettingid",id);
                    Intent newIntent = new Intent(MainActivity.this, HomeScreen.class);
                    startActivity(newIntent);
                }
            }
        };
    }

    private void login()
    {
        String email = mEmailEntered.getText().toString();
        String password = mPasswordEntered.getText().toString();

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
        {
            //signing in the user
            mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this, "Error Signing In!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(MainActivity.this, "Enter both email and password!", Toast.LENGTH_SHORT).show();
        }

    }

    private void SignUp() {
        String email = mEmailEntered.getText().toString();
        String password = mPasswordEntered.getText().toString();

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
        {
            //Registering user
            mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this, "Error Registering!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else{
            Toast.makeText(MainActivity.this, "Enter both email and password!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }
}