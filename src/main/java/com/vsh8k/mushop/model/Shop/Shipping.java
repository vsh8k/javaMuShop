package com.vsh8k.mushop.model.Shop;

import com.vsh8k.mushop.model.Database.DBConnector;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Shipping {
    private String optionName;
    private float price;

    @Override
    public String toString() {
        return optionName;
    }

    @SneakyThrows
    public static ArrayList<Shipping> getServices(DBConnector db) {
        db.connect();
        ResultSet services = db.query("SELECT * FROM shipping");
        ArrayList<Shipping> servicesList = new ArrayList<>();
        while (services.next()) {
            servicesList.add(new Shipping(services.getString("option_name"), services.getFloat("option_price")));
        }
        return servicesList;
    }
}

