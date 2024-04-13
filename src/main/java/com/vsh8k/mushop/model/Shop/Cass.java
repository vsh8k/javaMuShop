package com.vsh8k.mushop.model.Shop;

import java.sql.Time;

public class Cass extends Media{
    public Cass(int id, String title, String description, int qty, float weight, float price, int discount, String artist, String album, int releaseYear, String label, Time totalLen, short trackQty, String mediaGrade, String sleeveGrade, String genre, String ean, String mediaType, String imageURL) {
        super(id, title, description, qty, weight, price, discount, artist, album, releaseYear, label, totalLen, trackQty, mediaGrade, sleeveGrade, genre, ean, mediaType, imageURL);
    }
}
