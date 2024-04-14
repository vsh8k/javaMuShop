package com.vsh8k.mushop.model.Shop;

import com.vsh8k.mushop.model.AccountSystem.Customer;
import com.vsh8k.mushop.model.AccountSystem.Manager;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Cart{
    private int id;
    private List<Product> productList;
    private Customer customer;

    public void addProduct(Product product){
        productList.add(product);
        System.out.println(productList.size());
    }

    public void removeProduct(Product product){
        productList.remove(product);
        System.out.println(productList.size());
    }
}