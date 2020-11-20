package ru.vsu.cs.app.db.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "user_role")
public class UserRoleModel {

    @Id
    Long id;
    String name;

    @OneToMany(mappedBy = "userRole")
    List<UserModel> userModels;

}
