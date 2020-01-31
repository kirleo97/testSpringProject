package com.example.StadiumsSystem.service;

import com.example.StadiumsSystem.domain.Stadium;
import com.example.StadiumsSystem.repository.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /*
    public List<Stadium> findByEventType(EventType eventType) {
        return stadiumRepository.findAllByEventType(eventType);
    }
    */
}
