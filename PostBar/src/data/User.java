package data;

import javafx.beans.property.SimpleStringProperty;

public class User {
    private final SimpleStringProperty uName;
    private final SimpleStringProperty uPass;
    private final SimpleStringProperty uSex;
    private final SimpleStringProperty uAge;
    private final SimpleStringProperty uPhone;

    public User(String name, String pass, String sex, String age, String phone){
        this.uName = new SimpleStringProperty(name);
        this.uPass = new SimpleStringProperty(pass);
        this.uSex = new SimpleStringProperty(sex);
        this.uAge = new SimpleStringProperty(age);
        this.uPhone = new SimpleStringProperty(phone);
    }


    public String getuName() {
        return uName.get();
    }

    public SimpleStringProperty uNameProperty() {
        return uName;
    }

    public String getuPass() {
        return uPass.get();
    }

    public SimpleStringProperty uPassProperty() {
        return uPass;
    }

    public String getuSex() {
        return uSex.get();
    }

    public SimpleStringProperty uSexProperty() {
        return uSex;
    }

    public String getuAge() {
        return uAge.get();
    }

    public SimpleStringProperty uAgeProperty() {
        return uAge;
    }

    public String getuPhone() {
        return uPhone.get();
    }

    public SimpleStringProperty uPhoneProperty() {
        return uPhone;
    }
}
