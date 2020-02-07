package com.example.StadiumsSystem.service;

import com.example.StadiumsSystem.domain.Event;
import com.example.StadiumsSystem.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event findById(Integer id) {
        return eventRepository.getOne(id);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public void deleteById(Integer id) {
        eventRepository.deleteById(id);
    }

    public void checkIfDateOfEventIsInPeriodOfPreparationAndDismantle(Event event, BindingResult bindingResult) {
        LocalDate dateOfEvent = LocalDate.from(event.getDateOfEvent());
        if (dateOfEvent.isBefore(event.getStartOfPreparationOfStadium()) || dateOfEvent.isAfter(event.getEndOfDismantleOfStadium())) {
            bindingResult.addError(new FieldError("event", "dateOfEvent", "Дата мероприятия должна входить в период подготовки и окончания мероприятия ( " + dateOfEvent));
        }
    }

    public void checkIfStartOfPreparationIsBeforeEndOfDismantleOrEquals(Event event, BindingResult bindingResult) {
        if (Period.between(event.getStartOfPreparationOfStadium(), event.getEndOfDismantleOfStadium()).isNegative()) {
            bindingResult.addError(new FieldError("event", "endOfDismantleOfStadium", "Выбранный период невозможен, так как начало периода не должно быть после его окончания ( " + event.getStartOfPreparationOfStadium() + " - " + event.getEndOfDismantleOfStadium()));
        }
    }

    public void checkIfPerionOfEventIsNotIntersectWithOtherEventsOfStadium(Event checkEvent, BindingResult bindingResult) {
        List<Event> otherEventsByStadium = eventRepository.findAllByStadiumOfEvent(checkEvent.getStadiumOfEvent());
        if (checkEvent.getId() != null) {
            Event event = findById(checkEvent.getId());
            otherEventsByStadium.remove(event);
        }
        LocalDate startOfCheckEvent = checkEvent.getStartOfPreparationOfStadium();
        LocalDate endOfCheckEvent = checkEvent.getEndOfDismantleOfStadium();
        LocalDate startOfOtherEvent;
        LocalDate endOfOtherEvent;
        for (Event event : otherEventsByStadium) {
            startOfOtherEvent = event.getStartOfPreparationOfStadium();
            endOfOtherEvent = event.getEndOfDismantleOfStadium();
            if (startOfCheckEvent.isBefore(startOfOtherEvent)) {
                if (!endOfCheckEvent.isBefore(startOfOtherEvent)) {
                    bindingResult.addError(new FieldError("checkEvent", "endOfDismantleOfStadium", "Выбранный период мероприятия пересекается с проведением другого мероприятия: " + startOfOtherEvent + " - " + endOfOtherEvent));
                }
            }
            if (startOfCheckEvent.isAfter(startOfOtherEvent)) {
                if (!startOfCheckEvent.isAfter(endOfOtherEvent)) {
                    bindingResult.addError(new FieldError("checkEvent", "endOfDismantleOfStadium", "Выбранный период мероприятия пересекается с проведением другого мероприятия: " + startOfOtherEvent + " - " + endOfOtherEvent));
                }
            }
            if (startOfCheckEvent.isEqual(startOfOtherEvent)) {
                bindingResult.addError(new FieldError("checkEvent", "startOfPreparationOfStadium", "Выбранный период мероприятия пересекается с проведением другого мероприятия: " + startOfOtherEvent + " - " + endOfOtherEvent));
            }
        }
    }

    public void checkValidationFormForEvent(Event event, BindingResult bindingResult) {
        checkIfDateOfEventIsInPeriodOfPreparationAndDismantle(event, bindingResult);
        checkIfPerionOfEventIsNotIntersectWithOtherEventsOfStadium(event, bindingResult);
        checkIfStartOfPreparationIsBeforeEndOfDismantleOrEquals(event, bindingResult);
    }
}