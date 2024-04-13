package com.vsh8k.mushop.model.Database;

import com.vsh8k.mushop.model.AccountSystem.Customer;
import com.vsh8k.mushop.model.AccountSystem.Manager;
import com.vsh8k.mushop.model.AccountSystem.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.SneakyThrows;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    @SneakyThrows
    public static User getUserFromDB(int authorId, DBConnector db) {
        // Your implementation here
        if (!db.isConnected()) {
            db.connect();
        } else return null;
        ResultSet resultSet = db.query("SELECT * FROM users WHERE author_id = " + authorId);
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            int userType = resultSet.getInt("account_level");
            User usr = null;
            String uName = resultSet.getString("login");
            String name = resultSet.getString("name");
            String sName = resultSet.getString("surname");
            String email = resultSet.getString("email");
            String storedHash = null;
            int accountLevel = resultSet.getInt("account_level");
            switch (userType) {
                case 0:
                    usr = new Manager(id, name, sName, uName, storedHash, email, accountLevel, false);
                    break;
                case 1:
                    usr = new Manager(id, name, sName, uName, storedHash, email, accountLevel, true);
                    break;
                case 2:
                    LocalDate createDate = resultSet.getDate("dateCreated").toLocalDate();
                    String cardDetails = ""; //resultSet.getString("cc_details");
                    String deliveryAddr = resultSet.getString("deliveryAddress");
                    String billingAddr = resultSet.getString("billingAddress");
                    LocalDate birthDate = resultSet.getDate("birthDate").toLocalDate();
                    usr = new Customer(id, name, sName, uName, email, storedHash, accountLevel, cardDetails, deliveryAddr, billingAddr, birthDate);
                    break;
            }
            return usr;
        }
        return null;
    }

    public static ObservableList<User> getAllUsersFromDB(DBConnector db) throws Exception {
        ObservableList<User> userList = FXCollections.observableArrayList();
        db.connect();

        ResultSet resultSet = db.query("SELECT * FROM users");
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userType = resultSet.getInt("account_level");
                User usr = null;
                String uName = resultSet.getString("login");
                String name = resultSet.getString("name");
                String sName = resultSet.getString("surname");
                String storedHash = null;
                String email = resultSet.getString("email");
                int accountLevel = resultSet.getInt("account_level");
                switch (userType) {
                    case 1:
                        usr = new Manager(id, name, sName, uName, storedHash, email, accountLevel, false);
                        break;
                    case 2:
                        usr = new Manager(id, name, sName, uName, storedHash, email, accountLevel, true);
                        break;
                    case 3:
                        LocalDate createDate = resultSet.getDate("dateCreated").toLocalDate();
                        String cardDetails = ""; //resultSet.getString("cc_details");
                        String deliveryAddr = resultSet.getString("deliveryAddress");
                        String billingAddr = resultSet.getString("billingAddress");
                        LocalDate birthDate = resultSet.getDate("birthDate").toLocalDate();
                        usr = new Customer(id, name, sName, uName, email, storedHash, accountLevel, cardDetails, deliveryAddr, billingAddr, birthDate);
                        break;
                }
                if (usr != null) {
                    userList.add(usr);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        } finally {
            db.disconnect();
        }

        return userList;
    }

    public static void updateUser(DBConnector db, User user, String colToUpdate) throws Exception {
        db.connect();
        ResultSet userSet = db.query("SELECT * FROM users WHERE id = " + user.getId());
        if (userSet.next()) {
            Object val = null;
            switch (colToUpdate) {
                case "name":
                    val = user.getName();
                    break;
                case "surname":
                    val = user.getSurname();
                    break;
                case "email":
                    val = user.getEmail();
                    break;
                case "hash":
                    val = user.getPassword();
                    System.out.println("GOTIT");
                    break;
                case "account_level":
                    val = user.getAccountType();
                    break;
                case "login":
                    val = user.getLogin();
                    break;
            }
            db.update("users", colToUpdate, val, "id", user.getId());
        } else {
            throw new Exception("User not found");
        }
        db.disconnect();
    }

    public static void addUserToDB(DBConnector db, User user) throws SQLException {
        int id = user.getId();
        String name = user.getName();
        String surname = user.getSurname();
        String login = user.getLogin();
        String hash = user.getPassword();
        int accountType = user.getAccountType();
        String email = user.getEmail();
        String phoneNumber = null;
        String deliveryAddr = null;
        String billingAddr = null;
        Date birthDate = null;
        if (user instanceof Customer) {
            Customer cust = (Customer) user;
            phoneNumber = cust.getPhoneNumber();
            deliveryAddr = cust.getDeliveryAddress();
            billingAddr = cust.getBillingAddress();
            birthDate = Date.valueOf(cust.getBirthDate());
        }
        String[] cols = {"name", "surname", "login", "email", "phone", "hash", "account_level", "deliveryAddress", "billingAddress", "birthDate"};
        Object[] vals = {name, surname, login, email, phoneNumber, hash, accountType, deliveryAddr, billingAddr, birthDate};
        db.connect();
        db.insert("users", cols, vals);
        db.disconnect();
    }

}