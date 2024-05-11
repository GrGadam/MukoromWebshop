package io.github.grgadam.mukoromwebshop.DAO;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.api.core.ApiFuture;

import java.util.ArrayList;
import java.util.List;

import io.github.grgadam.mukoromwebshop.Model.Item;

public class ItemDAOImpl implements ItemDAO {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static ItemDAOImpl object = new ItemDAOImpl();
    public static ItemDAOImpl getInstance() {
        return object;
    }

    @Override
    public void add(Item item) {
        db.collection("Items").add(item);
    }

    @Override
    public Item get(String id) {
        return null;
    }

    @Override
    public boolean update(Item item) {
        return false;
    }

    @Override
    public boolean delete(Item item) {
        return false;
    }

    @Override
    public List<Item> list() {
        List<Item> list = new ArrayList<>();
        db.collection("Items").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (DocumentSnapshot document : task.getResult()) {
                    Item taskItem = document.toObject(Item.class);
                    assert taskItem != null;
                    taskItem.setId(document.getId());
                    list.add(taskItem);
                }
            }
        });
        return list;
    }

}