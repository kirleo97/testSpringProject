package com.example.StadiumsSystem.service;

import com.example.StadiumsSystem.domain.EventType;
import com.example.StadiumsSystem.repository.EventTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Service
public class EventTypeService {
    private final EventTypeRepository eventTypeRepository;

    @Autowired
    public EventTypeService(EventTypeRepository eventTypeRepository) {
        this.eventTypeRepository = eventTypeRepository;
    }

    public EventType findById(Integer id) {
        return eventTypeRepository.findById(id).orElse(null);
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

    public boolean isValidationForEventTypeSuccessful(EventType eventType, BindingResult bindingResult) {
        EventType checkEventType = eventTypeRepository.findByEventTypeName(eventType.getEventTypeName());
        if (checkEventType != null) {
            if (!checkEventType.getId().equals(eventType.getId())) {
                bindingResult.addError(new FieldError("eventType", "eventTypeName", "EventType with name '" + eventType.getEventTypeName() + "' already exists. Please enter a different name."));
            }
        }
        return !bindingResult.hasErrors();
    }
}