package com.vsh8k.mushop.model.Shop;

import com.vsh8k.mushop.model.AccountSystem.Customer;
import com.vsh8k.mushop.model.AccountSystem.Manager;
import com.vsh8k.mushop.model.Misc.Validate;
import javafx.util.Pair;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Cart {
    private int id;
    private ArrayList<Pair<Product, Integer>> productList = new ArrayList<>();

    private Customer customer;

    public void addProduct(Product product, int qty) throws Validate.ValidationException{
        for (Pair<Product, Integer> pair : productList) {
            if (pair.getKey().getId() == product.getId()) {
                if (pair.getValue() + qty > pair.getKey().getQty()) {
                    throw new Validate.ValidationException("Selected amount exceeds available amount");
                }
                int index = productList.indexOf(pair);
                productList.set(index, new Pair<>(product, (pair.getValue() + qty)));
                System.out.println("SUMA");
                return;
            }
        }
        productList.add(new Pair<>(product, qty));
    }

    public void changeProductQty(Product product, int qty) {
        for (Pair<Product, Integer> pair : productList) {
            if (pair.getKey().equals(product)) {
                pair = new Pair<>(product, (qty));
                return;
            }
        }
    }

    public void removeProduct(Product product) {
        for (Pair<Product, Integer> pair : productList) {
            if (pair.getKey().equals(product)) {
                productList.remove(pair);
            }
        }
    }

    public String toString() {
        String str = "";
        for (Pair<Product, Integer> pair : productList) {
            str += pair.getKey().getId() + ":" + pair.getValue() + ";";
        }
        return str;
    }
}