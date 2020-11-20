package ru.vsu.cs.app.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.app.db.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    @Query(
            value = "select u.id, email, password, fio, ur.name as role from users u " +
                    "join user_role ur on ur.id = u.role_id where u.id = ?1",
            nativeQuery = true
    )
    UserModel findByIdJoinRole(Long id);
//
//    @Query(
//            value = "insert into users (email, password, fio, role_id) VALUES (?1.)",
//            nativeQuery = true
//    )
//    UserModel saveWithGetDbRoleId(UserModel userModel);

}
