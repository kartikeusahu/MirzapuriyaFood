package com.example.mirzapuriyafood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mirzapuriyafood.Models.Users;
import com.example.mirzapuriyafood.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp_Activity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(SignUp_Activity.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We're creating your account");

        // Apply edge-to-edge insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Register button click
        binding.buttonRegister.setOnClickListener(v -> {
            String user = binding.editTextText.getText().toString().trim();
            String ph = binding.editTextPhone.getText().toString().trim();
            String em = binding.editTextTextEmailAddress.getText().toString().trim();
            String pass = binding.editTextNumberPassword.getText().toString().trim();

            if (user.isEmpty() || ph.isEmpty() || em.isEmpty() || pass.isEmpty()) {
                Toast.makeText(SignUp_Activity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            progressDialog.show();
            auth.createUserWithEmailAndPassword(em, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                // Create user object
                                Users userObj = new Users(user, em, pass, ph);
                                String id = task.getResult().getUser().getUid();

                                // Save to Realtime Database
                                database.getReference().child("Users").child(id).setValue(userObj)
                                        .addOnSuccessListener(aVoid -> {
                                            Toast.makeText(SignUp_Activity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(SignUp_Activity.this, SignIn_Activity.class));
                                            finish();
                                        })
                                        .addOnFailureListener(e ->
                                                Toast.makeText(SignUp_Activity.this, "DB Error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                                        );

                            } else {
                                Toast.makeText(SignUp_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });

        // Go to login screen
        binding.gologin.setOnClickListener(v -> {
            Toast.makeText(SignUp_Activity.this, "Login Clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignUp_Activity.this, SignIn_Activity.class);
            startActivity(intent);
        });

        // Google login click
        binding.btnGoogle.setOnClickListener(v ->
                Toast.makeText(SignUp_Activity.this, "Google Login Clicked", Toast.LENGTH_SHORT).show());

        // Facebook login click
        binding.btnFacebook.setOnClickListener(v ->
                Toast.makeText(SignUp_Activity.this, "Facebook Login Clicked", Toast.LENGTH_SHORT).show());
    }
}