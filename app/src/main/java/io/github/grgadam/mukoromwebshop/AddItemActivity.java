package io.github.grgadam.mukoromwebshop;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import io.github.grgadam.mukoromwebshop.Model.Item;

public class AddItemActivity extends AppCompatActivity {
    private CollectionReference itemCollection;

    private ImageView backImageViewToolsAdd;

    private EditText nameEditTextADD;
    private EditText priceEditTextADD;
    private EditText typeEditTextADD;
    private EditText colorEditTextADD;
    private AppCompatButton addItemButtonAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemCollection = FirebaseFirestore.getInstance().collection("Items");

        //-----STATUS AND NAVBAR-----
        //hide navbar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        //ertesites sav hatter beallit
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setStatusBarColor(getColor(R.color.pastel2));

        setContentView(R.layout.activity_additem);

        //ImageViews
        backImageViewToolsAdd = findViewById(R.id.backImageViewToolsAdd);

        //EditTexts
        nameEditTextADD = findViewById(R.id.nevEditText);
        priceEditTextADD = findViewById(R.id.arEditText);
        typeEditTextADD = findViewById(R.id.tipusEditText);
        colorEditTextADD = findViewById(R.id.szinEditText);

        //Buttons
        addItemButtonAdd = findViewById(R.id.addItemButtonAdd);

        //Listeners
        addItemButtonAdd.setOnClickListener(this::uploadItem);
        backImageViewToolsAdd.setOnClickListener(this::goBack);
    }

    private void uploadItem(View view) {
        if (nameEditTextADD.getText().toString().isEmpty() || priceEditTextADD.getText().toString().isEmpty() || typeEditTextADD.getText().toString().isEmpty() || colorEditTextADD.getText().toString().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Hiba");
            builder.setMessage("Üres mezők!");
            builder.setPositiveButton("Ok", (dialog, which) -> dialog.dismiss());
            builder.show();
        } else {
            Item i = new Item();
            i.setName(nameEditTextADD.getText().toString());
            i.setPrice(Integer.parseInt(priceEditTextADD.getText().toString()));
            i.setType(typeEditTextADD.getText().toString());
            i.setColor(colorEditTextADD.getText().toString());

            itemCollection.add(i).addOnSuccessListener(queryDocumentSnapshots -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Siker");
                builder.setMessage("Termék sikeresen hozzáadva!");
                builder.setPositiveButton("Ok", (dialog, which) -> dialog.dismiss());
                builder.show();
            });
        }
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

}