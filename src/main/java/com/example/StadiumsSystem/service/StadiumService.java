package com.example.StadiumsSystem.service;

import com.example.StadiumsSystem.domain.EventType;
import com.example.StadiumsSystem.domain.Stadium;
import com.example.StadiumsSystem.repository.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Service
public class StadiumService {
    private final StadiumRepository stadiumRepository;

    @Autowired
    public StadiumService(StadiumRepository stadiumRepository) {
        this.stadiumRepository = stadiumRepository;
    }

    public Stadium findById(Integer id) {
        return stadiumRepository.getOne(id);
    }

    public List<Stadium> findAll() {
        return stadiumRepository.findAll();
    }

    public Stadium saveStadium(Stadium stadium) {
        return stadiumRepository.save(stadium);
    }

    public void deleteById(Integer id) {
        stadiumRepository.deleteById(id);
    }

    public boolean isValidationForCreateStadiumSuccessful(Stadium stadium, BindingResult bindingResult) {
        String stadiumName = stadium.getStadiumName();
        if(isStadiumWithDefinedNameExist(stadiumName)) {
            bindingResult.addError(new FieldError("stadium", "stadiumName", "Стадион с именем '" + stadiumName + "' уже существует. Пожалуйста, введите другое имя."));
        }
        return !bindingResult.hasErrors();
    }

    public boolean isValidationForUpdateStadiumSuccessful(Stadium stadium, BindingResult bindingResult) {
        String stadiumName = stadium.getStadiumName();
        if(isStadiumWithDefinedNameExist(stadiumName)) {
            Stadium checkStadium = stadiumRepository.findByStadiumName(stadiumName);
            if (!stadium.getId().equals(checkStadium.getId())) {
                bindingResult.addError(new FieldError("stadium", "stadiumName", "Стадион с именем '" + stadiumName + "' уже существует. Пожалуйста, введите другое имя."));
                return false;
            }
        }
        return true;
    }

    public boolean isStadiumWithDefinedNameExist(String stadiumName) {
        return stadiumRepository.findByStadiumName(stadiumName) != null;
    }
}