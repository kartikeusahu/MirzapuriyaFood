package com.example.mirzapuriyafood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mirzapuriyafood.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn_Activity extends AppCompatActivity {

    ActivitySignInBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // ✅ Binding initialize karo
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(SignIn_Activity.this);
        progressDialog.setTitle("Logging In");
        progressDialog.setMessage("We're logging you in");



        // ✅ Login button click
        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                auth.signInWithEmailAndPassword(
                        binding.loginEmail.getText().toString(),
                        binding.loginPassword.getText().toString()
                ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(SignIn_Activity.this, Dashboard.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(SignIn_Activity.this,
                                    task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        // ✅ Agar user already login hai
//        if (auth.getCurrentUser() != null) {
//            Intent intent = new Intent(SignIn_Activity.this, Dashboard.class);
//            startActivity(intent);
//
//        }

        // ✅ SignUp text click
        binding.textSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(SignIn_Activity.this, SignUp_Activity.class);
            startActivity(intent);
        });
    }
}