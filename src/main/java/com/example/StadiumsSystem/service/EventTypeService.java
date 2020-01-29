package com.example.StadiumsSystem.service;

import com.example.StadiumsSystem.domain.EventType;
import com.example.StadiumsSystem.repository.EventTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventTypeService {
    private final EventTypeRepository eventTypeRepository;

    @Autowired
    public EventTypeService(EventTypeRepository eventTypeRepository) {
        this.eventTypeRepository = eventTypeRepository;
    }

    public EventType findById(Integer id) {
        return eventTypeRepository.getOne(id);
    }

    public EventType findByName(String name) {
        return eventTypeRepository.findByEventTypeName(name);
    }

    public List<EventType> findAll() {
        return eventTypeRepository.findAll();
    }

    public EventType saveEventType(EventType eventType) {
        return eventTypeRepository.save(eventType);
    }

    public void deleteById(Integer id) {
        eventTypeRepository.deleteById(id);
    }
}
