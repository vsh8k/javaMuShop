package com.vsh8k.mushop.model.AccountSystem;

import com.vsh8k.mushop.model.Misc.Validate;
import com.vsh8k.mushop.model.Shop.Cart;
import com.vsh8k.mushop.model.Shop.CreditCard;
import com.vsh8k.mushop.model.Shop.Product;
import jakarta.persistence.criteria.Order;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

@Getter
@Setter
public final class Customer extends User {

    private String phoneNumber;
    private String deliveryAddress;
    private String billingAddress;
    private LocalDate birthDate;
    private Cart cart;
    private ArrayList<Order> orders;
    private CreditCard card;


    public Customer(int id, String name, String surname, String login, String email, String phoneNumber, String password, int accountType, CreditCard card, String deliveryAddress, String billingAddress, LocalDate birthDate) {
        super(id, name, surname, login, password, email, accountType);
        this.deliveryAddress = deliveryAddress;
        this.billingAddress = billingAddress;
        this.birthDate = birthDate;
        this.card = card;
        this.phoneNumber = phoneNumber;
    }

    public Customer(int id, String name, String surname, String login, String email, String password, int accountType) {
        super(id, name, surname, login, password, email, accountType);
    }

    public void addToCart(Product product, int qty) throws Validate.ValidationException {
        this.cart.addProduct(product, qty);
        System.out.println(product.toString() + " added to cart");
    }

    @Override
    public String toString() {
        return "login: " + login;
    }
}
