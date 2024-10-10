package com.example.roomdatabaselibraryapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Auth extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);

        Button signUpButton = findViewById(R.id.buttonRegister);
        Button loginButton = findViewById(R.id.buttonLogin);

        signUpButton.setOnClickListener(v -> {
            // Handle sign up button click
            Intent intent = new Intent(Auth.this, MainActivity.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(v -> {
            // Handle login button click
            Intent intent = new Intent(Auth.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
