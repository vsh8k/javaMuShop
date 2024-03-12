package com.vsh8k.mushop.model;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public abstract class Media extends Product {
    private String artist;
    private String album;
    private String releaseYear;
    private String label;
    private String totalLen;
    private short trackQty;
    protected String mediaGrade;
    protected String sleeveGrade;
    protected short packQty;
    protected String type;

    public String toString(){
        return this.artist + " - " + this.album;
    }

    public Media(String title, String description, int qty, float weight, float price, String artist, String album, String releaseYear, String label, String totalLen, short trackQty, String mediaGrade, String sleeveGrade, short packQty, String type) {
        super(title, description, qty, weight, price);
        this.artist = artist;
        this.album = album;
        this.releaseYear = releaseYear;
        this.label = label;
        this.totalLen = totalLen;
        this.trackQty = trackQty;
        this.mediaGrade = mediaGrade;
        this.sleeveGrade = sleeveGrade;
        this.packQty = packQty;
        this.type = type;
    }
}
