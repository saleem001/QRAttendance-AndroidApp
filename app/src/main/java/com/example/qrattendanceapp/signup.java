package com.example.qrattendanceapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class signup extends AppCompatActivity {

    private EditText emailTV, passwordTV, locationTV, nameTV;
    private Button regBtn;

    String name, password, email, location;


    private FirebaseAuth mAuth;
    DatabaseReference ref;

    AlertDialog waiting_dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        ref= FirebaseDatabase.getInstance().getReference();


        waiting_dialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Loading")
                .setCancelable(false)
                .build();

        initializeUI();
    }

    //function to hide keyboard when touch on ui
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    private void registerNewUser() {


        email = emailTV.getText().toString();
        password = passwordTV.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;

        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        if(password.length()<6)
        {
            Toast.makeText(getApplicationContext(), "Password shall be more than 6 characters!", Toast.LENGTH_LONG).show();
        }
        name = nameTV.getText().toString();
        password = passwordTV.getText().toString();
        //  phone = phoneTV.getText().toString();
        email = emailTV.getText().toString();
        location = locationTV.getText().toString();


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            String userKey = mAuth.getCurrentUser().getUid();
                            insertUserData(name, email, location, userKey);
                            waiting_dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MakeSelection.class);
                            startActivity(intent);

                        } else {

                            Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void initializeUI() {
        emailTV = findViewById(R.id.signup_email);
        passwordTV = findViewById(R.id.signup_password);
        locationTV = findViewById(R.id.signup_location);
        nameTV = findViewById(R.id.signup_name);

    }

    public void register_user(View view) {
        waiting_dialog.show();
        registerNewUser();
    }

    public void insertUserData(String name, String email, String location, String userKey) {

        DatabaseReference usersRef = ref.child("Students").child(userKey);
        Map<String, Object> users = new HashMap<>();
        users.put("id", userKey);
        users.put("location", location);
        users.put("name", name);
        users.put("username", email);

        usersRef.updateChildren(users);
    }


}
