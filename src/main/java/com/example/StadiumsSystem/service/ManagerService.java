package com.example.StadiumsSystem.service;

import com.example.StadiumsSystem.domain.EventType;
import com.example.StadiumsSystem.domain.Manager;
import com.example.StadiumsSystem.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

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

    public boolean isValidationForManagerSuccessful(Manager manager, BindingResult bindingResult) {
        if(isManagerWithDefinedTelephoneNumberExist(manager.getManagerTelephoneNumber())) {
            bindingResult.addError(new FieldError("manager", "managerTelephoneNumber", "Организатор с телефоном '" + manager.getManagerTelephoneNumber() + "' уже существует. Пожалуйста, введите другое номер."));
        }
        return !bindingResult.hasErrors();
    }

    public boolean isManagerWithDefinedTelephoneNumberExist(String managerTelephoneNumber) {
        return managerRepository.findByManagerTelephoneNumber(managerTelephoneNumber) != null;
    }
}
