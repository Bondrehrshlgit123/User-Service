package com.example.userservice.repositories;

import com.example.userservice.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {
    @Query(value = "select * from userservice_db.session where user_id=:user_id and token=:token",nativeQuery = true)
    Optional<Session> findByUseridAndToken(Long user_id, String token);

    @Override
    <S extends Session> S save(S entity);
}
