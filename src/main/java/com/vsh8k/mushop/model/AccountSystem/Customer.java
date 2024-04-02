package com.vsh8k.mushop.model.AccountSystem;

import com.vsh8k.mushop.model.Shop.Cart;
import com.vsh8k.mushop.model.Shop.CreditCard;
import jakarta.persistence.criteria.Order;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

@Getter
@Setter
public class Customer extends User {

    private String deliveryAddress;
    private String billingAddress;
    private LocalDate birthDate;
    private Cart cart;
    private ArrayList<Order> orders;
    private CreditCard card;


    public Customer(String name, String surname, String login, String password, String cardDetails, String deliveryAddress, String billingAddress, LocalDate birthDate) {
        super(name, surname, login, password);
        String[] cardDetailsRaw;
        cardDetailsRaw = cardDetails.split("-");
        this.deliveryAddress = deliveryAddress;
        this.billingAddress = billingAddress;
        this.birthDate = birthDate;
        this.card = new CreditCard(cardDetailsRaw[0], cardDetailsRaw[1], cardDetailsRaw[2], cardDetailsRaw[3]);
    }

    public Customer(String name, String surname, String login, String password) {
        super(name, surname, login, password);
    }

    @Override
    public String toString() {
        return "login: " + login;
    }
}
