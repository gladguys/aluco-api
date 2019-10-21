package com.gladguys.alucoapi.repositories;

import com.gladguys.alucoapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
