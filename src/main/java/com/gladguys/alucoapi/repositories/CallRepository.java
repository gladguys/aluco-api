package com.gladguys.alucoapi.repositories;

import com.gladguys.alucoapi.entities.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallRepository extends JpaRepository<Call, Long> {}
