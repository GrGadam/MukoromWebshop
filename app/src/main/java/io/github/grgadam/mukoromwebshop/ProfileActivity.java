package io.github.grgadam.mukoromwebshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity  extends Activity {
    private FirebaseAuth mAuth;
    private ImageView backImageView;
    private TextView profileEmailTextView;
    private AppCompatButton logoutButton;
    private RelativeLayout rendelesRelativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //-----STATUS AND NAVBAR-----
        //hide navbar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        //ertesites sav hatter beallit
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setStatusBarColor(getColor(R.color.pastel2));

        setContentView(R.layout.activity_profile);

        //ImageViews
        backImageView = findViewById(R.id.backImageView);

        //TextViews
        profileEmailTextView = findViewById(R.id.profileEmailTextView);

        //Buttons
        logoutButton = findViewById(R.id.logoutButton);

        //RelativaLayouts
        rendelesRelativeLayout = findViewById(R.id.rendelesRelativeLayout);

        //setting user email
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        profileEmailTextView.setText(user.getEmail());


        //Listeners
        logoutButton.setOnClickListener(this::logout);
        backImageView.setOnClickListener(this::back);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent showShoppingIntent = new Intent(this, LoginActivity.class);
        startActivity(showShoppingIntent);
    }

    public void back(View view) {
        Intent showShoppingIntent = new Intent(this, ShoppingActivity.class);
        startActivity(showShoppingIntent);
    }

}
