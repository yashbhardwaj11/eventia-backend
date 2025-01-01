package com.devinfusion.eventia.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devinfusion.eventia.events.entity.Event;

public interface EventRepository extends JpaRepository<Event,String>{
    
}
