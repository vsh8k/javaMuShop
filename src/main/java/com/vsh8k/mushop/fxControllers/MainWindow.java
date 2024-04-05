package com.vsh8k.mushop.fxControllers;


import com.vsh8k.mushop.model.Database.DBConnector;
import com.vsh8k.mushop.model.Misc.Validate;
import com.vsh8k.mushop.model.Popup.Warning;
import com.vsh8k.mushop.model.Shop.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.SneakyThrows;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Year;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainWindow {
    //<editor-fold desc="DB Details">
    String dbUrl = "jdbc:mysql://localhost:3306/products";
    String dbUsername = "root";
    String dbPassword = "";
    private DBConnector db = new DBConnector(dbUrl, dbUsername, dbPassword);
    private String mediaCols[] = {"title", "description", "qty", "weight", "price", "discount", "artist", "album", "release_year", "label", "total_length", "track_quantity", "media_grade", "sleeve_grade", "genre", "ean", "media_type"};
    private Object mediaValues[] = {};
    //</editor-fold>

    //<editor-fold desc="Tab: Products">
    private ArrayList<Product> products = new ArrayList<>();
    @FXML
    private ListView<Product> productList;
    @FXML
    private TextField yearField;
    @FXML
    private TextField artistField;
    @FXML
    private TextField albumField;
    @FXML
    private TextField labelField;
    @FXML
    private TextField tracksField;
    @FXML
    private TextField lengthField;

    //<editor-fold desc="Media grade selector">
    @FXML
    private MenuButton mediaGradeSelector;
    @FXML
    private MenuItem mgs1;
    @FXML
    private MenuItem mgs2;
    @FXML
    private MenuItem mgs3;
    @FXML
    private MenuItem mgs4;
    @FXML
    private MenuItem mgs5;
    @FXML
    private MenuItem mgs6;
    //</editor-fold>
    //<editor-fold desc="Sleeve grade selector">
    @FXML
    private MenuButton sleeveGradeSelector;
    @FXML
    private MenuItem mss1;
    @FXML
    private MenuItem mss2;
    @FXML
    private MenuItem mss3;
    @FXML
    private MenuItem mss4;
    @FXML
    private MenuItem mss5;
    @FXML
    private MenuItem mss6;
    //</editor-fold>
    //<editor-fold desc="Media Type Select">
    @FXML
    private RadioButton typeSelectorCD;
    @FXML
    private RadioButton typeSelectorVinyl;
    @FXML
    private RadioButton typeSelectorCass;
    private ToggleGroup tg = new ToggleGroup();
    //</editor-fold>

    @FXML
    private TextField genreField;
    @FXML
    private TextField weightField;
    @FXML
    private TextField discountField;
    @FXML
    private TextField eanField;
    @FXML
    private TextField qtyField;
    @FXML
    private TextField priceField;

    //<editor-fold desc="CRUD Buttons">
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button removeButton;
    //</editor-fold>

    private String type;

    //<editor-fold desc="Products tab data and reading">
    private String title;
    private String description;
    private int qty;
    private float weight;
    private float price;
    private String ean;
    private int discount;
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

    private void readValidateDataFromUI() throws Exception{
        qty = Validate.validateAndConvertInteger(qtyField.getText(), "Quantity");
        weight = Validate.validateAndConvertFloat(weightField.getText(), "Weight");
        price = Validate.validateAndConvertFloat(priceField.getText(), "Price");
        ean = Validate.validateAndConvertString(eanField.getText(), "EAN");
        discount = Validate.validateAndConvertInteger(discountField.getText(), "Discount");
        artist = Validate.validateAndConvertString(artistField.getText(), "Artist");
        album = Validate.validateAndConvertString(albumField.getText(), "Album");
        releaseYear = Validate.validateAndConvertYear(yearField.getText(), "Release Year");
        label = Validate.validateAndConvertString(labelField.getText(), "Label");
        totalLen = Validate.validateAndConvertTime(lengthField.getText(), "Total Length");
        trackQty = Validate.validateAndConvertShort(tracksField.getText(), "Track Quantity");
        mediaGrade = Validate.validateAndConvertString(mediaGradeSelector.getText(), "Media Grade");
        sleeveGrade = Validate.validateAndConvertString(sleeveGradeSelector.getText(), "Sleeve Grade");
        genre = Validate.validateAndConvertString(genreField.getText(), "Genre");
        mediaType = Validate.validateAndConvertString(type, "Media Type");
        title = artist + "-" + album;
        description = "";
        mediaValues = new Object[]{title, description, qty, weight, price, discount, artist, album, releaseYear, label, totalLen, trackQty, mediaGrade, sleeveGrade, genre, ean, mediaType};
    }

    //</editor-fold>

    //<editor-fold desc="Media type selection logic">
    @FXML
    private void selectTypeCD() {
        type = "CD";
        System.out.println(type);
    }
    @FXML
    private void selectTypeVinyl() {
        type = "Vinyl";
        System.out.println(type);
    }
    @FXML
    private void selectTypeCass() {
        type = "Cass";
        System.out.println(type);
    }
    //</editor-fold>

    //<editor-fold desc="EventHandlers for MenuButtons">
    @FXML
    EventHandler<ActionEvent> changeMediaGrade = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            mediaGradeSelector.setText(((MenuItem)e.getSource()).getText());
        }
    };
    @FXML
    EventHandler<ActionEvent> changeSleeveGrade = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            sleeveGradeSelector.setText(((MenuItem)e.getSource()).getText());
        }
    };
    //</editor-fold>
    @FXML
    private void addButtonOnClick()
    {
        try {

            readValidateDataFromUI();
            Media media = new Media(0, title, description, qty, weight, price, discount, artist, album, releaseYear, label, totalLen, trackQty, mediaGrade, sleeveGrade, genre, ean, mediaType);
            productList.getItems().add(media);
            db.connect();
            db.insert("media", mediaCols, mediaValues);
            updateProductList();
            db.disconnect();
        } catch (Exception e) {
            Warning.display("Validation error!", e.getMessage());
        }
    }
    @FXML
    public void deleteRecord() {
        //FEP: Add Confirmation Dialog
        try {
            db.connect();
            Object selectedItem = productList.getSelectionModel().getSelectedItem();
            if(selectedItem instanceof Product selectedProduct)
            {
                db.delete("media", "id", selectedProduct.getId());
                db.disconnect();
            }
            productList.getItems().remove(selectedItem);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void updateRecord() {
        try {
            readValidateDataFromUI();
            db.connect();
            Object selectedItem = productList.getSelectionModel().getSelectedItem();
        if (selectedItem instanceof Media media) {
                for(int i = 0; i < mediaCols.length; i++){
                    db.update("media", mediaCols[i], mediaValues[i], "id", media.getId());
                }
                db.disconnect();
            media.setTitle(title);
            media.setDescription(description);
            media.setQty(qty);
            media.setWeight(weight);
            media.setPrice(price);
            media.setDiscount(discount);
            media.setArtist(artist);
            media.setAlbum(album);
            media.setReleaseYear(releaseYear);
            media.setLabel(label);
            media.setTotalLen(totalLen);
            media.setTrackQty(trackQty);
            media.setMediaGrade(mediaGrade);
            media.setSleeveGrade(sleeveGrade);
            media.setGenre(genre);
            media.setEan(ean);
            media.setMediaType(mediaType);
            productList.refresh();
        }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
@FXML
public void loadProductData() {

    Product product = productList.getSelectionModel().getSelectedItem();
    if (product instanceof Media) {
        Media media = (Media) product;
        yearField.setText(String.valueOf(media.getReleaseYear()));
        artistField.setText(media.getArtist());
        albumField.setText(media.getAlbum());
        labelField.setText(media.getLabel());
        tracksField.setText(Integer.toString(media.getTrackQty()));
        lengthField.setText(String.valueOf(media.getTotalLen()));
        mediaGradeSelector.setText(media.getMediaGrade());
        sleeveGradeSelector.setText(media.getSleeveGrade());
        switch (media.getMediaType()) {
            case "CD":
                selectTypeCD();
                typeSelectorCD.fire();
                break;
            case "Cass":
                selectTypeCass();
                typeSelectorCass.fire();
                break;
            case "Vinyl":
                selectTypeVinyl();
                typeSelectorVinyl.fire();
                break;
        }
        genreField.setText(media.getGenre());
        weightField.setText(Float.toString(media.getWeight()));
        discountField.setText(Integer.toString(media.getDiscount()));
        eanField.setText(media.getEan());
        qtyField.setText(Integer.toString(media.getQty()));
        priceField.setText(Float.toString(media.getPrice()));

        try {
            // Read and validate data after setting all fields
            readValidateDataFromUI();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
@SneakyThrows
@FXML
private void updateProductList() {
        productList.getItems().clear();
        products = Media.getAllProductsFromDB(db);
        for (Product product : products) {
            productList.getItems().add(product);
        }
        System.out.println("Product list updated");
}
    //</editor-fold>
    //<editor-fold desc="Tab: Users">
    //</editor-fold>
    //<editor-fold desc="Tab: Shop">
    //</editor-fold>
    //<editor-fold desc="Tab: Cart">
    //</editor-fold>
    @FXML
    private void initialize() {
        typeSelectorVinyl.setToggleGroup(tg);
        typeSelectorCass.setToggleGroup(tg);
        typeSelectorCD.setToggleGroup(tg);

        mgs1.setOnAction(changeMediaGrade);
        mgs2.setOnAction(changeMediaGrade);
        mgs3.setOnAction(changeMediaGrade);
        mgs4.setOnAction(changeMediaGrade);
        mgs5.setOnAction(changeMediaGrade);
        mgs6.setOnAction(changeMediaGrade);

        mss1.setOnAction(changeSleeveGrade);
        mss2.setOnAction(changeSleeveGrade);
        mss3.setOnAction(changeSleeveGrade);
        mss4.setOnAction(changeSleeveGrade);
        mss5.setOnAction(changeSleeveGrade);
        mss6.setOnAction(changeSleeveGrade);
        //
        System.out.println("init!");
    }

}

