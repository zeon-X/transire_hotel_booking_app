package aleeha.com.example.transire;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView tv_register_btn;
    Button btn_login,btn_skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_register_btn = (TextView) findViewById(R.id.tv_register_btn);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_skip = (Button) findViewById(R.id.btn_skip);

        tv_register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iRegister = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(iRegister);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iHome = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(iHome);
                finish();
            }
        });

        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iHome = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(iHome);
                finish();
            }
        });

    }
}