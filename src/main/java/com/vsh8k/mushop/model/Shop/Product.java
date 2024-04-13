package com.vsh8k.mushop.model.Shop;

import com.vsh8k.mushop.model.Database.CommentManager;
import com.vsh8k.mushop.model.Database.DBConnector;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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
    protected String imageURL;

    public String toString() {
        return title;
    }

    public Product(int id, String title, String description, int qty, float weight, float price, int discount, String ean, String imageURL) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.qty = qty;
        this.weight = weight;
        this.comments = new ArrayList<>();
        this.dateCreated = LocalDate.now();
        this.price = price;
        this.discount = discount;
        this.ean = ean;
        this.imageURL = imageURL;
    }

    public List<Comment> getCommentsFromDB(DBConnector db) {
        CommentManager cm = new CommentManager();
        List<Comment> commList = cm.getCommentsFromDB(db, this.id);
        for (Comment comment : commList) {
            System.out.println(comment.getBody());
        }
        return commList;
    }
}
