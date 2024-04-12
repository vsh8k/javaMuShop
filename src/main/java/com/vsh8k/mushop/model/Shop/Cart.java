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
    private LocalDate dateCreated;
}
//Chat bus User:Message-User:Message