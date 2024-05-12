package io.github.grgadam.mukoromwebshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class ToolsActivity extends Activity {

    private ImageView backImageViewTools;
    private AppCompatButton addItemButton;
    private AppCompatButton deleteItemButton;
    private AppCompatButton modifyItemButton;

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

        setContentView(R.layout.activity_tools);

        //ImageViews
        backImageViewTools = findViewById(R.id.backImageViewTools);

        //Buttons
        addItemButton = findViewById(R.id.addItemButton);
        deleteItemButton = findViewById(R.id.deleteItemButton);
        modifyItemButton = findViewById(R.id.modifyItemButton);

        //Listeners

        backImageViewTools.setOnClickListener(this::goBack);
        addItemButton.setOnClickListener(this::showAddItemActivity);
        deleteItemButton.setOnClickListener(this::showDeleteItemActivity);
        modifyItemButton.setOnClickListener(this::showModifyItemActivity);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void goBack(View view) {
        Intent showShoppingIntent = new Intent(this, ShoppingActivity.class);
        startActivity(showShoppingIntent);
    }

    private void showAddItemActivity(View view) {

    }

    private void showDeleteItemActivity(View view) {

    }

    private void showModifyItemActivity(View view) {

    }

}
