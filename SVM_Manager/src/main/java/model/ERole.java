package model;

public enum ERole {
    admin(1, "root" ), user(2, "customer");

    private long id;
    private String name;

    ERole(long id, String name) {
        this.id = id;
        this.name = name;
    }
    public static ERole getERoleByName(String name) {
        for (ERole eRole : ERole.values()) {
            if (eRole.toString().equals(name)) {
                return eRole;
            }
        }
        return null;
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
}
