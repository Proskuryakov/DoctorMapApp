package ru.vsu.cs.app.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.app.db.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByEmail(String email);

}
