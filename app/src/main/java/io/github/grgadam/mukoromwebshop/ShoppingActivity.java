package io.github.grgadam.mukoromwebshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import io.github.grgadam.mukoromwebshop.Controller.ItemController;
import io.github.grgadam.mukoromwebshop.Model.Item;

public class ShoppingActivity extends AppCompatActivity {

    private ImageView profileImageView;
    private ImageView cartImageView;
    private RelativeLayout itemsRelativeLayout;
    private CollectionReference itemCollection;
    private ArrayList<Item> items;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemCollection = FirebaseFirestore.getInstance().collection("Items");
        items = new ArrayList<>();

        //-----STATUS AND NAVBAR-----
        //hide navbar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        //ertesites sav hatter beallit
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setStatusBarColor(getColor(R.color.pastel2));

        setContentView(R.layout.activity_shopping);

        //ImageViews
        profileImageView = findViewById(R.id.profileImageView);
        cartImageView = findViewById(R.id.cartImageView);

        //RelativeLayouts
        itemsRelativeLayout = findViewById(R.id.itemsRelativeLayout);

        //Listeners
        profileImageView.setOnClickListener(this::showProfile);

        //Show items
        itemCollection.get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                Item i = document.toObject(Item.class);
                items.add(i);
            }
            showItems();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void showProfile(View view) {
        Intent showShoppingIntent = new Intent(this, ProfileActivity.class);
        startActivity(showShoppingIntent);
    }

    private void showItems() {
        for (Item i : items) {

        }
    }

}