package com.feriadedaxecommerce.repository;

import com.feriadedaxecommerce.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}

