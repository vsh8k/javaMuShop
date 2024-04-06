package com.vsh8k.mushop.model.Database;

import com.vsh8k.mushop.model.AccountSystem.Customer;
import com.vsh8k.mushop.model.AccountSystem.Manager;
import com.vsh8k.mushop.model.AccountSystem.User;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.time.LocalDate;

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
            int userType = resultSet.getInt("account_level");
            User usr = null;
            String uName = resultSet.getString("login");
            String name = resultSet.getString("name");
            String sName = resultSet.getString("surname");
            String storedHash = null;
            switch (userType) {
                case 0:
                    usr = new Manager(name, sName, uName, storedHash, false);
                    break;
                case 1:
                    usr = new Manager(name, sName, uName, storedHash, true);
                    break;
                case 2:
                    LocalDate createDate = resultSet.getDate("dateCreated").toLocalDate();
                    String cardDetails = ""; //resultSet.getString("cc_details");
                    String deliveryAddr = resultSet.getString("deliveryAddress");
                    String billingAddr = resultSet.getString("billingAddress");
                    LocalDate birthDate = resultSet.getDate("birthDate").toLocalDate();
                    usr = new Customer(name, sName, uName, storedHash, cardDetails, deliveryAddr, billingAddr, birthDate);
                    break;
            }
            return usr;
        }
        return null;
    }
}