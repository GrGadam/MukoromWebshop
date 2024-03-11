package io.github.grgadam.mukoromwebshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppCompatButton registerButton = findViewById(R.id.regsiterButton);
        registerButton.setOnClickListener(this::showRegisterActivity);
    }

    public void showRegisterActivity(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}