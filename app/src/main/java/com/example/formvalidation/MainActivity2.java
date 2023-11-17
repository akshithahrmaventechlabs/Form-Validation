package com.example.formvalidation;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Find the TextViews in the XML layout
        TextView textFirstName = findViewById(R.id.textFirstName);
        TextView textLastName = findViewById(R.id.textLastName);
        TextView textEmail = findViewById(R.id.textEmail);
        TextView textDOB = findViewById(R.id.textDOB);
        TextView textGender = findViewById(R.id.textGender);
        TextView textBuildingNo = findViewById(R.id.textBuildingNo);
        TextView textStreet = findViewById(R.id.textStreet);
        TextView textCity = findViewById(R.id.textCity);
        TextView textState = findViewById(R.id.textState);
        TextView textPincode = findViewById(R.id.textPincode);
        TextView textConatctNo = findViewById(R.id.textConatctNo);
        TextView textSkill = findViewById(R.id.textSkill);

        // Retrieve data from the intent
        Intent intent = getIntent();
        String firstName = intent.getStringExtra("FirstName");
        String lastName = intent.getStringExtra("LastName");
        String email = intent.getStringExtra("Email");
        String dob = intent.getStringExtra("DOB");
        boolean isMale = intent.getBooleanExtra("IsMale", false);
        boolean isFemale = intent.getBooleanExtra("IsFemale", false);
        String gender = isMale ? "Male" : (isFemale ? "Female" : "N/A");
        String buildingNo = intent.getStringExtra("BuildingNo");
        String street = intent.getStringExtra("Street");
        String city = intent.getStringExtra("City");
        String state = intent.getStringExtra("State");
        String pincode = intent.getStringExtra("PinCode");
        String contactNo = intent.getStringExtra("ContactNo");
        boolean knowsHTML = intent.getBooleanExtra("KnowsHTML", false);
        boolean knowsCSS = intent.getBooleanExtra("KnowsCSS", false);
        boolean knowsJS = intent.getBooleanExtra("KnowsJS", false);
        textFirstName.setText("FirstName: " + firstName);
        // Set the retrieved data in the TextViews
        textFirstName.setText(firstName);
        textLastName.setText(lastName);
        textEmail.setText(email);
        textDOB.setText(dob);
        textGender.setText(gender);
        textBuildingNo.setText(buildingNo);
        textStreet.setText(street);
        textCity.setText(city);
        textState.setText(state);
        textPincode.setText(pincode);
        textConatctNo.setText(contactNo);

        String skills = "";
        if (knowsHTML) {
            skills += "HTML, ";
        }
        if (knowsCSS) {
            skills += "CSS, ";
        }
        if (knowsJS) {
            skills += "JavaScript";
        }
        textSkill.setText(skills);
    }
}