package com.javainuse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javainuse.model.User;

public interface Repository extends JpaRepository<User, Integer> {

}
