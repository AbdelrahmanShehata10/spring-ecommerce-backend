package com.example.E_COMMERCE_PLATFORM.REPO;

import com.example.E_COMMERCE_PLATFORM.Entites.CartItem;
import com.example.E_COMMERCE_PLATFORM.Entites.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    List<CartItem> findByUser(Users user);
    Optional<CartItem> findByIdAndUser(Integer id, Users user);

}
