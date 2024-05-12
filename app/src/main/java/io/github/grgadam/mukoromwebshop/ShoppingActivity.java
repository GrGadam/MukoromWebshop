package io.github.grgadam.mukoromwebshop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

import io.github.grgadam.mukoromwebshop.Model.Item;

public class ShoppingActivity extends AppCompatActivity {

    private ImageView profileImageView;
    private ImageView cartImageView;
    private LinearLayout itemsLinearLayout;
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
        itemsLinearLayout = findViewById(R.id.itemsLinearLayout);

        //Listeners
        profileImageView.setOnClickListener(this::showProfile);
        cartImageView.setOnClickListener(this::showCart);

        //Show items
        items.clear();
        itemCollection.get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                Item i = document.toObject(Item.class);
                assert i != null;
                i.setId(document.getId());
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

    private void showCart(View view) {
        Intent showShoppingIntent = new Intent(this, CartActivity.class);
        startActivity(showShoppingIntent);
    }


    private void showItems() {
        int layout_count = 0;
        int eltolas = 0;
        for (Item i : items) {
            LayoutInflater li = getLayoutInflater();
            View dynamicView = li.inflate(R.layout.dynamic_item_view, null);

            //Beállítjuk az értékeket
            //1. kép típus szerint
            GridLayout gl = dynamicView.findViewById(R.id.layout_0);
            gl.setId(View.generateViewId());

            layout_count++;
            ImageView iw = (ImageView) gl.getChildAt(0);
            switch (i.getType()) {
                case "koromlakk":
                    iw.setImageResource(R.drawable.koromlakk);
                    break;
                case "basegel":
                    iw.setImageResource(R.drawable.basegel);
                    break;
                default:
                    iw.setImageResource(R.drawable.koromlakk);
            }

            LinearLayout ll = (LinearLayout) gl.getChildAt(1);
            //2.Név
            TextView nevText = (TextView) ll.getChildAt(0);
            nevText.setText(i.getName());

            //3.Szín
            TextView szinText = (TextView) ll.getChildAt(1);
            szinText.setText(i.getColor());

            //4.Ár
            TextView arText = (TextView) ll.getChildAt(2);
            arText.setText(Integer.toString(i.getPrice()) + " Ft");

            //Onclick esemény beállítása
            ImageView kosarView = (ImageView) gl.getChildAt(2);
            kosarView.setTag(i.getId());
            kosarView.setOnClickListener(this::addToCart);

            itemsLinearLayout.addView(dynamicView);
        }
    }

    private void addToCart(View view) {
        String id = (String) view.getTag();
        SharedPreferences sp = getSharedPreferences("cart", Context.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sp.edit();
        int ertek = sp.getInt(id, 0);
        ertek++;
        spEditor.putInt(id, ertek);
        spEditor.apply();
    }



}