package com.example.formvalidation;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Button btnSubmit;
    EditText FirstName, LastName, Email, DOB, BuildingNo, Street, City, State, PinCode, ContactNo;
    RadioButton Male, Female;
    CheckBox html, css, js;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSubmit = findViewById(R.id.btnSubmit);
        FirstName = findViewById(R.id.FirstName);
        LastName = findViewById(R.id.LastName);
        Email = findViewById(R.id.Email);
        DOB = findViewById(R.id.DOB);
        BuildingNo = findViewById(R.id.buildingNo);
        Street = findViewById(R.id.Street);
        City = findViewById(R.id.City);
        State = findViewById(R.id.State);
        PinCode = findViewById(R.id.PinCode);
        ContactNo = findViewById(R.id.ContactNo);
        Male = findViewById(R.id.Male);
        Female = findViewById(R.id.Female);
        html = findViewById(R.id.HTML);
        css = findViewById(R.id.CSS);
        js = findViewById(R.id.JS);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndOpenMainActivity2();
            }
        });
    }

    public void validateAndOpenMainActivity2() {
        // Get user input data
        String firstName = FirstName.getText().toString().trim();
        String lastName = LastName.getText().toString();
        String email = Email.getText().toString();
        String dob = DOB.getText().toString().trim();
        String pincode = PinCode.getText().toString();
        String contactNo = ContactNo.getText().toString();

        boolean invalid = false; // Flag to check for invalid input

        if (firstName.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please fill the First Name field", Toast.LENGTH_SHORT).show();
            invalid = true;
        } else if (!firstName.matches("^[a-zA-Z]+$")) {
            Toast.makeText(MainActivity.this, "Name should only contain alphabetic characters", Toast.LENGTH_SHORT).show();
            invalid = true;
        } else if (firstName.equals("") || firstName.equals("")) {
            invalid = true;
            // Display an error message using a TextView or Toast
        } else if (email.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please fill the Email field", Toast.LENGTH_SHORT).show();
            invalid = true;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(MainActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            invalid = true;
        } else if (contactNo.isEmpty() || contactNo.length() != 10) {
            Toast.makeText(MainActivity.this, "Please enter a valid 10-digit Contact No", Toast.LENGTH_SHORT).show();
            invalid = true;
        } else if (dob.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please fill the Date of Birth field", Toast.LENGTH_SHORT).show();
            invalid = true;
        } else if (pincode.isEmpty() || pincode.length() != 6) {
            Toast.makeText(MainActivity.this, "Please enter a valid 6-digit PinCode", Toast.LENGTH_SHORT).show();
            invalid = true;
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dobDate;

            try {
                dobDate = dateFormat.parse(dob);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dobDate);

                int year = calendar.get(Calendar.YEAR);

                if (year > Calendar.getInstance().get(Calendar.YEAR)) {
                    Toast.makeText(MainActivity.this, "Date of Birth cannot be in the future", Toast.LENGTH_SHORT).show();
                    invalid = true;
                } else if (year < 1970) {
                    Toast.makeText(MainActivity.this, "Date of Birth must be after 1970", Toast.LENGTH_SHORT).show();
                    invalid = true;
                }
            } catch (ParseException e) {
                Toast.makeText(MainActivity.this, "Invalid Date of Birth format. Please use dd/MM/yyyy", Toast.LENGTH_SHORT).show();
                invalid = true;
            }
        }

        if (!invalid) {
            // If all validation checks pass, proceed to the next activity
            Intent intent = new Intent(this, MainActivity2.class);
            // Add the user input data as intent extras
            intent.putExtra("FirstName", firstName);
            intent.putExtra("LastName", lastName);
            intent.putExtra("Email", email);
            intent.putExtra("DOB", dob);
            intent.putExtra("BuildingNo", BuildingNo.getText().toString());
            intent.putExtra("Street", Street.getText().toString());
            intent.putExtra("City", City.getText().toString());
            intent.putExtra("State", State.getText().toString());
            intent.putExtra("PinCode", pincode);
            intent.putExtra("ContactNo", contactNo);

            // Add the selected gender
            boolean isMale = Male.isChecked();
            boolean isFemale = Female.isChecked();
            intent.putExtra("IsMale", isMale);
            intent.putExtra("IsFemale", isFemale);

            // Add the selected skills
            intent.putExtra("KnowsHTML", html.isChecked());
            intent.putExtra("KnowsCSS", css.isChecked());
            intent.putExtra("KnowsJS", js.isChecked());

            // Start the next activity
            startActivity(intent);

            btnSendPostRequestClicked();
        }
    }

    //this block of codes is handling the process of sending user inut data to a server
    //using the retrofit library in an android app
    private void btnSendPostRequestClicked() {
        // Get user input data
        String firstName = FirstName.getText().toString().trim();
        String lastName = LastName.getText().toString();
        String email = Email.getText().toString();
        String dob = DOB.getText().toString().trim();
        String pincode = PinCode.getText().toString();
        String contactNo = ContactNo.getText().toString();

        boolean isMale = Male.isChecked();
        boolean isFemale = Female.isChecked();
        boolean knowsHTML = html.isChecked();
        boolean knowsCSS = css.isChecked();
        boolean knowsJS = js.isChecked();

        // Create a User object with the user input data
        //all the information from the user input data is combined and
        //organized into a "user"object..like user a structured representation of the user's information
        User user = new User(
                firstName,
                lastName,
                email,
                dob,
                isMale ? "Male" : "Female",
                BuildingNo.getText().toString(),
                Street.getText().toString(),
                City.getText().toString(),
                State.getText().toString(),
                pincode,
                contactNo,
                (knowsHTML ? "HTML, " : "") + (knowsCSS ? "CSS, " : "") + (knowsJS ? "JavaScript" : "")
        );

        // Make the Retrofit request with the user data
        Apiinterface apiinterface = RetrofitClient.getRetrofitInstance().create(Apiinterface.class);

        //call is used to make an API requests to a server
        //the request is sent to an apiinterface which is an interface for making api calls
        Call<User> call = apiinterface.getUserInformation(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getDOB(),
                user.getBuildingNo(),
                user.getStreet(),
                user.getCity(),
                user.getState(),
                user.getPinCode(),
                user.getContactNo(),
                user.getGender().equals("Male"),
                user.getGender().equals("Female"),
                user.getSkill().contains("HTML"),
                user.getSkill().contains("CSS"),
                user.getSkill().contains("JavaScript")
        );

        //If the response is successful, it extracts the data from the
        // response and stores it in a variable called "responseData."
        //If the response is not successful (i.e., there was an issue
        // with the request or the server didn't provide the expected data),
        // it logs an error message using "Log.e"
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    // Handle the response from the server
                    User responseData = response.body();
                    // You can access the data from the response if needed
                } else {
                    Log.e(TAG, "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
            //The "onFailure" method is a callback that's executed when a
            // network request fails. It logs the error message using the Log class,
            // and the error message includes information about the failure, which can
            // be helpful for debugging and diagnosing issues in the app.
        });
    }
}
//The Callback<User> is an interface provided by Retrofit
//that allows you to define what should happen when the network
// call is successful or when there's an error.