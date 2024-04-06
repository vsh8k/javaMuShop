package com.vsh8k.mushop.model.Shop;

import com.vsh8k.mushop.model.AccountSystem.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private int id;
    private User author;
    private String body;
}
