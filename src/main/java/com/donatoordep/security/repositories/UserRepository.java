package com.donatoordep.security.repositories;

import com.donatoordep.security.entities.User;
import com.donatoordep.security.projections.UserMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = """
                SELECT tb_user.name, tb_user.email, tb_user.password, tb_role.role_name
                FROM tb_user_role
                INNER JOIN tb_user ON tb_user_role.user_id = tb_user.id
                INNER JOIN tb_role ON tb_role.id = tb_user_role.role_id
                WHERE tb_user.email LIKE UPPER(CONCAT('%', :email, '%'))""")
    List<UserMinProjection> findUserByEmail(String email);
}
