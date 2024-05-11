package io.github.grgadam.mukoromwebshop.Controller;

import java.util.List;

import io.github.grgadam.mukoromwebshop.DAO.ItemDAOImpl;
import io.github.grgadam.mukoromwebshop.Model.Item;

public class ItemController {

    private ItemDAOImpl dao = null;
    private static ItemController instance = null;

    public ItemController() {
        dao = ItemDAOImpl.getInstance();
    }

    public static ItemController getInstance() {
        if (instance == null) {
            instance = new ItemController();
        }
        return instance;
    }

    public List<Item> listAll() {
        return dao.list();
    }

}
