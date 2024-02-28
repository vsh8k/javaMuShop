package com.vsh8k.mushop.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Manager extends User {
    private boolean isAdmin;

    public Manager(String name, String surname, String login, String password, boolean isAdmin) {
        super(name, surname, login, password);
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "isAdmin=" + isAdmin +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
