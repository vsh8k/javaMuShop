package com.vsh8k.mushop.model.AccountSystem;

import javafx.beans.DefaultProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
@Setter
public abstract class User{
    protected SimpleIntegerProperty id = new SimpleIntegerProperty();
    protected SimpleStringProperty name = new SimpleStringProperty();
    protected SimpleStringProperty surname = new SimpleStringProperty();
    protected SimpleStringProperty login = new SimpleStringProperty();
    protected SimpleStringProperty password = new SimpleStringProperty();
    protected SimpleIntegerProperty accountType = new SimpleIntegerProperty();
    protected SimpleObjectProperty dateCreated = new SimpleObjectProperty();
    protected SimpleObjectProperty dateModified = new SimpleObjectProperty();
    protected SimpleStringProperty email = new SimpleStringProperty();

    public User(int id, String name, String surname, String login, String password, String email, int accountType) {
        this.id.set(id);
        this.name.set(name);
        this.surname.set(surname);
        this.login.set(login);
        this.password.set(password);
        this.email.set(email);
        this.accountType.set(accountType);
        this.dateCreated.set(LocalDate.now());
        this.dateModified.set(LocalDate.now());
    }

    public int getId() {
        return id.get();
    }

    public String getSurname() {
        return surname.get();
    }

    public String getName() {
        return name.get();
    }

    public String getEmail() {
        return email.get();
    }

    public int getAccountType() {
        return accountType.get();
    }

    public String getPassword() {
        return "Hey, it's not your data!";
    }

    public String getLogin() {
        return login.get();
    }
}
