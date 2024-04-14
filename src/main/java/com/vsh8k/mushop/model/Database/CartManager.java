package com.vsh8k.mushop.model.Database;

import com.sun.source.doctree.SeeTree;
import com.vsh8k.mushop.model.AccountSystem.User;
import com.vsh8k.mushop.model.Shop.Cart;
import com.vsh8k.mushop.model.Shop.Product;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CartManager {

    private static final String[] cartCols = {"id", "product_list"};

    @SneakyThrows
    public static void getCartFromDB(User user, DBConnector db) {
        db.connect();
        ResultSet cartQuery = db.query("SELECT product_list FROM carts WHERE id = " + user.getId());
        Cart cart = new Cart();
        if(cartQuery.next()) {
            for(String s : cartQuery.getString("product_list").split(";")) {
                cart.addProduct(ProductManager.getProductById(Integer.parseInt(s), db));
            }
        }
        else {
            db.insert("carts", cartCols, new Object[]{user.getId(), ""});
        }
    }

}
