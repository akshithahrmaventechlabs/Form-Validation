package com.example.formvalidation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform login logic with validation
                if (validateLoginFields()) {
                    // Login successful, you can proceed with your logic
                    showToast("Login is Successful");

                    // For example, navigate to another activity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Optional: Close the current activity
                }
            }
        });
    }
    public void onSignUpButtonClick(View view) {
        // Navigate to the sign-up activity
        Intent intent = new Intent(LoginActivity.this, signup.class);
        startActivity(intent);
    }


    private boolean validateLoginFields() {
        String usernameValue = username.getText().toString().trim();
        String passwordValue = password.getText().toString().trim();

        if (TextUtils.isEmpty(usernameValue)) {
            showToast("Username is required");
            return false;
        }

        if (TextUtils.isEmpty(passwordValue)) {
            showToast("Password is required");
            return false;
        }

        // Your additional login validations, if any

        // All validations passed
        return true;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}