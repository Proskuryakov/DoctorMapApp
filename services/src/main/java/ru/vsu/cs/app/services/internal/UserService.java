package ru.vsu.cs.app.services.internal;


import ru.vsu.cs.app.services.models.User;

public interface UserService {

    User createUser(User user);

    User getById(Long id);

    User update(Long id, User updatableUser);

    boolean deleteById(Long id);

}
