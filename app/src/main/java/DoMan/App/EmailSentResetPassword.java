package DoMan.App;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class EmailSentResetPassword extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button DoneChangePassword;
    TextView textView, ResendEmailReset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_sent_reset_password);


        mAuth = FirebaseAuth.getInstance();
        DoneChangePassword = findViewById(R.id.DoneChangePass);
        ResendEmailReset = findViewById(R.id.ResendEmail);

        //From LogIn to Register
        textView = findViewById(R.id.BackForgotPassword);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Intent to start SecondActivity
                Intent intent = new Intent(EmailSentResetPassword.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

        DoneChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmailSentResetPassword.this, LogIn.class);
                startActivity(intent);
            }
        });

        ResendEmailReset.setOnClickListener(new View.OnClickListener() {
            String ResetPass = getIntent().getStringExtra("EMAIL");
            @Override
            public void onClick(View v) {
                mAuth.sendPasswordResetEmail(ResetPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(EmailSentResetPassword.this, "Email Resent",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(EmailSentResetPassword.this, "Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

}