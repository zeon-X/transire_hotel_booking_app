package aleeha.com.example.transire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    TextView tv_btn_login;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        tv_btn_login = (TextView) findViewById(R.id.tv_btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);

        tv_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iLogin = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(iLogin);
                finish();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iLogin = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(iLogin);
                finish();
            }
        });
    }
}