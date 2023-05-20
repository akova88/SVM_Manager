package model;

import javax.accessibility.AccessibleRole;

public class User implements IModel<User>{
    private long id;
    private String name;
    private String userName;
    private String passWord;
    private ERole eRole;

    public User() {
    }

    public User(long id, String name, String userName, String passWord, ERole eRole) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.passWord = passWord;
        this.eRole = eRole;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", this.id, this.name, this.userName, this.passWord, this.eRole);
    }

    @Override
    public void parseData(String line) {
        String[] items = line.split(",");
        this.setId(Long.parseLong(items[0]));
        this.setName(items[1]);
        this.setUserName(items[2]);
        this.setpassWord(items[3]);
        this.seteRole(ERole.getERoleByName(items[4]));
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpassWord() {
        return passWord;
    }

    public void setpassWord(String passWord) {
        this.passWord = passWord;
    }

    public ERole geteRole() {
        return eRole;
    }

    public void seteRole(ERole eRole) {
        this.eRole = eRole;
    }
}
