package io.github.grgadam.mukoromwebshop.Model;

import java.util.Map;

public class Order {

    private String id;
    private String email;
    private String address;
    private Map<String, Integer> items;

    public Order(String id, String email, String address, Map<String, Integer> items) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }
}
