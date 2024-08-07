package com.example.userservice.repositories;

import com.example.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "select * from userservice_db.user where email=:email",nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Override
    <S extends User> S save(S entity);
}
