package io.github.grgadam.mukoromwebshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatButton;

public class RegisterActivity extends Activity {

    EditText usernameEditText;
    EditText passwordEditText;
    EditText passwordAgainEditText;
    EditText emailEditText;
    AppCompatButton registerButton;
    AppCompatButton loginButton;

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

    }

    private void showLoginActivity(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void register(View view) {
        //TODO
    }

}
