package ru.vsu.cs.app.services.internal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vsu.cs.app.db.models.ResponseUserModel;
import ru.vsu.cs.app.db.repositories.UserRepository;
import ru.vsu.cs.app.services.internal.UserService;
import ru.vsu.cs.app.services.mappers.UserMapper;
import ru.vsu.cs.app.services.models.User;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        var encodePassword = passwordEncoder.encode(user.getPassword());

        var userModel = userMapper.toModel(user, encodePassword);

        final var resUser = userRepository.createUser(userModel);
        return userMapper.fromModel(resUser, user.getRole());
    }

    @Override
    public User getById(Long id) {
        ResponseUserModel userModel = userRepository.findById(id).orElse(null);

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

    @Override
    public User getCurrentUser(Object object) {

        if (!(object instanceof ResponseUserModel)) {
            return null;
        }

        return userMapper.fromModel((ResponseUserModel) object);
    }


}
