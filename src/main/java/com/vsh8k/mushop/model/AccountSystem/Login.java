package com.vsh8k.mushop.model.AccountSystem;

import javax.xml.datatype.DatatypeFactory;

import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;

import com.vsh8k.mushop.model.Database.DBConnector;
import com.vsh8k.mushop.model.Shop.Media;
import org.apache.commons.codec.binary.Hex;

import java.util.HexFormat;

public class Login {

    public static User getUser(String username, String password) throws Exception {
        String dbUrl = "jdbc:mysql://localhost:3306/products";
        String dbUsername = "root";
        String dbPassword = "";
        DBConnector loginConnector = new DBConnector(dbUrl, dbUsername, dbPassword);
        loginConnector.connect();
        String storedHash = Hash.getStoredHash(username, loginConnector);
        if(!Hash.verifyPassword(password, storedHash)) {
            throw new Exception("Wrong credentials");
        }
        System.out.println(storedHash);
        ResultSet resultSet = loginConnector.query("SELECT * FROM users WHERE login = '" + username + "'");
        if(resultSet.next()) {
            int userType = resultSet.getInt("account_level");
            User usr = null;
            String uName = resultSet.getString("login");
            String name = resultSet.getString("name");
            String sName = resultSet.getString("surname");
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
