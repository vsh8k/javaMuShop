package com.vsh8k.mushop.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Cart {
    private int id;
    private List<Product> productList;
    private Manager manager;
    private Customer customer;
    private LocalDate dateCreated;
    private List<Comment> chat;
}
