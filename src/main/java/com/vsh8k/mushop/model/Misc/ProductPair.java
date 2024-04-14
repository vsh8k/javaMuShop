package com.vsh8k.mushop.model.Misc;

import com.vsh8k.mushop.model.Shop.Product;
import javafx.util.Pair;

public class ProductPair<P, I extends Number> extends Pair<Product, Integer> {

    public ProductPair(Product key, Integer value) {
        super(key, value);
    }

    @Override
    public String toString() {
        return getKey().getEan() + " | " + getKey().toString() + " | " + getValue().toString() + " | " + getKey().getPrice() * getValue();
    }
}
