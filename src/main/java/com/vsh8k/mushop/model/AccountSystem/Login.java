package com.vsh8k.mushop.model.AccountSystem;

import java.sql.ResultSet;
import java.time.LocalDate;

import com.vsh8k.mushop.model.Database.DBConnector;

public class Login {

    public static User validateAndGetUser(String username, String password, DBConnector loginConnector) throws Exception {

        loginConnector.connect();
        String storedHash = Hash.getStoredHash(username, loginConnector);
        if(!Hash.verifyPassword(password, storedHash)) {
            throw new Exception("Wrong credentials");
        }
        System.out.println(storedHash);
        ResultSet resultSet = loginConnector.query("SELECT * FROM users WHERE login = '" + username + "'");
        if(resultSet.next()) {
            int id = resultSet.getInt("id");
            int userType = resultSet.getInt("account_level");
            User usr = null;
            String uName = resultSet.getString("login");
            String name = resultSet.getString("name");
            String sName = resultSet.getString("surname");
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
            loginConnector.disconnect();
            return usr;
        }
        loginConnector.disconnect();
        return null;
    }
}
