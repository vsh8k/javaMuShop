package com.vsh8k.mushop.model.Shop;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.Year;

@Getter
@Setter
public class Media extends Product {
    private String artist;
    private String album;
    private Year releaseYear;
    private String label;
    private Time totalLen;
    private short trackQty;
    private String mediaGrade;
    private String sleeveGrade;
    private String genre;
    private String mediaType;

    public String toString(){
        return this.artist + " - " + this.album;
    }

    public Media(String title, String description, int qty, float weight, float price, int discount, String artist, String album, Year releaseYear, String label, Time totalLen, short trackQty, String mediaGrade, String sleeveGrade, String genre, String ean, String mediaType) {
        super(title, description, qty, weight, price, discount, ean);
        this.artist = artist;
        this.album = album;
        this.releaseYear = releaseYear;
        this.label = label;
        this.totalLen = totalLen;
        this.trackQty = trackQty;
        this.mediaGrade = mediaGrade;
        this.sleeveGrade = sleeveGrade;
        this.genre = genre;
        this.mediaType = mediaType;
    }
}
