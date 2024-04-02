package com.vsh8k.mushop.model.Shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {
    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String cvv;
    public String toString() {
        return cardNumber + "-" + cardHolderName + "-" + expirationDate + "-" + cvv;
    }

}
