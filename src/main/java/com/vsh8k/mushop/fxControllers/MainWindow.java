package com.vsh8k.mushop.fxControllers;


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
        String title = "tofix";
        String description = "";
        System.out.println(qtyField.getText());
        int qty = Integer.parseInt(qtyField.getText());
        float weight = Float.parseFloat(weightField.getText());
        float price = Float.parseFloat(priceField.getText());
        String ean = eanField.getText();
        int discount = Integer.parseInt(discountField.getText());
        String artist = artistField.getText();
        String album = albumField.getText();
        Year releaseYear = Year.parse(yearField.getText());
        String label = labelField.getText();
        Time totalLen = Time.valueOf(lengthField.getText());
        short trackQty = Short.parseShort(tracksField.getText());
        String mediaGrade = mediaGradeSelector.getText();
        String sleeveGrade = sleeveGradeSelector.getText();
        String genre = genreField.getText();
        String mediaType = type;

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
//        Product product = productList.getSelectionModel().getSelectedItem();
//        Media album = (Media) product;
//            album.setArtist(toString(artistField.getText());
//            album.setReleaseYear(yearField.getText());
//            album.setAlbum(albumField.getText());
//            album.setLabel(labelField.getText());
//            album.setTrackQty(Short.parseShort(tracksField.getText()));
//            album.setTotalLen(lengthField.getText());
//            album.setMediaGrade(gradeMField.getText());
//            album.setSleeveGrade(gradeSField.getText());
//            album.setQty(Integer.parseInt(qtyField.getText()));
//            album.setPrice(Float.parseFloat(priceField.getText()));
    }
    @FXML
    public void loadProductData() {
//        Product product = productList.getSelectionModel().getSelectedItem();
//
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

