package com.ajBinary.CharShow.repository;

import com.ajBinary.CharShow.entity.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends CrudRepository<Owner,Long> {
    Optional<Object> findOwnerByFirstName(String firstName);
}