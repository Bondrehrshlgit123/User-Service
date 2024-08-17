package com.example.userservice.repositories;

import com.example.userservice.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    @Override
    <S extends Role> S save(S entity);

    @Override
    Optional<Role> findById(Long aLong);

    @Override
    List<Role> findAll();

    @Override
    List<Role> findAllById(Iterable<Long> longs);
}
