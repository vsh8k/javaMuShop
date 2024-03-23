package com.vsh8k.mushop.model.AccountSystem;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
@Getter
@Setter
public abstract class User implements Serializable {
    //Man reikes veliau, kai dirbsiu su db
    protected int id;
    protected String name;
    protected String surname;
    protected String login;
    protected String password;
    protected LocalDate dateCreated;
    protected LocalDate dateModified;

    public User(String name, String surname, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.dateCreated = LocalDate.now();
    }


}
