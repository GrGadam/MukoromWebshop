package io.github.grgadam.mukoromwebshop.DAO;

import java.util.List;

import io.github.grgadam.mukoromwebshop.Model.Item;

public interface ItemDAO {

    void add(Item item);
    Item get(String id);
    boolean update(Item item);
    boolean delete(Item item);
    List<Item> list();

}
