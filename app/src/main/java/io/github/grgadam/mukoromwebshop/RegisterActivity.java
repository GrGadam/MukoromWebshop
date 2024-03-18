package io.github.grgadam.mukoromwebshop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends Activity {

    EditText usernameEditText;
    EditText passwordEditText;
    EditText passwordAgainEditText;
    EditText emailEditText;
    AppCompatButton registerButton;
    AppCompatButton loginButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //get elements
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordAgainEditText = findViewById(R.id.passwordAgainEditText);
        emailEditText = findViewById(R.id.emailEditText);
        registerButton = findViewById(R.id.regsiterButton);
        loginButton = findViewById(R.id.loginButton);

        //login Button send to Login Activity
        loginButton.setOnClickListener(this::showLoginActivity);

        //register on register Button click
        registerButton.setOnClickListener(this::register);

        //autofill username
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            usernameEditText.setText(extras.getString("username"));
        }

        mAuth = FirebaseAuth.getInstance();

    }

    private void showLoginActivity(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void register(View view) {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String passwordAgain = passwordAgainEditText.getText().toString();

        if (password.equals(passwordAgain)) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    //TODO
                    //startActivity(new Intent(this, MainActivity.class));
                } else {
                    new AlertDialog.Builder(RegisterActivity.this).setTitle("Hiba").setMessage("Váratlan hiba történt.").setPositiveButton("Ok", null).show();
                }
            });
        } else {
            new AlertDialog.Builder(RegisterActivity.this).setTitle("Hiba").setMessage("A jelszavak nem egyeznek!").setPositiveButton("Ok", null).show();
        }

    }

}
