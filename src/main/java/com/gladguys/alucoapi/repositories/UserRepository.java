package com.gladguys.alucoapi.repositories;

import com.gladguys.alucoapi.entities.User;
import com.gladguys.alucoapi.repositories.customs.CustomUserRepository;
import com.gladguys.alucoapi.repositories.customs.CustomUserRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {
	User findFirstByEmail(String email);
}
