package com.example.StadiumsSystem.service;

import com.example.StadiumsSystem.domain.Manager;
import com.example.StadiumsSystem.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public Manager findById(Integer id) {
        return managerRepository.getOne(id);
    }

    public List<Manager> findAll() {
        return managerRepository.findAll();
    }

    public Manager saveManager(Manager manager) {
        return managerRepository.save(manager);
    }

    public void deleteById(Integer id) {
        managerRepository.deleteById(id);
    }
}
