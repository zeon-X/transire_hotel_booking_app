package aleeha.com.example.transire;

import static androidx.fragment.app.FragmentManager.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;

public class RegistrationActivity extends AppCompatActivity {

    TextView tv_btn_login;
    Button btn_register;
    private FirebaseAuth mAuth;

    TextView et_email,et_pass,et_confirm_pass,et_name,et_phone;
    LinearLayout ll_registration_page;
    ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        et_email = findViewById(R.id.et_email);
        et_pass = findViewById(R.id.et_pass);
        et_confirm_pass = findViewById(R.id.et_confirm_pass);
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);

        ll_registration_page = findViewById(R.id.ll_registration_page);
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

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

                progressBar.setVisibility(View.VISIBLE);
                ll_registration_page.setVisibility(View.GONE);

                String email,password,confirmPassword,name,phone;
                email = et_email.getText().toString().trim();
                password = et_pass.getText().toString();
                confirmPassword = et_confirm_pass.getText().toString();
                name = et_name.getText().toString();
                phone = et_phone.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(RegistrationActivity.this, "Enter Email", Toast.LENGTH_SHORT).show(); return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(RegistrationActivity.this, "Enter Password", Toast.LENGTH_SHORT).show(); return;
                }
                if(!password.equals(confirmPassword)){
                    Toast.makeText(RegistrationActivity.this, "Put the same passwords", Toast.LENGTH_SHORT).show(); return;
                }

                //Toast.makeText(RegistrationActivity.this, email+password, Toast.LENGTH_SHORT).show();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                    //FirebaseUser user = mAuth.getCurrentUser();
                                    //updateUI(user);

                                    progressBar.setVisibility(View.GONE);
                                    ll_registration_page.setVisibility(View.VISIBLE);

                                    Intent iLogin = new Intent(RegistrationActivity.this, LoginActivity.class);
                                    startActivity(iLogin);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    progressBar.setVisibility(View.GONE);
                                    ll_registration_page.setVisibility(View.VISIBLE);
                                    Toast.makeText(RegistrationActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


            }
        });
    }


}