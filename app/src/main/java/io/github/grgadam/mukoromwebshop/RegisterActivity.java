package io.github.grgadam.mukoromwebshop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends Activity {

    EditText passwordEditText;
    EditText passwordAgainEditText;
    EditText emailEditText;
    AppCompatButton registerButton;
    AppCompatButton loginButton;

    TextView emailTextView;
    TextView passwordTextView;
    TextView passwordAgainTextView;
    TextView loginTextView;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ertesitessav es navbar eltuntetese
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        setContentView(R.layout.activity_register);

        //EditTexts
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordAgainEditText = findViewById(R.id.passwordAgainEditText);

        //Buttons
        registerButton = findViewById(R.id.regsiterButton);
        loginButton = findViewById(R.id.loginButton);

        //TextViews
        emailTextView = findViewById(R.id.emailTextView);
        passwordTextView = findViewById(R.id.passwordTextView);
        passwordAgainTextView = findViewById(R.id.passwordAgainTextView);
        loginTextView = findViewById(R.id.loginTextView);

        //login Button send to Login Activity
        loginButton.setOnClickListener(this::showLoginActivity);

        //register on register Button click
        registerButton.setOnClickListener(this::register);

        //autofill username
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            emailEditText.setText(extras.getString("email"));
        }

        mAuth = FirebaseAuth.getInstance();


        //animacio betoltese
        Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

        //animacio inditasa
        emailTextView.startAnimation(fadeIn);
        emailEditText.startAnimation(fadeIn);

        passwordTextView.startAnimation(fadeIn);
        passwordEditText.startAnimation(fadeIn);

        passwordAgainTextView.startAnimation(fadeIn);
        passwordAgainEditText.startAnimation(fadeIn);

        loginButton.startAnimation(fadeIn);
        loginTextView.startAnimation(fadeIn);
        registerButton.startAnimation(fadeIn);
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
