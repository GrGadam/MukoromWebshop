package io.github.grgadam.mukoromwebshop;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class DeleteItemActivity extends AppCompatActivity {
    private CollectionReference itemCollection;

    private ImageView backImageViewToolsDelete;
    private Spinner itemSpinner;
    private AppCompatButton deleteitemButton;
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemCollection = FirebaseFirestore.getInstance().collection("Items");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);

        //-----STATUS AND NAVBAR-----
        //hide navbar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        //ertesites sav hatter beallit
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setStatusBarColor(getColor(R.color.pastel2));

        setContentView(R.layout.activity_deleteitem);

        //ImageViews
        backImageViewToolsDelete = findViewById(R.id.backImageViewToolsDelete);

        //Spinner
        itemSpinner = findViewById(R.id.itemSpinner);
        itemSpinner.setAdapter(adapter);

        //Buttons
        deleteitemButton = findViewById(R.id.deleteItemButton);

        //Listeners
        deleteitemButton.setOnClickListener(this::deleteItem);
        backImageViewToolsDelete.setOnClickListener(this::goBack);

        itemCollection.limit(50).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                addToSpinner(document.getId());
            }
        });
    }

    private void deleteItem(View view) {
        String id = itemSpinner.getSelectedItem().toString();
        itemCollection.document(id).delete().addOnSuccessListener(queryDocumentSnapshots -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Siker");
            builder.setMessage("Termék sikeresen törölve!");
            builder.setPositiveButton("Ok", (dialog, which) -> dialog.dismiss());
            builder.show();
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

    private void goBack(View view) {
        Intent showShoppingIntent = new Intent(this, ShoppingActivity.class);
        startActivity(showShoppingIntent);
    }

    private void addToSpinner(String id) {
        listItems.add(id);
        adapter.notifyDataSetChanged();
    }

}