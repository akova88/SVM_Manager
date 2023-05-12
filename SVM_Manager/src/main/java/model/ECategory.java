package model;

import java.util.List;

public enum ECategory {
    DRINK(1, "Đồ uống"),
    FAST_FOOD(2, "Đồ ăn nhanh");

    private int id;
    private String name;

    ECategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ECategory getEcategoryById(int idCategory) {
        for (ECategory eCategory : ECategory.values()) {
            if (eCategory.getId() == idCategory) {
                return eCategory;
            }
        }
        return null;
    }

    public static ECategory getEcategoryByName(String name) {
        for (ECategory eCategory : ECategory.values()) {
            if (eCategory.toString().equals(name)) {
                return eCategory;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
