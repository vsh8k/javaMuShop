//Need to fix some code duplication
package com.vsh8k.mushop.model.Database;

import com.vsh8k.mushop.model.Shop.*;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class ProductManager {
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
                int releaseYear = resultSet.getInt("release_year");
                String label = resultSet.getString("label");
                Time totalLen = resultSet.getTime("total_length");
                short trackQty = resultSet.getShort("track_quantity");
                String mediaGrade = resultSet.getString("media_grade");
                String sleeveGrade = resultSet.getString("sleeve_grade");
                String genre = resultSet.getString("genre");
                String ean = resultSet.getString("ean");
                String mediaType = resultSet.getString("media_type");
                int id = resultSet.getInt("id");
                String url = resultSet.getString("image_url");
                Vinyl.VinylSpeed vinylSpeed = null;

                Media media = null;

                // Create a new Media object based on the retrieved data
                switch (mediaType) {
                    case "Cass":
                        media = new Cass(id, title, description, qty, weight, price, discount, artist, album, releaseYear, label, totalLen, trackQty, mediaGrade, sleeveGrade, genre, ean, mediaType, url);
                        break;
                    case "Vinyl":
                        switch (resultSet.getInt("vinyl_rpm")) {
                            case 33:
                                vinylSpeed = Vinyl.VinylSpeed.SPEED_33;
                                break;
                            case 45:
                                vinylSpeed = Vinyl.VinylSpeed.SPEED_45;
                                break;
                            case 78:
                                vinylSpeed = Vinyl.VinylSpeed.SPEED_78;
                                break;
                        }
                        media = new Vinyl(id, title, description, qty, weight, price, discount, artist, album, releaseYear, label, totalLen, trackQty, mediaGrade, sleeveGrade, genre, ean, mediaType, url, vinylSpeed);
                        break;
                    case "CD":
                        media = new CD(id, title, description, qty, weight, price, discount, artist, album, releaseYear, label, totalLen, trackQty, mediaGrade, sleeveGrade, genre, ean, mediaType, url);
                        break;
                }
                // Add the media to the list
                products.add(media);
            }
        } finally {
            db.disconnect(); // Disconnect from the database
        }

        return products;
    }

    public static ArrayList<Product> searchProductsFromDB(DBConnector db, String searchString) throws SQLException {
        ArrayList<Product> products = new ArrayList<>();

        try {
            db.connect(); // Connect to the database

            // Execute query to retrieve all media products
            ResultSet resultSet = db.query("SELECT * FROM media WHERE UPPER(title) LIKE UPPER('%" + searchString + "%')");

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
                int releaseYear = resultSet.getInt("release_year");
                String label = resultSet.getString("label");
                Time totalLen = resultSet.getTime("total_length");
                short trackQty = resultSet.getShort("track_quantity");
                String mediaGrade = resultSet.getString("media_grade");
                String sleeveGrade = resultSet.getString("sleeve_grade");
                String genre = resultSet.getString("genre");
                String ean = resultSet.getString("ean");
                String mediaType = resultSet.getString("media_type");
                int id = resultSet.getInt("id");
                String url = resultSet.getString("image_url");
                Vinyl.VinylSpeed vinylSpeed = null;
                Media media = null;
                // Create a new Media object based on the retrieved data
                switch (mediaType) {
                    case "Cass":
                        media = new Cass(id, title, description, qty, weight, price, discount, artist, album, releaseYear, label, totalLen, trackQty, mediaGrade, sleeveGrade, genre, ean, mediaType, url);
                        break;
                    case "Vinyl":
                        switch (resultSet.getInt("vinyl_rpm")) {
                            case 33:
                                vinylSpeed = Vinyl.VinylSpeed.SPEED_33;
                                break;
                            case 45:
                                vinylSpeed = Vinyl.VinylSpeed.SPEED_45;
                                break;
                            case 78:
                                vinylSpeed = Vinyl.VinylSpeed.SPEED_78;
                                break;
                        }
                        media = new Vinyl(id, title, description, qty, weight, price, discount, artist, album, releaseYear, label, totalLen, trackQty, mediaGrade, sleeveGrade, genre, ean, mediaType, url, vinylSpeed);
                        break;
                    case "CD":
                        media = new CD(id, title, description, qty, weight, price, discount, artist, album, releaseYear, label, totalLen, trackQty, mediaGrade, sleeveGrade, genre, ean, mediaType, url);
                        break;
                }
                // Add the media to the list
                products.add(media);
            }
        } finally {
            db.disconnect(); // Disconnect from the database
        }

        return products;
    }

    @SneakyThrows //Written with sneaky
    public static Product getProductById(int id, DBConnector db) {
        ResultSet resultSet = db.query("SELECT * FROM media WHERE id = " + id);
        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            int qty = resultSet.getInt("qty");
            float weight = resultSet.getFloat("weight");
            float price = resultSet.getFloat("price");
            int discount = resultSet.getInt("discount");
            String artist = resultSet.getString("artist");
            String album = resultSet.getString("album");
            int releaseYear = resultSet.getInt("release_year");
            String label = resultSet.getString("label");
            Time totalLen = resultSet.getTime("total_length");
            short trackQty = resultSet.getShort("track_quantity");
            String mediaGrade = resultSet.getString("media_grade");
            String sleeveGrade = resultSet.getString("sleeve_grade");
            String genre = resultSet.getString("genre");
            String ean = resultSet.getString("ean");
            String mediaType = resultSet.getString("media_type");
            String url = resultSet.getString("image_url");
            Vinyl.VinylSpeed vinylSpeed = null;
            Media media = null;
            // Create a new Media object based on the retrieved data
            switch (mediaType) {
                case "Cass":
                    media = new Cass(id, title, description, qty, weight, price, discount, artist, album, releaseYear, label, totalLen, trackQty, mediaGrade, sleeveGrade, genre, ean, mediaType, url);
                    break;
                case "Vinyl":
                    switch (resultSet.getInt("vinyl_rpm")) {
                        case 33:
                            vinylSpeed = Vinyl.VinylSpeed.SPEED_33;
                            break;
                        case 45:
                            vinylSpeed = Vinyl.VinylSpeed.SPEED_45;
                            break;
                        case 78:
                            vinylSpeed = Vinyl.VinylSpeed.SPEED_78;
                            break;
                    }
                    media = new Vinyl(id, title, description, qty, weight, price, discount, artist, album, releaseYear, label, totalLen, trackQty, mediaGrade, sleeveGrade, genre, ean, mediaType, url, vinylSpeed);
                    break;
                case "CD":
                    media = new CD(id, title, description, qty, weight, price, discount, artist, album, releaseYear, label, totalLen, trackQty, mediaGrade, sleeveGrade, genre, ean, mediaType, url);
                    break;
            }
            db.disconnect(); // Disconnect from the database
            return media;
        }
        db.disconnect(); // Disconnect from the database
        return null;
    }
}
