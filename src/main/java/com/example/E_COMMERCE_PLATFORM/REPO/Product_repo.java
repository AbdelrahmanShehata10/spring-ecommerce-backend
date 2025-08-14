package com.example.E_COMMERCE_PLATFORM.REPO;

import com.example.E_COMMERCE_PLATFORM.Entites.CartItem;
import com.example.E_COMMERCE_PLATFORM.Entites.Product;
import com.example.E_COMMERCE_PLATFORM.Entites.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Product_repo extends JpaRepository<Product, Integer> {


    Optional<Product> findById(int id);
    Optional<Product> findByIdAndUser(Integer id, Users user);
    List<CartItem> findByUser(Users user);


}
