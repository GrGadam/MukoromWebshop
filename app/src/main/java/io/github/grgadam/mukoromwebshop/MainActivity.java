package io.github.grgadam.mukoromwebshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void showRegisterActivity(View view) {
        Intent showRegisterIntent = new Intent(this, RegsiterActivity.class);
        startActivity(showRegisterIntent);
    }
}