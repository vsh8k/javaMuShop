package com.vsh8k.mushop.model.Database;

import com.vsh8k.mushop.model.AccountSystem.Customer;
import com.vsh8k.mushop.model.AccountSystem.Manager;
import com.vsh8k.mushop.model.AccountSystem.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.SneakyThrows;

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
        }
        else return null;
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
    public static ObservableList<User> getAllUsersFromDB(DBConnector db) throws Exception{
    ObservableList<User> userList = FXCollections.observableArrayList();
    if (!db.isConnected()) {
        db.connect();
    } else {
        return null; // or throw an exception indicating the database connection issue
    }

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
            if (usr != null) {
                userList.add(usr);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Handle or log the exception appropriately
    }

    return userList;
}

}