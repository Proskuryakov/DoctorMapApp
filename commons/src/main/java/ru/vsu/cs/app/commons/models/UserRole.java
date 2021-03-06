package ru.vsu.cs.app.commons.models;

public enum UserRole {

    ADMIN(1),
    DOCTOR(2);

    private final int id;

    UserRole(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static UserRole fromId(Integer id) {
        if (id == null) {
            return null;
        }

        for (var value : values()) {
            if (id.equals(value.id)) {
                return value;
            }
        }

        return null;
    }

}
