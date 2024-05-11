package io.github.grgadam.mukoromwebshop;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.LifecycleObserver;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements LifecycleObserver {

    private FirebaseAuth mAuth;

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
        mAuth = FirebaseAuth.getInstance();

        //-----STATUS AND NAVBAR-----
        //hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hide navbar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        //ertesites sav hatter beallit
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setStatusBarColor(getColor(R.color.background));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        //megjelenit
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
            LoginActivity instane = this;
            mAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent showShoppingIntent = new Intent(getInstance(), ShoppingActivity.class);
                                startActivity(showShoppingIntent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getInstance());
                                builder.setTitle("Hiba");
                                builder.setMessage("Hibás e-mail/jelszó!");
                                builder.setPositiveButton("Ok", (dialog, which) -> dialog.dismiss());
                                builder.show();
                            }
                        }
                    });

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
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            Toast.makeText(this, "Kérjük, jelentkezzen be vagy regisztráljon", Toast.LENGTH_SHORT).show();
        } else {
            Intent showShoppingIntent = new Intent(this, ShoppingActivity.class);
            startActivity(showShoppingIntent);
        }
    }

    public LoginActivity getInstance() {
        return this;
    }

}