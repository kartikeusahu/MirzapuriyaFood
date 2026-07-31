package com.example.mirzapuriyafood;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button registerbtn, login;
    private FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Firebase init
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        // Agar user already login hai to direct Dashboard
        if (auth.getCurrentUser() != null) {
            Intent intent = new Intent(MainActivity.this, Dashboard.class);
            startActivity(intent);
            finish(); // back press par login screen na aaye
            return;
        }

        // Buttons
        registerbtn = findViewById(R.id.regbtn);
        login = findViewById(R.id.loginm);

        registerbtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignUp_Activity.class);
            startActivity(intent);
        });

        login.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignIn_Activity.class);
            startActivity(intent);
        });
    }
}