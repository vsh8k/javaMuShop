package com.vsh8k.mushop.model.Shop;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter

public abstract class Product implements Serializable {
    protected int id;
    protected String title;
    protected String description;
    protected int qty;
    protected float weight;
    protected List<Comment> comments;
    protected LocalDate dateCreated;
    protected float price;
    protected String ean;
    protected int discount;

    public Product(String title, String description, int qty, float weight, float price, int discount, String ean) {
        this.title = title;
        this.description = description;
        this.qty = qty;
        this.weight = weight;
        this.comments = new ArrayList<>();
        this.dateCreated = LocalDate.now();
        this.price = price;
        this.discount = discount;
    }
}
