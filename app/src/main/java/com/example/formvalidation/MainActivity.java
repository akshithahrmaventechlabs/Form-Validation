package com.example.formvalidation;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button saveData, retrieveData;
    private EditText FirstName, LastName, Email, DOB, BuildingNo, Street, City, State, PinCode, ContactNo;
    private TextView textview;
    private SharedPreferences sharedPreferences;

    private Calendar calendar;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        saveData = findViewById(R.id.saveData);
        retrieveData = findViewById(R.id.retrieveData);
        textview = findViewById(R.id.textView);

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Update the DOB EditText with the selected date
                                DOB.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndSaveData();
            }
        });

        retrieveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = sharedPreferences.getString("FirstName", "");
                String lastName = sharedPreferences.getString("LastName", "");
                String email = sharedPreferences.getString("Email", "");
                String dob = sharedPreferences.getString("DOB", "");
                String buildingNo = sharedPreferences.getString("BuildingNo", "");
                String street = sharedPreferences.getString("Street", "");
                String city = sharedPreferences.getString("City", "");
                String state = sharedPreferences.getString("State", "");
                String pinCode = sharedPreferences.getString("PinCode", "");
                String contactNo = sharedPreferences.getString("ContactNo", "");

                textview.setText("First Name: " + firstName +
                        "\nLast Name: " + lastName +
                        "\nEmail: " + email +
                        "\nDOB: " + dob +
                        "\nBuilding No: " + buildingNo +
                        "\nStreet: " + street +
                        "\nCity: " + city +
                        "\nState: " + state +
                        "\nPin Code: " + pinCode +
                        "\nContact No: " + contactNo);

                textview.setTextSize(16);
            }
        });
    }

    private void validateAndSaveData() {
        String firstName = FirstName.getText().toString().trim();
        String lastName = LastName.getText().toString().trim();
        String email = Email.getText().toString().trim();
        String dob = DOB.getText().toString().trim();
        String buildingNo = BuildingNo.getText().toString().trim();
        String street = Street.getText().toString().trim();
        String city = City.getText().toString().trim();
        String state = State.getText().toString().trim();
        String pinCode = PinCode.getText().toString().trim();
        String contactNo = ContactNo.getText().toString().trim();

        boolean invalid = false;

        if (firstName.isEmpty()) {
            showToast("Please fill the First Name field");
            invalid = true;
        } else if (!firstName.matches("^[a-zA-Z]+$")) {
            showToast("Name should only contain alphabetic characters");
            invalid = true;
        } else if (email.isEmpty()) {
            showToast("Please fill the Email field");
            invalid = true;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Please enter a valid email address");
            invalid = true;
        } else if (contactNo.isEmpty() || contactNo.length() != 10) {
            showToast("Please enter a valid 10-digit Contact No");
            invalid = true;
        } else if (dob.isEmpty()) {
            showToast("Please fill the Date of Birth field");
            invalid = true;
        } else if (pinCode.isEmpty() || pinCode.length() != 6) {
            showToast("Please enter a valid 6-digit PinCode");
            invalid = true;
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dobDate;

            try {
                dobDate = dateFormat.parse(dob);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dobDate);

                int dobYear = calendar.get(Calendar.YEAR);

                if (dobYear > Calendar.getInstance().get(Calendar.YEAR)) {
                    showToast("Date of Birth cannot be in the future");
                    invalid = true;
                } else if (dobYear < 1950) {
                    showToast("Date of Birth must be 1950 or later");
                    invalid = true;
                }
            } catch (ParseException e) {
                showToast("Invalid Date of Birth format. Please use dd/MM/yyyy");
                invalid = true;
            }
        }

        if (!invalid) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("FirstName", firstName);
            editor.putString("LastName", lastName);
            editor.putString("Email", email);
            editor.putString("DOB", dob);
            editor.putString("BuildingNo", buildingNo);
            editor.putString("Street", street);
            editor.putString("City", city);
            editor.putString("State", state);
            editor.putString("PinCode", pinCode);
            editor.putString("ContactNo", contactNo);
            editor.apply();

            showToast("Data Saved!");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}






















