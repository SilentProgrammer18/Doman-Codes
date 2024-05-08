package DoMan.App;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;




public class Registration extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseFirestore firestore;
    EditText RegisterEmail, RegisterPassword, RegisterConfirmPassword, RegisterNumber, RegisterfirstName,
            RegisterlastName, RegisterphoneNumber;

    Button RegisterButtonRedCross;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        TextInputLayout layoutPassword1 = findViewById(R.id.layoutPassword);
        TextInputLayout layoutConfirmPassword1 = findViewById(R.id.layoutConfirmPassword);
        TextInputLayout layoutEmail1 = findViewById(R.id.layoutEmail);

        RegisterNumber = findViewById(R.id.Number);
        RegisterfirstName = findViewById(R.id.firstName);
        RegisterlastName = findViewById(R.id.lastName);
        RegisterphoneNumber = findViewById(R.id.phoneNumber);

        RegisterEmail = findViewById(R.id.RegEmail);
        RegisterPassword = findViewById(R.id.RegPassword);
        RegisterConfirmPassword = findViewById(R.id.RegConfirmPassword);
        RegisterButtonRedCross = findViewById(R.id.RegisterButtonRedCross);


        RegisterButtonRedCross.setOnClickListener(new View.OnClickListener() {
            @Override

            //Email and password must filled
            public void onClick(View view) {
                String RegEmail, RegPassword, RegConfirmPassword, Number, firstName, lastName, phoneNumber;

                RegEmail = String.valueOf(RegisterEmail.getText());
                RegPassword = String.valueOf(RegisterPassword.getText());
                RegConfirmPassword = String.valueOf(RegisterConfirmPassword.getText());
                Number = String.valueOf(RegisterNumber.getText());
                firstName = String.valueOf(RegisterfirstName.getText());
                lastName = String.valueOf(RegisterlastName.getText());
                phoneNumber = String.valueOf(RegisterphoneNumber.getText());

                if (TextUtils.isEmpty(Number)) {
                    Toast.makeText(Registration.this, "input ID Number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(firstName)) {
                    Toast.makeText(Registration.this, "input First Name", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(lastName)) {
                    Toast.makeText(Registration.this, "input Last Name", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(phoneNumber)) {
                    Toast.makeText(Registration.this, "input phoneNumber", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(RegEmail)) {
                    Toast.makeText(Registration.this, "input email", Toast.LENGTH_SHORT).show();
                    return;

                }


                //****Password Requirements**********************************

                //input password
                if (TextUtils.isEmpty(RegPassword)) {
                    Toast.makeText(Registration.this, "input password", Toast.LENGTH_SHORT).show();
                    return;
                }

                //at least 1 upper case
                if (!RegPassword.matches(".*[A-Z].*")) {
                    Toast.makeText(Registration.this, "Complete password requirements",
                            Toast.LENGTH_SHORT).show();
                        return;
                }

                //at least 2digits
                int digitCount = 0;
                for (char c : RegPassword.toCharArray()) {
                    if (Character.isDigit(c))
                        digitCount++;
                }
                if (digitCount < 2) {
                    Toast.makeText(Registration.this, "Complete password requirements",
                            Toast.LENGTH_SHORT).show();;
                    return;
                }

                //at least 1 special character
                if (!RegPassword.matches(".*[!@#$%^&*(),.?'\":{}|<>].*")) {
                    Toast.makeText(Registration.this, "Complete password requirements",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                //password must atleast have 8characters
                if (RegPassword.length() < 8) {
                    Toast.makeText(Registration.this, "Complete password requirements",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                //****Password Requirements**********************************


                if (RegConfirmPassword.isEmpty() || !RegPassword.equals(RegConfirmPassword)){
                    Toast.makeText(Registration.this, "Confirm password is not equal to password", Toast.LENGTH_SHORT).show();
                    return;

                }
                //***********************************************************************





                mAuth.createUserWithEmailAndPassword(RegEmail, RegPassword)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if(true) {
                                        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(Registration.this, "Verify your Email.",
                                                            Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        });
                                    }

                                    if (task.isSuccessful()) {

                                            // Create Intent to start SecondActivity
                                            Intent intent = new Intent(Registration.this, RegistrationSuccess.class);
                                            startActivity(intent);
                                            finish();

                                    }
                                    else {
                                        // If sign in fails, display a message to the user.

                                        Toast.makeText(Registration.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                        });






            }
        });





        //***************** RegisterPassword Helper ****************************************************
        RegisterPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String RegPassword = s.toString();

                //at least 1 upper case
                if (!RegPassword.matches(".*[A-Z].*")) {
                    layoutPassword1.setHelperText("");
                    layoutPassword1.setError("Weak password! put at least 1 uppercase letter");
                    return;
                }

                //at least 2digits
                int digitCount = 0;
                for (char c : RegPassword.toCharArray()) {
                    if (Character.isDigit(c))
                        digitCount++;
                }
                if (digitCount < 2) {
                    layoutPassword1.setHelperText("");
                    layoutPassword1.setError("Weak password! put at least 2 digits");
                    return;
                }

                //at least 1 special character
                if (!RegPassword.matches(".*[!@#$%^&*(),.?'\":{}|<>].*")) {
                    layoutPassword1.setHelperText("");
                    layoutPassword1.setError("Weak password! put at least 1 special character");
                    return;
                }

                //password must at least have 8characters
                if (RegPassword.length() < 8) {
                    layoutPassword1.setHelperText("");
                    layoutPassword1.setError("Weak password! put at least 8character");
                    return;
                }
                else {
                    layoutPassword1.setHelperText("Strong Password");
                    layoutPassword1.setError("");
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //***************** Confirm Password Helper **********************
        RegisterConfirmPassword.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String RegPassword = RegisterPassword.getText().toString();
                String RegConfirmPassword = RegisterConfirmPassword.getText().toString();

            if (RegConfirmPassword.isEmpty() || !RegConfirmPassword.equals(RegPassword)){
                layoutConfirmPassword1.setHelperText("");
                layoutConfirmPassword1.setError("Password is not matched");
                return;
            }
            else{
                layoutConfirmPassword1.setHelperText("Password Matched");
                layoutConfirmPassword1.setError("");
            }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });







//-Back (From Register to LogIn)
        TextView textView = findViewById(R.id.back);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Intent to start SecondActivity
                Intent intent = new Intent(Registration.this, LogIn.class);
                startActivity(intent);
            }
        });


    }
}