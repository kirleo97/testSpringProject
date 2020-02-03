package com.example.StadiumsSystem.domain;

import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EVENT_ID")
    private Integer id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "EVENT_EVENTTYPE", nullable = false)
    private EventType eventType;

    @Column(name = "EVENT_NAME", nullable = false)
    private String eventName;

    @Column(name = "EVENT_DATE", nullable = false)
    private ZonedDateTime dateOfEvent;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "STADIUM_ID", nullable = false)
    private Stadium stadiumOfEvent;

    @Column(name = "event_startOfPreparationOfStadium", nullable = false)
    private LocalDate startOfPreparationOfStadium;

    @Column(name = "event_endOfDismantleOfStadium", nullable = false)
    private LocalDate endOfDismantleOfStadium;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MANAGER_ID", nullable = false)
    private Manager eventManager;

    public Event() {
    }

    @Autowired
    public Event(EventType eventType, String eventName, ZonedDateTime dateOfEvent, Stadium stadiumOfEvent, LocalDate startOfPreparationOfStadium, LocalDate endOfDismantleOfStadium, Manager eventManager) {
        this.eventType = eventType;
        this.eventName = eventName;
        this.dateOfEvent = dateOfEvent;
        this.stadiumOfEvent = stadiumOfEvent;
        this.startOfPreparationOfStadium = startOfPreparationOfStadium;
        this.endOfDismantleOfStadium = endOfDismantleOfStadium;
        this.eventManager = eventManager;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public ZonedDateTime getDateOfEvent() {
        return dateOfEvent;
    }

    public void setDateOfEvent(ZonedDateTime dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    public Stadium getStadiumOfEvent() {
        return stadiumOfEvent;
    }

    public void setStadiumOfEvent(Stadium stadiumOfEvent) {
        this.stadiumOfEvent = stadiumOfEvent;
    }

    public LocalDate getStartOfPreparationOfStadium() {
        return startOfPreparationOfStadium;
    }

    public void setStartOfPreparationOfStadium(LocalDate startOfPreparationOfStadium) {
        this.startOfPreparationOfStadium = startOfPreparationOfStadium;
    }

    public LocalDate getEndOfDismantleOfStadium() {
        return endOfDismantleOfStadium;
    }

    public void setEndOfDismantleOfStadium(LocalDate endOfDismantleOfStadium) {
        this.endOfDismantleOfStadium = endOfDismantleOfStadium;
    }

    public Manager getEventManager() {
        return eventManager;
    }

    public void setEventManager(Manager eventManager) {
        this.eventManager = eventManager;
    }

    public void parseAndSetAllDates(String date, String startOfPreparation, String endOfDismantle) {
        setDateOfEvent(ZonedDateTime.of(LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")), ZoneId.of("Europe/Moscow")));
        setStartOfPreparationOfStadium(LocalDate.parse(startOfPreparation, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        setEndOfDismantleOfStadium(LocalDate.parse(endOfDismantle, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}