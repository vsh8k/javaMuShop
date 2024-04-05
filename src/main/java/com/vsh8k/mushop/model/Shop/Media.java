package com.vsh8k.mushop.model.Shop;

import com.vsh8k.mushop.model.Database.DBConnector;
import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Year;
import java.util.ArrayList;

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
@Override
    public String toString(){
        return id + " : " + this.artist + " - " + this.album;
    }

    public Media(int id, String title, String description, int qty, float weight, float price, int discount, String artist, String album, Year releaseYear, String label, Time totalLen, short trackQty, String mediaGrade, String sleeveGrade, String genre, String ean, String mediaType) {
        super(id, title, description, qty, weight, price, discount, ean);
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

    public static ArrayList<Product> getAllProductsFromDB(DBConnector db) throws SQLException {
        ArrayList<Product> products = new ArrayList<>();

        try {
            db.connect(); // Connect to the database

            // Execute query to retrieve all media products
            ResultSet resultSet = db.query("SELECT * FROM media");

            // Process the result set
            while (resultSet.next()) {
                // Retrieve data from the result set and create Media objects
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                int qty = resultSet.getInt("qty");
                float weight = resultSet.getFloat("weight");
                float price = resultSet.getFloat("price");
                int discount = resultSet.getInt("discount");
                String artist = resultSet.getString("artist");
                String album = resultSet.getString("album");
                Year releaseYear = Year.of(resultSet.getInt("release_year"));
                String label = resultSet.getString("label");
                Time totalLen = resultSet.getTime("total_length");
                short trackQty = resultSet.getShort("track_quantity");
                String mediaGrade = resultSet.getString("media_grade");
                String sleeveGrade = resultSet.getString("sleeve_grade");
                String genre = resultSet.getString("genre");
                String ean = resultSet.getString("ean");
                String mediaType = resultSet.getString("media_type");
                int id = resultSet.getInt("id");

                // Create a new Media object based on the retrieved data
                Media media = new Media(id, title, description, qty, weight, price, discount, artist, album, releaseYear, label, totalLen, trackQty, mediaGrade, sleeveGrade, genre, ean, mediaType);

                // Add the media to the list
                products.add(media);
            }
        } finally {
            db.disconnect(); // Disconnect from the database
        }

        return products;
    }
}
