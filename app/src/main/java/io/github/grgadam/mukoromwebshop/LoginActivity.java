package io.github.grgadam.mukoromwebshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class LoginActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;
    AppCompatButton loginButton;
    AppCompatButton registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //get elements
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.regsiterButton);

        //register Button send to register Activity
        registerButton.setOnClickListener(this::showRegisterActivity);

        //login method on login Button click
        loginButton.setOnClickListener(this::login);
    }

    public void showRegisterActivity(View view) {
        Intent showRegisterIntent = new Intent(this, RegisterActivity.class);

        if (!emailEditText.getText().toString().isEmpty()) {
            showRegisterIntent.putExtra("email", emailEditText.getText().toString());
        }

        startActivity(showRegisterIntent);
    }

    private void login(View view) {

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}