package com.example.StadiumsSystem.repository;

import com.example.StadiumsSystem.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    Manager findByManagerTelephoneNumber(String managerTelephoneNumber);
}