package com.vsh8k.mushop.model.Database;

import com.sun.source.doctree.SeeTree;
import com.vsh8k.mushop.model.AccountSystem.Customer;
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
    public static Cart getCartFromDB(User user, DBConnector db) {
        db.connect();
        ResultSet cartQuery = db.query("SELECT product_list FROM carts WHERE id = " + user.getId());
        Cart cart = new Cart();
        if (cartQuery.next()) {
            if (!cartQuery.getString("product_list").isEmpty()) {
                System.out.println(cartQuery.getString("product_list"));
                for (String s : cartQuery.getString("product_list").split(";")) {
                    String[] split = s.split(":");
                    cart.addProduct(ProductManager.getProductById(Integer.parseInt(split[0]), db), Integer.parseInt(split[1]));
                }
            }
        } else {
            db.insert("carts", cartCols, new Object[]{user.getId(), ""});
        }
        return cart;
    }

    @SneakyThrows
    public static void updateCartInDB(User user, DBConnector db) {
        if (user instanceof Customer cust) {
            db.connect();
            db.update("carts", "product_list", cust.getCart().toString(), "id", cust.getId());
            db.disconnect();
        }
    }

}