//package com.example.formvalidation;
//
//import android.app.DatePickerDialog;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Patterns;
//import android.view.View;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//
//public class MainActivity extends AppCompatActivity {
//
//    private Button saveData, retrieveData;
//    private EditText FirstName, LastName, Email, DOB, BuildingNo, Street, City, State, PinCode, ContactNo;
//    private TextView textview;
//    private SharedPreferences sharedPreferences;
//
//    private Calendar calendar;
//    private int year, month, day;
//
//    // Use these constants as keys for SharedPreferences
//    private static final String KEY_FIRST_NAME = "FirstName";
//    private static final String KEY_LAST_NAME = "LastName";
//    private static final String KEY_EMAIL = "Email";
//    private static final String KEY_DOB = "DOB";
//    private static final String KEY_BUILDING_NO = "BuildingNo";
//    private static final String KEY_STREET = "Street";
//    private static final String KEY_CITY = "City";
//    private static final String KEY_STATE = "State";
//    private static final String KEY_PIN_CODE = "PinCode";
//    private static final String KEY_CONTACT_NO = "ContactNo";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        FirstName = findViewById(R.id.FirstName);
//        LastName = findViewById(R.id.LastName);
//        Email = findViewById(R.id.Email);
//        DOB = findViewById(R.id.DOB);
//        BuildingNo = findViewById(R.id.buildingNo);
//        Street = findViewById(R.id.Street);
//        City = findViewById(R.id.City);
//        State = findViewById(R.id.State);
//        PinCode = findViewById(R.id.PinCode);
//        ContactNo = findViewById(R.id.ContactNo);
//        saveData = findViewById(R.id.saveData);
//        retrieveData = findViewById(R.id.retrieveData);
//        textview = findViewById(R.id.textView);
//
//        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
//        calendar = Calendar.getInstance();
//        year = calendar.get(Calendar.YEAR);
//        month = calendar.get(Calendar.MONTH);
//        day = calendar.get(Calendar.DAY_OF_MONTH);
//
//        DOB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
//                        new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                                // Update the DOB EditText with the selected date
//                                DOB.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
//                            }
//                        }, year, month, day);
//                datePickerDialog.show();
//            }
//        });
//
//        saveData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                validateAndSaveData();
//            }
//        });
//
//        retrieveData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                retrieveDataFromSharedPreferences();
//            }
//        });
//    }
//
//    private void validateAndSaveData() {
//        String firstName = FirstName.getText().toString().trim();
//        String lastName = LastName.getText().toString().trim();
//        String email = Email.getText().toString().trim();
//        String dob = DOB.getText().toString().trim();
//        String buildingNo = BuildingNo.getText().toString().trim();
//        String street = Street.getText().toString().trim();
//        String city = City.getText().toString().trim();
//        String state = State.getText().toString().trim();
//        String pinCode = PinCode.getText().toString().trim();
//        String contactNo = ContactNo.getText().toString().trim();
//
//        boolean invalid = false;
//
//        if (firstName.isEmpty()) {
//            showToast("Please fill the First Name field");
//            invalid = true;
//        } else if (!firstName.matches("^[a-zA-Z]+$")) {
//            showToast("Name should only contain alphabetic characters");
//            invalid = true;
//        } else if (email.isEmpty()) {
//            showToast("Please fill the Email field");
//            invalid = true;
//        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            showToast("Please enter a valid email address");
//            invalid = true;
//        } else if (contactNo.isEmpty() || contactNo.length() != 10) {
//            showToast("Please enter a valid 10-digit Contact No");
//            invalid = true;
//        } else if (dob.isEmpty()) {
//            showToast("Please fill the Date of Birth field");
//            invalid = true;
//        } else if (pinCode.isEmpty() || pinCode.length() != 6) {
//            showToast("Please enter a valid 6-digit PinCode");
//            invalid = true;
//        } else {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            Date dobDate;
//
//            try {
//                dobDate = dateFormat.parse(dob);
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTime(dobDate);
//
//                int dobYear = calendar.get(Calendar.YEAR);
//
//                if (dobYear > Calendar.getInstance().get(Calendar.YEAR)) {
//                    showToast("Date of Birth cannot be in the future");
//                    invalid = true;
//                } else if (dobYear < 1950) {
//                    showToast("Date of Birth must be 1950 or later");
//                    invalid = true;
//                }
//            } catch (ParseException e) {
//                showToast("Invalid Date of Birth format. Please use dd/MM/yyyy");
//                invalid = true;
//            }
//        }
//
//        if (!invalid) {
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString(KEY_FIRST_NAME, firstName);
//            editor.putString(KEY_LAST_NAME, lastName);
//            editor.putString(KEY_EMAIL, email);
//            editor.putString(KEY_DOB, dob);
//            editor.putString(KEY_BUILDING_NO, buildingNo);
//            editor.putString(KEY_STREET, street);
//            editor.putString(KEY_CITY, city);
//            editor.putString(KEY_STATE, state);
//            editor.putString(KEY_PIN_CODE, pinCode);
//            editor.putString(KEY_CONTACT_NO, contactNo);
//            editor.apply();
//
//            showToast("Data Saved!");
//        }
//    }
//
//    private void retrieveDataFromSharedPreferences() {
//        // Retrieve data from SharedPreferences
//        String firstName = sharedPreferences.getString(KEY_FIRST_NAME, "");
//        String lastName = sharedPreferences.getString(KEY_LAST_NAME, "");
//        String email = sharedPreferences.getString(KEY_EMAIL, "");
//        String dob = sharedPreferences.getString(KEY_DOB, "");
//        String buildingNo = sharedPreferences.getString(KEY_BUILDING_NO, "");
//        String street = sharedPreferences.getString(KEY_STREET, "");
//        String city = sharedPreferences.getString(KEY_CITY, "");
//        String state = sharedPreferences.getString(KEY_STATE, "");
//        String pinCode = sharedPreferences.getString(KEY_PIN_CODE, "");
//        String contactNo = sharedPreferences.getString(KEY_CONTACT_NO, "");
//
//        // Update the UI with retrieved data
//        textview.setText("First Name: " + firstName +
//                "\nLast Name: " + lastName +
//                "\nEmail: " + email +
//                "\nDOB: " + dob +
//                "\nBuilding No: " + buildingNo +
//                "\nStreet: " + street +
//                "\nCity: " + city +
//                "\nState: " + state +
//                "\nPin Code: " + pinCode +
//                "\nContact No: " + contactNo);
//
//        textview.setTextSize(16);
//    }
//
//    private void showToast(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }
//}








