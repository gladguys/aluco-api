package com.gladguys.alucoapi.repositories;

import com.gladguys.alucoapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findFirstByEmail(String email);
}
