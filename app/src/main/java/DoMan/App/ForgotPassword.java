package DoMan.App;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    FirebaseAuth mAuth;


    TextView resetPassEmail;
    Button buttonResetPassword, CancelResetPasword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mAuth = FirebaseAuth.getInstance();
        resetPassEmail = findViewById(R.id.ForgotPasswordEmail);
        buttonResetPassword= findViewById(R.id.ConfirmEmailButton);
        CancelResetPasword = findViewById(R.id.cancelButton);





        buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ResetPass = String.valueOf(resetPassEmail.getText());
                
                        mAuth.sendPasswordResetEmail(ResetPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Intent intent = new Intent(ForgotPassword.this, EmailSentResetPassword.class);
                                    intent.putExtra("EMAIL", ResetPass);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(ForgotPassword.this, "Entered your Registered Email",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });


        //**************Back to Log In*************************
        CancelResetPasword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassword.this, LogIn.class);
                startActivity(intent);
            }
        });









    }


}

