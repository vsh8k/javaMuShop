package com.vsh8k.mushop.model.Shop;

import com.vsh8k.mushop.model.AccountSystem.Manager;

import java.io.Serializable;
import java.util.ArrayList;

public class Order extends Cart implements Serializable {
    private int id;
    private int status;
    private Manager assignedManager;
    private ArrayList<Message> chat = new ArrayList<Message>();
}
