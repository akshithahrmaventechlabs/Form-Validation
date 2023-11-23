package com.example.formvalidation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    private EditText username, password, confirmPassword;
    private Button register, loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.user);
        password = findViewById(R.id.pass);
        confirmPassword = findViewById(R.id.pass1);
        register = findViewById(R.id.btnreg);
        loginButton = findViewById(R.id.btnlogin);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform registration logic with validation
                if (validateRegistrationFields()) {
                    // Registration successful, you can proceed with your logic
                    showToast("Registration is Successful");

                    // For example, navigate to another activity
                    Intent intent = new Intent(signup.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Optional: Close the current activity
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the login activity
                Intent intent = new Intent(signup.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateRegistrationFields() {
        String usernameValue = username.getText().toString().trim();
        String passwordValue = password.getText().toString().trim();
        String confirmPasswordValue = confirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(usernameValue)) {
            showToast("Username is required");
            return false;
        }

        if (TextUtils.isEmpty(passwordValue)) {
            showToast("Password is required");
            return false;
        }

        if (!passwordValue.equals(confirmPasswordValue)) {
            showToast("Passwords do not match");
            return false;
        }


        // All validations passed
        return true;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}




//package com.example.formvalidation;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//public class signup extends AppCompatActivity {
//
//    private EditText username, password, confirmPassword;
//    private Button register;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup);
//
//        username = findViewById(R.id.user);
//        password = findViewById(R.id.pass);
//        confirmPassword = findViewById(R.id.pass1);
//        register = findViewById(R.id.btnreg);
//
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Perform registration logic with validation
//                if (validateFields()) {
//                    // Registration successful, you can proceed with your logic
//                    showToast("Registration is Successful");
//
//                    // For example, navigate to another activity
//                    Intent intent = new Intent(signup.this, MainActivity.class);
//                    startActivity(intent);
//                    finish(); // Optional: Close the current activity
//                }
//            }
//        });
//    }
//
//    private boolean validateFields() {
//        String usernameValue = username.getText().toString().trim();
//        String passwordValue = password.getText().toString().trim();
//        String confirmPasswordValue = confirmPassword.getText().toString().trim();
//
//        if (TextUtils.isEmpty(usernameValue)) {
//            showToast("Username is required");
//            return false;
//        }
//
//        if (TextUtils.isEmpty(passwordValue)) {
//            showToast("Password is required");
//            return false;
//        }
//
//        if (TextUtils.isEmpty(confirmPasswordValue)) {
//            showToast("Confirm Password is required");
//            return false;
//        }
//
//        if (!passwordValue.equals(confirmPasswordValue)) {
//            showToast("Passwords do not match");
//            return false;
//        }
//
//        // Your additional registration validations, if any
//
//        // All validations passed
//        return true;
//    }
//
//    private void showToast(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }
//}