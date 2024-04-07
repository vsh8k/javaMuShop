package com.vsh8k.mushop.model.AccountSystem;

import com.vsh8k.mushop.model.AccountSystem.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Manager extends User {
    private boolean isAdmin;

    public Manager(int id, String name, String surname, String login, String password, String email, int accountType, boolean isAdmin) {
        super(id, name, surname, login, password, email, accountType);
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
