package com.example.E_COMMERCE_PLATFORM.REPO;

import com.example.E_COMMERCE_PLATFORM.Entites.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
Optional<Users> findByEmail(String Email);
    Optional<Users> findByUsername(String username);
}
