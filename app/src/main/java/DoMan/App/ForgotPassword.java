package DoMan.App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //From LogIn to Register
        TextView textView = findViewById(R.id.backForgotPassword);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Intent to start SecondActivity
                Intent intent = new Intent(ForgotPassword.this, LogIn.class);
                startActivity(intent);
            }
        });
    }

}

