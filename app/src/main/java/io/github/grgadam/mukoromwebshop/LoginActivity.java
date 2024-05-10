package io.github.grgadam.mukoromwebshop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.LifecycleObserver;

public class LoginActivity extends AppCompatActivity implements LifecycleObserver {

    EditText emailEditText;
    EditText passwordEditText;
    AppCompatButton loginButton;
    AppCompatButton registerButton;

    TextView emailTextView;
    TextView passwordTextView;
    TextView registerTextView;

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

        setContentView(R.layout.activity_login);

        //EditTexts
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        //Buttons
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.regsiterButton);

        //TextViews
        emailTextView = findViewById(R.id.emailTextView);
        passwordTextView = findViewById(R.id.passwordTextView);
        registerTextView = findViewById(R.id.registerTextView);


        //animacio betoltese
        Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);

        //animacio inditasa
        emailEditText.startAnimation(slideUp);
        passwordEditText.startAnimation(slideUp);
        loginButton.startAnimation(slideUp);
        registerButton.startAnimation(slideUp);
        emailTextView.startAnimation(slideUp);
        passwordTextView.startAnimation(slideUp);
        registerTextView.startAnimation(slideUp);


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
        if (emailEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Hiba");
            builder.setMessage("Üres e-mail/jelszó!");
            builder.setPositiveButton("Ok", (dialog, which) -> dialog.dismiss());
            builder.show();
        } else {
            //TODO check successful login
            Intent showShoppingIntent = new Intent(this, ShoppingActivity.class);
            startActivity(showShoppingIntent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Kérjük, jelentkezzen be vagy regisztráljon", Toast.LENGTH_SHORT).show();
    }

}