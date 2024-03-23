package com.vsh8k.mushop.fxControllers;


import com.vsh8k.mushop.model.Shop.CD;
import com.vsh8k.mushop.model.Shop.Cass;
import com.vsh8k.mushop.model.Shop.Product;
import com.vsh8k.mushop.model.Shop.Vinyl;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainWindow {
    @FXML
    protected ListView<Product> productList;
    @FXML
    protected TextField artistField;
    @FXML
    protected TextField qtyField;
    @FXML
    protected TextField gradeMField;
    @FXML
    protected TextField gradeSField;
    @FXML
    protected TextField yearField;
    @FXML
    protected TextField albumField;
    @FXML
    protected TextField labelField;
    @FXML
    protected TextField tracksField;
    @FXML
    protected TextField lengthField;
    @FXML
    protected TextField priceField;
    @FXML
    protected MenuButton typeSelector;
    @FXML
    protected MenuItem typeCD;
    @FXML
    protected MenuItem typeVinyl;
    @FXML
    protected MenuItem typeCass;
    @FXML
    protected Button addButton;
    protected String type;
    @FXML
    protected void selectTypeCD() {
        type = "CD";
        typeSelector.setText("CD");
        System.out.println(type);
    }
    @FXML
    protected void selectTypeVinyl() {
        type = "Vinyl";
        typeSelector.setText("Vinyl");
        System.out.println(type);
    }
    @FXML
    protected void selectTypeCass() {
        type = "Cass";
        typeSelector.setText("Cassette");
        System.out.println(type);
    }
    @FXML
    protected void addButtonOnClick()
    {
        switch (type) {
            case "CD":
                CD cd = new CD("","", Integer.parseInt(qtyField.getText()), (float) 0, Float.parseFloat(priceField.getText()), artistField.getText(), albumField.getText(), yearField.getText(), labelField.getText(), lengthField.getText(), Short.parseShort(tracksField.getText()), gradeMField.getText(), gradeSField.getText(), (short) 1, "nėra atm");
                productList.getItems().add(cd);
                break;

            case "Vinyl":
                Vinyl vinyl = new Vinyl("","", Integer.parseInt(qtyField.getText()), (float) 0, Float.parseFloat(priceField.getText()), artistField.getText(), albumField.getText(), yearField.getText(), labelField.getText(), lengthField.getText(), Short.parseShort(tracksField.getText()), gradeMField.getText(), gradeSField.getText(), (short) 1, "nėra atm");
                productList.getItems().add(vinyl);
                break;

            case "Cass":
                Cass cass = new Cass("","", Integer.parseInt(qtyField.getText()), (float) 0, Float.parseFloat(priceField.getText()), artistField.getText(), albumField.getText(), yearField.getText(), labelField.getText(), lengthField.getText(), Short.parseShort(tracksField.getText()), gradeMField.getText(), gradeSField.getText(), (short) 1, "nėra atm");
                productList.getItems().add(cass);
                break;

        }
    }
    @FXML
    public void deleteRecord() {
        Product product = productList.getSelectionModel().getSelectedItem();
        productList.getItems().remove(product);
    }
    @FXML
    void updateRecord() {
        Product product = productList.getSelectionModel().getSelectedItem();

        if (product instanceof CD) {
            CD cd = (CD) product;
            cd.setArtist(artistField.getText());
            cd.setReleaseYear(yearField.getText());
            cd.setAlbum(albumField.getText());
            cd.setLabel(labelField.getText());
            cd.setTrackQty(Short.parseShort(tracksField.getText()));
            cd.setTotalLen(lengthField.getText());
            cd.setMediaGrade(gradeMField.getText());
            cd.setSleeveGrade(gradeSField.getText());
            cd.setQty(Integer.parseInt(qtyField.getText()));
            cd.setPrice(Float.parseFloat(priceField.getText()));
        }
        else if (product instanceof Vinyl) {
            Vinyl vinyl = (Vinyl) product;
            vinyl.setArtist(artistField.getText());
            vinyl.setReleaseYear(yearField.getText());
            vinyl.setAlbum(albumField.getText());
            vinyl.setLabel(labelField.getText());
            vinyl.setTrackQty(Short.parseShort(tracksField.getText()));
            vinyl.setTotalLen(lengthField.getText());
            vinyl.setMediaGrade(gradeMField.getText());
            vinyl.setSleeveGrade(gradeSField.getText());
            vinyl.setQty(Integer.parseInt(qtyField.getText()));
            vinyl.setPrice(Float.parseFloat(priceField.getText()));
        }
        else if (product instanceof Cass) {
            Cass cass = (Cass) product;
            cass.setArtist(artistField.getText());
            cass.setReleaseYear(yearField.getText());
            cass.setAlbum(albumField.getText());
            cass.setLabel(labelField.getText());
            cass.setTrackQty(Short.parseShort(tracksField.getText()));
            cass.setTotalLen(lengthField.getText());
            cass.setMediaGrade(gradeMField.getText());
            cass.setSleeveGrade(gradeSField.getText());
            cass.setQty(Integer.parseInt(qtyField.getText()));
            cass.setPrice(Float.parseFloat(priceField.getText()));
        }
    }
    @FXML
    public void loadProductData() {
        Product product = productList.getSelectionModel().getSelectedItem();

        if (product instanceof CD) {
            CD cd = (CD) product;
            artistField.setText(cd.getArtist());
            yearField.setText(cd.getReleaseYear());
            albumField.setText(cd.getAlbum());
            labelField.setText(cd.getLabel());
            tracksField.setText(Short.toString(cd.getTrackQty()));
            lengthField.setText(cd.getTotalLen());
            gradeMField.setText(cd.getMediaGrade());
            gradeSField.setText(cd.getSleeveGrade());
            typeSelector.setText("CD");
            qtyField.setText(Integer.toString(cd.getQty()));
            priceField.setText(Float.toString(cd.getPrice()));
        }
        else if (product instanceof Vinyl) {
            Vinyl vinyl = (Vinyl) product;
            artistField.setText(vinyl.getArtist());
            yearField.setText(vinyl.getReleaseYear());
            albumField.setText(vinyl.getAlbum());
            labelField.setText(vinyl.getLabel());
            tracksField.setText(Short.toString(vinyl.getTrackQty()));
            lengthField.setText(vinyl.getTotalLen());
            gradeMField.setText(vinyl.getMediaGrade());
            gradeSField.setText(vinyl.getSleeveGrade());
            typeSelector.setText("Vinyl");
            qtyField.setText(Integer.toString(vinyl.getQty()));
            priceField.setText(Float.toString(vinyl.getPrice()));
        }
        else if (product instanceof Cass) {
            Cass cass = (Cass) product;
            artistField.setText(cass.getArtist());
            yearField.setText(cass.getReleaseYear());
            albumField.setText(cass.getAlbum());
            labelField.setText(cass.getLabel());
            tracksField.setText(Short.toString(cass.getTrackQty()));
            lengthField.setText(cass.getTotalLen());
            gradeMField.setText(cass.getMediaGrade());
            gradeSField.setText(cass.getSleeveGrade());
            typeSelector.setText("Cassette");
            qtyField.setText(Integer.toString(cass.getQty()));
            priceField.setText(Float.toString(cass.getPrice()));
        }
    }
}

