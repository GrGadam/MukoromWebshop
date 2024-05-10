package io.github.grgadam.mukoromwebshop.Model;

public class Item {
    private String id;
    private String name;
    private String type;
    private String color;

    private int price;
    private int count;

    public Item(String id, String name, String type, String color, int price, int count) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.color = color;
        this.price = price;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}