////The Callback<User> is an interface provided by Retrofit
////that allows you to define what should happen when the network
//// call is successful or when there's an error.
//
//
//
////If the response is successful, it extracts the data from the
//// response and stores it in a variable called "responseData."
////If the response is not successful (i.e., there was an issue
//// with the request or the server didn't provide the expected data),
//// it logs an error message using "Log.e"
////The "onFailure" method is a callback that's executed when a
//// network request fails. It logs the error message using the Log class,
//// and the error message includes information about the failure, which can
//// be helpful for debugging and diagnosing issues in the app.
////The Callback<User> is an interface provided by Retrofit
////that allows you to define what should happen when the network
//// call is successful or when there's an error.




//package com.example.formvalidation;
//
//        import androidx.appcompat.app.AppCompatActivity;
//
//        import android.app.DatePickerDialog;
//        import android.content.Intent;
//        import android.os.Bundle;
//        import android.util.Log;
//        import android.util.Patterns;
//        import android.view.View;
//        import android.widget.Button;
//        import android.widget.CheckBox;
//        import android.widget.DatePicker;
//        import android.widget.EditText;
//        import android.widget.RadioButton;
//        import android.widget.TextView;
//        import android.widget.Toast;
//
//        import java.text.ParseException;
//        import java.text.SimpleDateFormat;
//        import java.util.Calendar;
//        import java.util.Date;
//        import java.util.Locale;
//
//        import retrofit2.Call;
//        import retrofit2.Callback;
//        import retrofit2.Response;
//        import android.content.SharedPreferences;
//        import android.preference.PreferenceManager;
//
//public class MainActivity extends AppCompatActivity {
//
//    private static final String TAG = "MainActivity";
//    private Button btnSubmit;
//    private EditText FirstName, LastName, Email, DOB, BuildingNo, Street, City, State, PinCode, ContactNo;
//    private RadioButton Male, Female;
//    private CheckBox html, css, js;
//    private DatePickerDialog datePickerDialog;
//    private SharedPreferences sharedPreferences;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        // Initialize SharedPreferences
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//
//        initializeViews();
//        setupDatePicker();
//        setupSubmitButton();
//
//        // Load saved data (if any)
//        loadSavedData();
//    }
//
//    private void loadSavedData() {
//        // Load data from SharedPreferences and set it to corresponding views
//        FirstName.setText(sharedPreferences.getString("FirstName", ""));
//        LastName.setText(sharedPreferences.getString("LastName", ""));
//        Email.setText(sharedPreferences.getString("Email", ""));
//        DOB.setText(sharedPreferences.getString("DOB", ""));
//        BuildingNo.setText(sharedPreferences.getString("BuildingNo", ""));
//        Street.setText(sharedPreferences.getString("Street", ""));
//        City.setText(sharedPreferences.getString("City", ""));
//        State.setText(sharedPreferences.getString("State", ""));
//        PinCode.setText(sharedPreferences.getString("PinCode", ""));
//        ContactNo.setText(sharedPreferences.getString("ContactNo", ""));
//
//        // Load radio button state
//        Male.setChecked(sharedPreferences.getBoolean("Male", false));
//        Female.setChecked(sharedPreferences.getBoolean("Female", false));
//
//        // Load checkbox state
//        // html.setChecked(sharedPreferences.getBoolean("HTML", false));
//        // css.setChecked(sharedPreferences.getBoolean("CSS", false));
//        // js.setChecked(sharedPreferences.getBoolean("JavaScript", false));
//    }
//
//    private void saveDataToSharedPreferences() {
//        // Save data to SharedPreferences
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("FirstName", FirstName.getText().toString());
//        editor.putString("LastName", LastName.getText().toString());
//        editor.putString("Email", Email.getText().toString());
//        editor.putString("DOB", DOB.getText().toString());
//        editor.putString("BuildingNo", BuildingNo.getText().toString());
//        editor.putString("Street", Street.getText().toString());
//        editor.putString("City", City.getText().toString());
//        editor.putString("State", State.getText().toString());
//        editor.putString("PinCode", PinCode.getText().toString());
//        editor.putString("ContactNo", ContactNo.getText().toString());
//
//        // Save radio button state
//        editor.putBoolean("Male", Male.isChecked());
//        editor.putBoolean("Female", Female.isChecked());
//
//        // Save checkbox state
//        // editor.putBoolean("HTML", html.isChecked());
//        // editor.putBoolean("CSS", css.isChecked());
//        // editor.putBoolean("JavaScript", js.isChecked());
//
//        editor.apply();
//    }
//
//    private void initializeViews() {
//        btnSubmit = findViewById(R.id.btnSubmit);
//        FirstName = findViewById(R.id.FirstName);
//        LastName = findViewById(R.id.LastName);
//        Email = findViewById(R.id.Email);
//        DOB = findViewById(R.id.DOB);
//        BuildingNo = findViewById(R.id.buildingNo);
//        Street = findViewById(R.id.Street);
//        City = findViewById(R.id.City);
//        State = findViewById(R.id.State);
//        PinCode = findViewById(R.id.PinCode);
//        ContactNo = findViewById(R.id.ContactNo);
//        Male = findViewById(R.id.Male);
//        Female = findViewById(R.id.Female);
//
////        html = findViewById(R.id.HTML);
////        css = findViewById(R.id.CSS);
////        js = findViewById(R.id.JS);
//    }
//
//    private void setupDatePicker() {
//        // Initialize DatePickerDialog with current date as default
//        Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//        datePickerDialog = new DatePickerDialog(
//                this,
//                onDateSetListener,
//                year,
//                month,
//                day
//        );
//
//        // Prevent selecting future dates
//        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
//
//        // Set onClickListener for the DOB field
//        DOB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDatePicker(v);
//            }
//        });
//    }
//
//    private void setupSubmitButton() {
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                validateAndOpenMainActivity2();
//                saveDataToSharedPreferences(); // Save data before moving to the next activity
//                // Uncomment the next line when you have the actual Retrofit implementation
////                btnSendPostRequestClicked();
//            }
//        });
//    }
//
//    public void showDatePicker(View view) {
//        datePickerDialog.show();
//    }
//
//    private final DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//            // Update the EditText with the selected date
//            Calendar selectedDate = Calendar.getInstance();
//            selectedDate.set(year, month, day);
//
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
//            DOB.setText(dateFormat.format(selectedDate.getTime()));
//        }
//    };
//
//    private void validateAndOpenMainActivity2() {
//        String firstName = FirstName.getText().toString().trim();
//        String lastName = LastName.getText().toString();
//        String email = Email.getText().toString();
//        String dob = DOB.getText().toString().trim();
//        String pincode = PinCode.getText().toString();
//        String contactNo = ContactNo.getText().toString();
//
//        boolean invalid = false;
//
//        if (firstName.isEmpty()) {
//            showToast("Please fill the First Name field");
//            invalid = true;
//        } else if (!firstName.matches("^[a-zA-Z]+$")) {
//            showToast("Name should only contain alphabetic characters");
//            invalid = true;
//        } else if (email.isEmpty()) {
//            showToast("Please fill the Email field");
//            invalid = true;
//        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            showToast("Please enter a valid email address");
//            invalid = true;
//        } else if (contactNo.isEmpty() || contactNo.length() != 10) {
//            showToast("Please enter a valid 10-digit Contact No");
//            invalid = true;
//        } else if (dob.isEmpty()) {
//            showToast("Please fill the Date of Birth field");
//            invalid = true;
//        } else if (pincode.isEmpty() || pincode.length() != 6) {
//            showToast("Please enter a valid 6-digit PinCode");
//            invalid = true;
//        } else {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            Date dobDate;
//
//            try {
//                dobDate = dateFormat.parse(dob);
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTime(dobDate);
//
//                int year = calendar.get(Calendar.YEAR);
//
//                if (year > Calendar.getInstance().get(Calendar.YEAR)) {
//                    showToast("Date of Birth cannot be in the future");
//                    invalid = true;
//                } else if (year < 1970) {
//                    showToast("Date of Birth must be after 1970");
//                    invalid = true;
//                }
//            } catch (ParseException e) {
//                showToast("Invalid Date of Birth format. Please use dd/MM/yyyy");
//                invalid = true;
//            }
//        }
//
//        if (!invalid) {
//            startMainActivity2();
//            // Uncomment the next line when you have the actual Retrofit implementation
//            btnSendPostRequestClicked();
//        }
//    }
//
//    private void startMainActivity2() {
//        Intent intent = new Intent(this, MainActivity2.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        intent.putExtra("FirstName", FirstName.getText().toString());
//        intent.putExtra("LastName", LastName.getText().toString());
//        intent.putExtra("Email", Email.getText().toString());
//        intent.putExtra("Date Of Birth",DOB.getText().toString());
//        boolean isMale = Male.isChecked();
//        boolean isFemale = Female.isChecked();
//        String gender = isMale ? "Male" : (isFemale ? "Female" : "N/A");
//        intent.putExtra("Gender", gender);
//        intent.putExtra("BuildingNo", BuildingNo.getText().toString());
//        intent.putExtra("Street", Street.getText().toString());
//        intent.putExtra("City", City.getText().toString());
//        intent.putExtra("State", State.getText().toString());
//        intent.putExtra("PinCode", PinCode.getText().toString());
//        intent.putExtra("ContactNo", ContactNo.getText().toString());
//
//        startActivity(intent);
//    }
//
//    private void showToast(String message) {
//        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
//    }
//
//    // This block of code handles the process of sending user input data to a server
//    // using the Retrofit library in an Android app
//    private void btnSendPostRequestClicked() {
//        // Get user input data
//        String firstName = FirstName.getText().toString().trim();
//        String lastName = LastName.getText().toString();
//        String email = Email.getText().toString();
//        String dob = DOB.getText().toString().trim();
//        String pincode = PinCode.getText().toString();
//        String contactNo = ContactNo.getText().toString();
//
//        boolean isMale = Male.isChecked();
//        boolean isFemale = Female.isChecked();
//        boolean knowsHTML = html.isChecked();
//        boolean knowsCSS = css.isChecked();
//        boolean knowsJS = js.isChecked();
//
//        // Create a User object with the user input data
//        User user = new User(
//                firstName,
//                lastName,
//                email,
//                dob,
//                isMale ? "Male" : "Female",
//                BuildingNo.getText().toString(),
//                Street.getText().toString(),
//                City.getText().toString(),
//                State.getText().toString(),
//                pincode,
//                contactNo,
//                (knowsHTML ? "HTML, " : "") + (knowsCSS ? "CSS, " : "") + (knowsJS ? "JavaScript" : "")
//        );
//
//        // Make the Retrofit request with the user data
//        Apiinterface apiinterface = RetrofitClient.getRetrofitInstance().create(Apiinterface.class);
//
//        Call<User> call = apiinterface.getUserInformation(
//                user.getFirstName(),
//                user.getLastName(),
//                user.getEmail(),
//                user.getDOB(),
//                user.getBuildingNo(),
//                user.getStreet(),
//                user.getCity(),
//                user.getState(),
//                user.getPinCode(),
//                user.getContactNo(),
//                user.getGender().equals("Male"),
//                user.getGender().equals("Female"),
//                user.getSkill().contains("HTML"),
//                user.getSkill().contains("CSS"),
//                user.getSkill().contains("JavaScript")
//        );
//
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                if (response.isSuccessful()) {
//                    // Handle the response from the server
//                    User responseData = response.body();
//                    // You can access the data from the response if needed
//                } else {
//                    Log.e(TAG, "onResponse: " + response.code());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Log.e(TAG, "onFailure: " + t.getMessage());
//            }
//        });
//    }
//}
////The Callback<User> is an interface provided by Retrofit
////that allows you to define what should happen when the network
//// call is successful or when there's an error.
//
//
//
////If the response is successful, it extracts the data from the
//// response and stores it in a variable called "responseData."
////If the response is not successful (i.e., there was an issue
//// with the request or the server didn't provide the expected data),
//// it logs an error message using "Log.e"
////The "onFailure" method is a callback that's executed when a
//// network request fails. It logs the error message using the Log class,
//// and the error message includes information about the failure, which can
//// be helpful for debugging and diagnosing issues in the app.
////The Callback<User> is an interface provided by Retrofit
////that allows you to define what should happen when the network
//// call is successful or when there's an error.