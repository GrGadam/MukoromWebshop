package io.github.grgadam.mukoromwebshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AppCompatButton registerButton = findViewById(R.id.loginButton);
        registerButton.setOnClickListener(this::showLoginActivity);
    }

    private void showLoginActivity(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

}
