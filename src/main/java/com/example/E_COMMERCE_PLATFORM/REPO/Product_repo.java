package com.example.E_COMMERCE_PLATFORM.REPO;

import com.example.E_COMMERCE_PLATFORM.Entites.Product;
import com.example.E_COMMERCE_PLATFORM.Entites.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Product_repo extends JpaRepository<Product, Integer> {

}
