package ru.vsu.cs.app.services.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vsu.cs.app.db.models.UserModel;
import ru.vsu.cs.app.db.repositories.UserRepository;
import ru.vsu.cs.app.db.repositories.UserRoleRepository;
import ru.vsu.cs.app.services.internal.UserService;
import ru.vsu.cs.app.services.mappers.UserMapper;
import ru.vsu.cs.app.services.models.User;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        var userRole = userRoleRepository.findByName(user.getRole().name());

        var encodePassword = passwordEncoder.encode(user.getPassword());

        var userModel = userMapper.toModel(user, encodePassword);
        userModel.setUserRole(userRole);

        final var resUser = userRepository.save(userModel);

        return userMapper.fromModel(resUser);
    }

    @Override
    public User getById(Long id) {
        UserModel userModel = userRepository.findById(id).orElse(null);

        if (userModel == null)
            return null;

        return userMapper.fromModel(userModel);
    }

    @Override
    public User update(Long id, User updatableUser) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

}
