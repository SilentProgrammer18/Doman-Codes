package DoMan.App;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class LogIn extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore firestore;
    EditText LogInEmail, LogInPassword;
    Button LogInButtonRedCross;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        LogInEmail = findViewById(R.id.LogEmail);
        LogInPassword = findViewById(R.id.LogPassword);
        LogInButtonRedCross = findViewById(R.id.LogRedcrossButton);

        LogInButtonRedCross.setOnClickListener(new View.OnClickListener() {
            @Override

            //Email and password must filled
            public void onClick(View view) {
                String LogEmail, LogPassword;

                LogEmail = String.valueOf(LogInEmail.getText());
                LogPassword = String.valueOf(LogInPassword.getText());

                if (TextUtils.isEmpty(LogEmail)) {
                    Toast.makeText(LogIn.this, "input email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(LogPassword)) {
                    Toast.makeText(LogIn.this, "input password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(LogEmail, LogPassword)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(getApplicationContext(), "Log In Succesful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(LogIn.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });

                //From LogIn to Register
                TextView textView = findViewById(R.id.Register);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Create Intent to start SecondActivity
                        Intent intent = new Intent(LogIn.this, Registration.class);
                        startActivity(intent);
                    }
                });

                //From LogIn to Forgot Password
                TextView textView1 = findViewById(R.id.ForgotPassword);
                textView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Create Intent to start SecondActivity
                        Intent intent = new Intent(LogIn.this, ForgotPassword.class);
                        startActivity(intent);
                    }
                });


            }
        }