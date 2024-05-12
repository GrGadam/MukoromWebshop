package io.github.grgadam.mukoromwebshop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

import io.github.grgadam.mukoromwebshop.Model.Item;

public class CartActivity extends Activity {
    private CollectionReference itemCollection;
    private ArrayList<Item> items;
    private Item item;
    private ImageView backImageViewCart;
    private ListView cartListView;
    private AppCompatButton deleteCartButton;
    private AppCompatButton sumbitOrderButton;

    //ListView things
    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemCollection = FirebaseFirestore.getInstance().collection("Items");
        items = new ArrayList<>();
        item = null;
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);

        //-----STATUS AND NAVBAR-----
        //hide navbar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        //ertesites sav hatter beallit
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setStatusBarColor(getColor(R.color.pastel2));

        setContentView(R.layout.activity_cart);

        //ImageViews
        backImageViewCart = findViewById(R.id.backImageViewCart);

        //Buttons
        deleteCartButton = findViewById(R.id.deleteCartButton);
        sumbitOrderButton = findViewById(R.id.sumbitOrderButton);

        //ListViews
        cartListView = findViewById(R.id.cartListView);
        cartListView.setAdapter(adapter);

        SharedPreferences sp = getSharedPreferences("cart", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = sp.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            itemCollection.document(entry.getKey()).get().addOnSuccessListener(queryDocumentSnapshot -> {
                Item i = queryDocumentSnapshot.toObject(Item.class);
                assert i != null;
                i.setId(queryDocumentSnapshot.getId());
                addToListView(i);
            });
        }

        //Listeners
        backImageViewCart.setOnClickListener(this::back);
        deleteCartButton.setOnClickListener(this::deleteCart);
        sumbitOrderButton.setOnClickListener(this::showAddressActivity);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void back(View view) {
        Intent showShoppingIntent = new Intent(this, ShoppingActivity.class);
        startActivity(showShoppingIntent);
    }

    private void addToListView(Item item) {
        SharedPreferences sp = getSharedPreferences("cart", Context.MODE_PRIVATE);
        int darab = sp.getInt(item.getId(), 0);
        listItems.add(item.getName() + ", " + item.getColor() + ", " + darab + "db");
        adapter.notifyDataSetChanged();
    }

    private void deleteCart(View view) {
        SharedPreferences sp = getSharedPreferences("cart", Context.MODE_PRIVATE);
        sp.edit().clear().apply();
        Intent showShoppingIntent = new Intent(this, CartActivity.class);
        startActivity(showShoppingIntent);
    }

    private void showAddressActivity(View view) {

    }

}