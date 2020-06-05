package com.example.order;

public class model {

    private int id;
    private String name;
    private int price;
    private String category;

    public model(){

    }

    public model(int id, String name, int price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCartegory() {
        return category;
    }

    public void setCartegory(String cartegory) {
        this.category = cartegory;
    }
}
