package com.vsh8k.mushop.model.Shop;

import com.vsh8k.mushop.model.Database.DBConnector;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.lang.model.element.NestingKind;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Year;
import java.util.ArrayList;

@Getter
@Setter
public abstract class Media extends Product {
    private String artist;
    private String album;
    private int releaseYear;
    private String label;
    private Time totalLen;
    private short trackQty;
    private String mediaGrade;
    private String sleeveGrade;
    private String genre;
    private String mediaType;

    @Override
    public String toString(){
        return this.artist + " - " + this.album;
    }

    public Media(int id, String title, String description, int qty, float weight, float price, int discount, String artist, String album, int releaseYear, String label, Time totalLen, short trackQty, String mediaGrade, String sleeveGrade, String genre, String ean, String mediaType, String imageURL) {
        super(id, title, description, qty, weight, price, discount, ean, imageURL);
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
