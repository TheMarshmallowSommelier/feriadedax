package com.feriadedaxecommerce.repository;

import com.feriadedaxecommerce.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
}

