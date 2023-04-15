package com.feriadedaxecommerce.repository;

import com.feriadedaxecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}

