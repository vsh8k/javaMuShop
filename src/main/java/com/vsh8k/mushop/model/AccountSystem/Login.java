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
import org.apache.commons.codec.binary.Hex;
import java.util.HexFormat;

public class Login {

    public static User getUser(String username, String password) throws Exception{
        String client_hash = Hash.getHash(username, password);
        String dbUrl = "jdbc:mysql://localhost:3306/mushop";
        String dbUsername = "root";
        String dbPassword = "";
        DBConnector loginConnector = new DBConnector(dbUrl, dbUsername, dbPassword);

        try {
            loginConnector.connect();
            ResultSet resultSet = loginConnector.query("SELECT * FROM users WHERE username = '" + username + "'");
            if (resultSet.next()) {
                String server_hash = resultSet.getString("password");
                if(client_hash.equals(server_hash))
                {
                    System.out.println("Tu dievas, prisijungei!!!");
                }
                else {
                    throw new Exception("Exception message");//Blogas pwd
                }
            } else {
                throw new Exception("Exception message");//Blogas pwd
            }
            int userType = resultSet.getInt("account_level");
            switch (userType) {
                case 0:
                    System.out.println("Admin");
                    break;
                case 1:
                    System.out.println("Manager");
                    break;
                case 2:
                    String uName = resultSet.getString("username");
                    String name = resultSet.getString("first_name");
                    String sName = resultSet.getString("last_name");
                    LocalDate createDate = resultSet.getDate("create_date").toLocalDate();
                    String cardDetails = resultSet.getString("cc_details");
                    String deliveryAddr = resultSet.getString("delivery_address");
                    String billingAddr = resultSet.getString("billing_address");
                    LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();
                    User usr = new Customer(name, sName, uName, client_hash,cardDetails,deliveryAddr,billingAddr,birthDate);
                    break;
            }
            User usr = new Manager("1", "1", "1", "1", true);
            return usr;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getToken(String uname, String pass) {
        return "THISISATOKEN";
    }
    public static void getUser(String token) {
    //Pakeisti void į user, kai baigsiu su SQL'u
    }
}
