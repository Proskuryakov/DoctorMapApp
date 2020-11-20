package ru.vsu.cs.app.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.app.db.models.UserRole;
import ru.vsu.cs.app.db.models.UserRoleModel;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleModel, Long> {

    UserRoleModel findByName(String name);
}
