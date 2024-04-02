package com.vsh8k.mushop.model.Shop;

import com.vsh8k.mushop.model.AccountSystem.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Message {
    private User author;
    private String body;
}
