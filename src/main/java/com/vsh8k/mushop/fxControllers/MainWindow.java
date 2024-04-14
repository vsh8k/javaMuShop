package com.vsh8k.mushop.fxControllers;

import com.vsh8k.mushop.mainApplication;
import com.vsh8k.mushop.model.AccountSystem.Customer;
import com.vsh8k.mushop.model.AccountSystem.Hash;
import com.vsh8k.mushop.model.AccountSystem.Manager;
import com.vsh8k.mushop.model.AccountSystem.User;
import com.vsh8k.mushop.model.Database.CartManager;
import com.vsh8k.mushop.model.Database.DBConnector;
import com.vsh8k.mushop.model.Database.ProductManager;
import com.vsh8k.mushop.model.Database.UserManager;
import com.vsh8k.mushop.model.Misc.UserFilter;
import com.vsh8k.mushop.model.Misc.Validate;
import com.vsh8k.mushop.model.Popup.Information;
import com.vsh8k.mushop.model.Popup.Repeat;
import com.vsh8k.mushop.model.Popup.Warning;
import com.vsh8k.mushop.model.Shop.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainWindow {
    Stage primaryStage = null;
    @FXML
    Tab storeTab;
    @FXML
    Tab cartTab;
    @FXML
    Tab productsTab;
    @FXML
    Tab usersTab;
    @FXML
    Tab orderTab;
    @FXML
    TabPane tabs;

    //<editor-fold desc="DB Details">
    private DBConnector db;
    private String mediaCols[] = {"title", "description", "qty", "weight", "price", "discount", "artist", "album", "release_year", "label", "total_length", "track_quantity", "media_grade", "sleeve_grade", "genre", "ean", "media_type", "image_url", "vinyl_rpm"};
    private Object mediaValues[] = {};
    //</editor-fold>

    //<editor-fold desc="Current User">
    User currentUser = null;
    //</editor-fold>

    //<editor-fold desc="Tab: Products">
    private ArrayList<Product> products = new ArrayList<>();
    @FXML
    private TextField productsSearchBar;
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
    @FXML
    private TextField urlField;
    @FXML
    private ComboBox speedSelector;

    //<editor-fold desc="CRUD Buttons">
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button commentsButton;
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
    private String imageURL;
    private Vinyl.VinylSpeed vinylSpeed;

    private void readValidateDataFromUI() throws Exception {
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
        imageURL = Validate.validateAndConvertString(urlField.getText(), "Image URL");
        if (speedSelector.isDisabled()) {
            vinylSpeed = null;
            mediaValues = new Object[]{title, description, qty, weight, price, discount, artist, album, releaseYear, label, totalLen, trackQty, mediaGrade, sleeveGrade, genre, ean, mediaType, imageURL, vinylSpeed};
        } else {
            vinylSpeed = (Vinyl.VinylSpeed) speedSelector.getSelectionModel().getSelectedItem();
            mediaValues = new Object[]{title, description, qty, weight, price, discount, artist, album, releaseYear, label, totalLen, trackQty, mediaGrade, sleeveGrade, genre, ean, mediaType, imageURL, vinylSpeed.getValue()};
        }
    }


    //</editor-fold>

    //<editor-fold desc="Media type selection logic">
    @FXML
    private void selectTypeCD() {
        type = "CD";
        speedSelector.setDisable(true);
        System.out.println(type);
    }

    @FXML
    private void selectTypeVinyl() {
        type = "Vinyl";
        speedSelector.setDisable(false);
        System.out.println(type);
    }

    @FXML
    private void selectTypeCass() {
        type = "Cass";
        speedSelector.setDisable(true);
        System.out.println(type);
    }
    //</editor-fold>

    //<editor-fold desc="EventHandlers for MenuButtons">
    @FXML
    EventHandler<ActionEvent> changeMediaGrade = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
            mediaGradeSelector.setText(((MenuItem) e.getSource()).getText());
        }
    };
    @FXML
    EventHandler<ActionEvent> changeSleeveGrade = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
            sleeveGradeSelector.setText(((MenuItem) e.getSource()).getText());
        }
    };

    //</editor-fold>
    @FXML
    private void addButtonOnClick() {
        try {
            Media media = null;
            readValidateDataFromUI();
            if (mediaType.equals("Vinyl")) {
                media = new Vinyl(0, title, description, qty, weight, price, discount, artist, album, releaseYear, label, totalLen, trackQty, mediaGrade, sleeveGrade, genre, ean, mediaType, imageURL, vinylSpeed); //TRN
            }
            if (mediaType.equals("Cassette")) {
                media = new Cass(0, title, description, qty, weight, price, discount, artist, album, releaseYear, label, totalLen, trackQty, mediaGrade, sleeveGrade, genre, ean, mediaType, imageURL); //TRN
            }
            if (mediaType.equals("CD")) {
                media = new CD(0, title, description, qty, weight, price, discount, artist, album, releaseYear, label, totalLen, trackQty, mediaGrade, sleeveGrade, genre, ean, mediaType, imageURL); //TRN
            }
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
    private void deleteRecord() {
        //FEP: Add Confirmation Dialog
        try {
            db.connect();
            Object selectedItem = productList.getSelectionModel().getSelectedItem();
            if (selectedItem instanceof Product selectedProduct) {
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
                for (int i = 0; i < mediaCols.length; i++) {
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
                media.setImageURL(imageURL);
                if (media instanceof Vinyl vinyl) {
                    vinyl.setVinylSpeed(vinylSpeed);
                }
                productList.refresh();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void editComments() {

    }

    @FXML
    private void loadProductData() {

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
                case "Vinyl":
                    selectTypeVinyl();
                    typeSelectorVinyl.fire();
                    speedSelector.getSelectionModel().select(((Vinyl) media).getVinylSpeed());
                    break;
                case "Cass":
                    selectTypeCass();
                    typeSelectorCass.fire();
                    break;
            }


            urlField.setText(media.getImageURL());
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
        String searchString = productsSearchBar.getText();
        if (searchString.isEmpty()) {
            products = ProductManager.getAllProductsFromDB(db);
        } else {
            products = ProductManager.searchProductsFromDB(db, searchString);
        }
        for (Product product : products) {
            productList.getItems().add(product);
            product.getCommentsFromDB(db);
            //KOMENTARAI IS DB
        }
        System.out.println("Product list updated");
    }
    //</editor-fold>

    //<editor-fold desc="Tab: Users">

    @FXML
    private TableView<User> usersTable;
    @FXML
    private TableColumn<User, Integer> usersIdColumn;
    @FXML
    private TableColumn<User, String> usersEmailColumn;
    @FXML
    private TableColumn<User, String> usersLoginColumn;
    @FXML
    private TableColumn<User, String> usersPasswordColumn;
    @FXML
    private TableColumn<User, String> usersFirstnameColumn;
    @FXML
    private TableColumn<User, String> usersLastnameColumn;
    @FXML
    private TableColumn<User, Integer> usersAccountTypeColumn;
    @FXML
    private final ObservableList<User> usersObservableList = FXCollections.observableArrayList();
    @FXML
    private ContextMenu contextMenuUsers;
    @FXML
    private MenuItem usersContextItem1;
    @FXML
    private MenuItem usersContextItem2;
    @FXML
    private ComboBox filterSelect;
    @FXML
    private TextField filterText;

    @FXML
    private void updateUsersTable() {
        System.out.println("updateUsersTable");
        try {
            ObservableList<User> allUsers = UserManager.getAllUsersFromDB(db);
            usersTable.setItems(allUsers);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    private void filterOnClick() {
        System.out.println("filterUsersTable");
        String filterCriteria = filterSelect.getSelectionModel().getSelectedItem().toString();
        System.out.println(filterCriteria);
        String filterValue = filterText.getText();
        ObservableList<User> filteredUsers = FXCollections.observableArrayList();

        try {
            ObservableList<User> allUsers = UserManager.getAllUsersFromDB(db);

            for (User user : allUsers) {
                switch (filterCriteria) {
                    case "Id":
                        System.out.println("ID");
                        try {
                            int idFilter = Validate.validateAndConvertInteger(filterValue, "Filter text");
                            if (user.getId() == idFilter) {
                                filteredUsers.add(user);
                            }
                        } catch (Validate.ValidationException e) {
                            // Handle validation error
                            System.err.println("Validation error: " + e.getMessage());
                        }
                        break;
                    case "Name":
                        if (Objects.equals(user.getName(), filterValue)) {
                            filteredUsers.add(user);
                        }
                        break;
                    case "Surname":
                        if (Objects.equals(user.getSurname(), filterValue)) {
                            filteredUsers.add(user);
                        }
                        break;
                    case "AccountType":
                        try {
                            int accountTypeFilter = Validate.validateAndConvertInteger(filterValue, "Filter text");
                            if (user.getAccountType() == accountTypeFilter) {
                                filteredUsers.add(user);
                            }
                        } catch (Validate.ValidationException e) {
                            // Handle validation error
                            System.err.println("Validation error: " + e.getMessage());
                        }
                        break;
                    default:
                        break;
                }
            }

            usersTable.setItems(filteredUsers);
        } catch (Exception e) {
            // Handle other exceptions
            System.err.println("Error: " + e.getMessage());
        }
    }


    //</editor-fold>

    //<editor-fold desc="Tab: Shop">
    private ArrayList<Product> storeProducts = new ArrayList<>();
    @FXML
    private ListView<Product> storeProductList;
    @FXML
    private TextField storeProductsSearchBar;
    @FXML
    private Text storeEanText;
    @FXML
    private Text storeStockText;
    @FXML
    private Text storePriceText;
    @FXML
    private Text storeArtistText;
    @FXML
    private Text storeAlbumText;
    @FXML
    private Text storeYearText;
    @FXML
    private Text storeLabelText;
    @FXML
    private Text storeTracksText;
    @FXML
    private Text storeGenreText;
    @FXML
    private Text storeGradeTextS;
    @FXML
    private Text storeGradeTextM;
    @FXML
    private Text storeMediaText;
    @FXML
    private ImageView productImageView;
    @FXML
    private ComboBox storeQtySelector;
    @FXML
    private Button storeAddButton;

    @FXML
    private void loadStoreProductData() {

        Product product = storeProductList.getSelectionModel().getSelectedItem();
        if (product instanceof Media media) {
            storeYearText.setText("Artist: " + String.valueOf(media.getReleaseYear()));
            storeArtistText.setText("Album: " + media.getArtist());
            storeAlbumText.setText("Release year: " + media.getAlbum());
            storeLabelText.setText("Label: " + media.getLabel());
            storeTracksText.setText("Track amount: " + Integer.toString(media.getTrackQty()));
            storeGradeTextS.setText("Grade (Sleeve): " + media.getSleeveGrade());
            storeGradeTextM.setText("Grade (Media): " + media.getMediaGrade());
            //Media type
            if (media instanceof Vinyl vinyl) {
                switch (vinyl.getVinylSpeed()) {
                    case SPEED_33 -> storeMediaText.setText("33 1/3 rpm Vinyl");
                    case SPEED_45 -> storeMediaText.setText("45 rpm Vinyl");
                    case SPEED_78 -> storeMediaText.setText("78 rpm Vinyl");
                }

            } else if (media instanceof CD) {
                storeMediaText.setText("CD");
            } else if (media instanceof Cass) {
                storeMediaText.setText("Cassette");
            }

            //urlField.setText(media.getImageURL());
            try {
                productImageView.setImage(new Image(media.getImageURL()));
            } catch (Exception e) {
                productImageView.setImage(new Image("https://t4.ftcdn.net/jpg/00/62/08/95/360_F_62089548_hUsAVnxJKkwmMpqgalL4zhwXjwTqP4Vx.jpg")); //Fallback image
            }
            storeGenreText.setText("Genre: " + media.getGenre());
            storeEanText.setText("Ean: " + media.getEan());
            if (media.getQty() > 0) {
                storeStockText.setText("Stock: " + Integer.toString(media.getQty()));
                storeQtySelector.setDisable(false);
                storeAddButton.setDisable(false);
            } else {
                storeStockText.setText("Out of stock");
                storeStockText.setStyle("-fx-text-fill: red;");
                storeQtySelector.setDisable(true);
                storeAddButton.setDisable(true);
            }
            if (media.getDiscount() > 0) {
                storePriceText.setText("Price: " + Float.toString(media.getPrice()) + "€ -" + Float.toString(media.getDiscount()) + "%");
            } else {
                storePriceText.setText("Price: " + Float.toString(media.getPrice()) + "€");
            }
            //Qty. Selector Magic
            storeQtySelector.getItems().clear();
            for (int i = 0; i < media.getQty(); i++) {
                storeQtySelector.getItems().add(i + 1);
            }
            storeQtySelector.getSelectionModel().select(0);
        }
    }

    @SneakyThrows
    @FXML
    private void updateStoreProductList() {
        if (db != null) {
            storeProductList.getItems().clear();
            String searchString = storeProductsSearchBar.getText();
            if (searchString.isEmpty()) {
                storeProducts = ProductManager.getAllProductsFromDB(db);
            } else {
                storeProducts = ProductManager.searchProductsFromDB(db, searchString);
            }
            for (Product product : storeProducts) {
                storeProductList.getItems().add(product);
                product.getCommentsFromDB(db);
                //KOMENTARAI IS DB
            }
            System.out.println("Store Product list updated");
        }
        if (!storeProductList.getItems().isEmpty()) {
            storeProductList.getSelectionModel().select(0);
            loadStoreProductData();
        }
    }

    @FXML
    private void addToCartOnClick() {
        int qty = (int) storeQtySelector.getSelectionModel().getSelectedItem();
        try {
            if (currentUser instanceof Customer cust) {
                cust.addToCart(storeProductList.getSelectionModel().getSelectedItem(), qty);
                CartManager.updateCartInDB(cust, db);
            }
        } catch (Validate.ValidationException e) {
            Warning.display("Can't add to cart", e.getMessage());
        }
        //Susyncinam su DB
    }


    //</editor-fold>

    //<editor-fold desc="Tab: Cart">
    @FXML
    ListView<Pair<Product, Integer>> cartProductList;
    @FXML
    TextField cartDeliveryAddressField;
    @FXML
    TextField cartCityField;
    @FXML
    TextField cartPostcodeField;
    @FXML
    ComboBox<Shipping> shippingSelector;
    @FXML
    Text cartProductPrice;
    @FXML
    Text cartVATPrice;
    @FXML
    Text cartDeliveryPrice;
    @FXML
    Text cartTotalPrice;

    private float cartPrice;

    @FXML
    private void updateCartList() {
        System.out.println("Cart List Updated");
        //Shipping selector
        shippingSelector.getItems().clear();
        for (Shipping service : Shipping.getServices(db)) {
            shippingSelector.getItems().add(service);
        }
        shippingSelector.getSelectionModel().select(0);
        //
        cartProductList.getItems().clear();
        if (currentUser instanceof Customer cust) {
            Cart cart = cust.getCart();
            for (Pair<Product, Integer> pair : cart.getProductList()) {
                cartProductList.getItems().add(pair);
            }
        }
        cartProductList.setCellFactory(param -> new ListCell<Pair<Product, Integer>>() {
            private final Button button = new Button("Action");

            {
                button.setOnAction(event -> {
                    Pair<Product, Integer> item = getItem();
                    if (item != null) {
                        // Handle button click event here
                        System.out.println("Button clicked for: " + item.getKey());
                    }
                });
            }

            @Override
            protected void updateItem(Pair<Product, Integer> item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.getKey().toString()); // Set text to item representation
                    setGraphic(button);
                }
            }
        });

        cartUpdatePrices();
    }


    @FXML
    private void cartUpdatePrices() {
        float productPrice = 0;
        for (Pair<Product, Integer> pair : cartProductList.getItems()) {
            productPrice += (pair.getKey().getPrice() * pair.getValue());
        }
        float VAT = productPrice * 0.21f;
        cartProductPrice.setText("Products: " + new DecimalFormat("#.##").format(productPrice) + "€");
        cartVATPrice.setText("VAT (21%): " + new DecimalFormat("#.##").format(VAT) + "€");
        float deliveryPrice = shippingSelector.getSelectionModel().getSelectedItem().getPrice();
        cartDeliveryPrice.setText("Delivery Fees: " + new DecimalFormat("#.##").format(deliveryPrice) + "€");
        cartTotalPrice.setText("Order Total: " + new DecimalFormat("#.##").format(productPrice + deliveryPrice + VAT) + "€");

    }


    @FXML
    private void placeOrderOnClick() {
        if (currentUser instanceof Customer cust) {
            try {
                Validate.validateCart(cust.getCart(), db);
            } catch (Validate.ValidationException e) {
                Warning.display("Can't place order", e.getMessage());
            }
        }
    }
    //</editor-fold>

    //<editor-fold desc="initialize()">

    @FXML
    private void initialize() {

        typeSelectorVinyl.setToggleGroup(tg);
        typeSelectorCass.setToggleGroup(tg);
        typeSelectorCD.setToggleGroup(tg);

        speedSelector.getItems().addAll(Vinyl.VinylSpeed.values());

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

        //USERS

        usersTable.setEditable(true);

        usersIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        usersEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        usersPasswordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        usersFirstnameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        usersLastnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        usersAccountTypeColumn.setCellValueFactory(new PropertyValueFactory<>("accountType"));
        usersLoginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));

        //<editor-fold desc="Table Col: Email">
        usersEmailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        usersEmailColumn.setOnEditCommit(event -> {
            try {
                System.out.println(event.getOldValue());
                User user = event.getRowValue();
                user.setEmail(Validate.validateEmail(event.getNewValue(), "Email", db));
                System.out.println(event.getNewValue());
                UserManager.updateUser(db, user, "email");
            } catch (Exception e) {
                Warning.display("Error", e.getMessage());
                TableView<User> tableView = event.getTableView();
                TableColumn<User, String> col = event.getTableColumn();
                int row = event.getTablePosition().getRow();
                tableView.getItems().get(row).setEmail(event.getOldValue());
                tableView.refresh();
            }
        });
        //</editor-fold>

        //<editor-fold desc="Table Col: Login">
        usersLoginColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        usersLoginColumn.setOnEditCommit(event -> {
            try {
                System.out.println(event.getOldValue());
                User user = event.getRowValue();
                user.setLogin(Validate.validateLogin(event.getNewValue(), "Login", db));
                System.out.println(event.getNewValue());
                UserManager.updateUser(db, user, "login");
            } catch (Exception e) {
                Warning.display("Error", e.getMessage());
                TableView<User> tableView = event.getTableView();
                TableColumn<User, String> col = event.getTableColumn();
                int row = event.getTablePosition().getRow();
                tableView.getItems().get(row).setLogin(event.getOldValue());
                tableView.refresh();
            }
        });
        //</editor-fold>

        //<editor-fold desc="Table Col: First Name">
        usersFirstnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        usersFirstnameColumn.setOnEditCommit(event -> {
            try {
                System.out.println(event.getOldValue());
                User user = event.getRowValue();
                user.setName(Validate.validateAndConvertString(event.getNewValue(), "First Name"));
                System.out.println(event.getNewValue());
                UserManager.updateUser(db, user, "name");
            } catch (Exception e) {
                Warning.display("Error", e.getMessage());
                TableView<User> tableView = event.getTableView();
                TableColumn<User, String> col = event.getTableColumn();
                int row = event.getTablePosition().getRow();
                tableView.getItems().get(row).setEmail(event.getOldValue());
                tableView.refresh();
            }
        });
        //</editor-fold>

        //<editor-fold desc="Table Col: Last Name">
        usersLastnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        usersLastnameColumn.setOnEditCommit(event -> {
            try {
                System.out.println(event.getOldValue());
                User user = event.getRowValue();
                user.setSurname(Validate.validateAndConvertString(event.getNewValue(), "Last Name"));
                System.out.println(event.getNewValue());
                UserManager.updateUser(db, user, "surname");
            } catch (Exception e) {
                Warning.display("Error", e.getMessage());
                TableView<User> tableView = event.getTableView();
                TableColumn<User, String> col = event.getTableColumn();
                int row = event.getTablePosition().getRow();
                tableView.getItems().get(row).setEmail(event.getOldValue());
                tableView.refresh();
            }
        });
        //</editor-fold>

        //<editor-fold desc="Table Col: Password">
        usersPasswordColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        usersPasswordColumn.setOnEditCommit(event -> {
            try {
                String baseVal = event.getOldValue();
                User user = event.getRowValue();
                user.setPassword(Hash.createHash(Validate.validateEquals(Validate.validatePassword(event.getNewValue(), "Password"), Repeat.repeatFieldValue("Password"), "Password")));
                UserManager.updateUser(db, user, "hash");
                System.out.println(user.getPassword());
                Information.display("Password changed", "New password for user \"" + user.getLogin() + "\" is \"" + event.getNewValue() + "\"");

                TableView<User> tableView = event.getTableView();
                TableColumn<User, String> col = event.getTableColumn();
                int row = event.getTablePosition().getRow();
                tableView.getItems().get(row).setPassword("");
                tableView.refresh();
            } catch (Exception e) {
                Warning.display("Error", e.getMessage());
                TableView<User> tableView = event.getTableView();
                TableColumn<User, String> col = event.getTableColumn();
                int row = event.getTablePosition().getRow();
                tableView.getItems().get(row).setEmail(event.getOldValue());
                tableView.refresh();
            }
        });
        //</editor-fold>

        //<editor-fold desc="Table Col: Acccount Type">
        ObservableList<Integer> options = FXCollections.observableArrayList(1, 2, 3);
        usersAccountTypeColumn.setCellFactory(ComboBoxTableCell.forTableColumn(options));
        usersAccountTypeColumn.setOnEditCommit(event -> {
            try {
                User user = event.getRowValue();
                user.setAccountType(event.getNewValue());
                UserManager.updateUser(db, user, "account_level");
            } catch (Exception e) {
                Warning.display("Error", e.getMessage());
            }
        });
        //</editor-fold>

        usersContextItem1 = new MenuItem("Add");
        usersContextItem2 = new MenuItem("Remove");
        contextMenuUsers = new ContextMenu();
        contextMenuUsers.getItems().addAll(usersContextItem1, usersContextItem2);

        usersContextItem1.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(mainApplication.class.getResource("new-user-window.fxml"));
            try {
                Parent root = loader.load();
                NewUserWindow newUserWindowController = loader.getController();
                newUserWindowController.setDBConnector(db);
                Scene scene = new Scene(root);
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);
                primaryStage.showAndWait();
                updateUsersTable();
                usersTable.refresh();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        usersContextItem2.setOnAction(event -> {
            try {
                db.connect();
                db.delete("users", "id", usersTable.getSelectionModel().getSelectedItem().getId());
                db.disconnect();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            usersTable.getItems().remove(usersTable.getSelectionModel().getSelectedItem());
            usersTable.refresh();
            // Add code to perform action 1 here
        });
        usersTable.setContextMenu(contextMenuUsers);

        filterSelect.getItems().setAll(UserFilter.values());

        filterSelect.getSelectionModel().select(0);
        //STORE

        System.out.println("init!");


    }
    //</editor-fold>

    //<editor-fold desc="Set methods">
    public void setDBConnector(DBConnector dbConnector) {
        db = dbConnector;
        updateStoreProductList();
    }

    public void setUser(User user) {
        System.out.println(user);
        currentUser = user;
        if (user.getAccountType() >= 3) {
            tabs.getTabs().remove(productsTab);
            tabs.getTabs().remove(usersTab);
            tabs.getTabs().remove(orderTab);
            //CART
            if (currentUser instanceof Customer cust) {
                System.out.println("User cart GOT");
                cust.setCart(CartManager.getCartFromDB(cust, db));
            }
        }
        if (user.getAccountType() >= 2) {
            usersAccountTypeColumn.setEditable(false);
        }
    }

    ;

    public void setPrimaryStage(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("muShop " + mainApplication.getVersion() + ", logged in as: " + currentUser.getLogin());
    }
    //</editor-fold>
}

