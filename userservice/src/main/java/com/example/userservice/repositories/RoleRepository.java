package com.example.userservice.repositories;

import com.example.userservice.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    @Override
    <S extends Role> S save(S entity);

    @Override
    List<Role> findAllById(Iterable<Long> longs);
}
