package com.vsh8k.mushop.model;

public class Vinyl extends Media{
    protected short rpm;
    protected short size;

    public Vinyl(String title, String description, int qty, float weight, float price, String artist, String album, String releaseYear, String label, String totalLen, short trackQty, String mediaGrade, String sleeveGrade, short packQty, String type, short rpm, short size) {
        super(title, description, qty, weight, price, artist, album, releaseYear, label, totalLen, trackQty, mediaGrade, sleeveGrade, packQty, type);
        this.rpm = rpm;
        this.size = size;
    }
}
