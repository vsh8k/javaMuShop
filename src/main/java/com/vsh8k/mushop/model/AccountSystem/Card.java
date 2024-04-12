package com.vsh8k.mushop.model.AccountSystem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Card {
    private String cardNumber;
    private LocalDate expiryDate;
    private String cvv;
}
