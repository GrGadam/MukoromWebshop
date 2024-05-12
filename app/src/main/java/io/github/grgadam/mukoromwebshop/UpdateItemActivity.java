package io.github.grgadam.mukoromwebshop;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.github.grgadam.mukoromwebshop.Model.Item;

public class UpdateItemActivity extends AppCompatActivity {
    private CollectionReference itemCollection;

    private ImageView backImageViewToolsUpdate;
    private Spinner updateSpinner;
    private AppCompatButton modifyItemButton;
    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;

    private EditText nevEditTextUpdate;
    private EditText arEditTextUpdate;
    private EditText tipusEditTextUpdate;
    private EditText szinEditTextUpdate;


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

        setContentView(R.layout.activity_updateitem);

        //ImageViews
        backImageViewToolsUpdate = findViewById(R.id.backImageViewToolsUpdate);

        //Spinner
        updateSpinner = findViewById(R.id.updateSpinner);
        updateSpinner.setAdapter(adapter);

        //Buttons
        modifyItemButton = findViewById(R.id.modifyItemButton);

        //EditTexts
        nevEditTextUpdate = findViewById(R.id.nevEditTextUpdate);
        arEditTextUpdate = findViewById(R.id.arEditTextUpdate);
        tipusEditTextUpdate = findViewById(R.id.tipusEditTextUpdate);
        szinEditTextUpdate = findViewById(R.id.szinEditTextUpdate);

        //Listeners
        modifyItemButton.setOnClickListener(this::modifyItem);
        backImageViewToolsUpdate.setOnClickListener(this::goBack);
        updateSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        updateTable();
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        itemCollection.limit(50).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                addToSpinner(document.getId());
            }
        });
    }

    private void modifyItem(View view) {
        String id = updateSpinner.getSelectedItem().toString();
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", nevEditTextUpdate.getText().toString());
        updates.put("price", Integer.parseInt(arEditTextUpdate.getText().toString()));
        updates.put("color", szinEditTextUpdate.getText().toString());
        updates.put("type", tipusEditTextUpdate.getText().toString());

        itemCollection.document(id).update(updates).addOnSuccessListener(queryDocumentSnapshots -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Siker");
            builder.setMessage("Termék sikeresen módosítva!");
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
        Intent showShoppingIntent = new Intent(this, ToolsActivity.class);
        startActivity(showShoppingIntent);
    }

    private void addToSpinner(String id) {
        listItems.add(id);
        adapter.notifyDataSetChanged();
    }

    private void updateTable() {
        String id = updateSpinner.getSelectedItem().toString();
        itemCollection.document(id).get().addOnSuccessListener(queryDocumentSnapshot -> {
            Item i = queryDocumentSnapshot.toObject(Item.class);
            assert i != null;
            nevEditTextUpdate.setText(i.getName());
            arEditTextUpdate.setText(String.valueOf(i.getPrice()));
            tipusEditTextUpdate.setText(i.getType());
            szinEditTextUpdate.setText(i.getColor());
        });
    }

}