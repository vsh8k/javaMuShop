package com.vsh8k.mushop.fxControllers;


import com.vsh8k.mushop.model.Misc.Validate;
import com.vsh8k.mushop.model.Shop.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Time;
import java.time.Year;

public class MainWindow {

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
    private Year releaseYear;
    private String label;
    private Time totalLen;
    private short trackQty;
    private String mediaGrade;
    private String sleeveGrade;
    private String genre;
    private String mediaType;

    private void readValidateDataFromUI() {
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
        mediaType = type;
        title = artist + "-" + album;
        description = "";
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
        readValidateDataFromUI();
        Media media = new Media(title, description, qty, weight, price, discount, artist, album, releaseYear, label, totalLen, trackQty, mediaGrade, sleeveGrade, genre, ean, mediaType);
        productList.getItems().add(media);
    }
    @FXML
    public void deleteRecord() {
        Product product = productList.getSelectionModel().getSelectedItem();
        productList.getItems().remove(product);
    }
    @FXML
    void updateRecord() {
        readValidateDataFromUI();
        Product product = productList.getSelectionModel().getSelectedItem();
        if (product instanceof Media) {
            Media media = (Media) product;
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
        }
    }
    @FXML
    public void loadProductData() {
        Product product = productList.getSelectionModel().getSelectedItem();

//            Media album = (Media) product;
//            artistField.setText(album.getArtist());
//            yearField.setText(album.getReleaseYear());
//            albumField.setText(album.getAlbum());
//            labelField.setText(album.getLabel());
//            tracksField.setText(Short.toString(album.getTrackQty()));
//            lengthField.setText(album.getTotalLen());
//            gradeMField.setText(album.getMediaGrade());
//            gradeSField.setText(album.getSleeveGrade());
//            typeSelectorCD.fire();
//            qtyField.setText(Integer.toString(album.getQty()));
//            priceField.setText(Float.toString(album.getPrice()));
    }
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

        System.out.println("init!");

    }
}

