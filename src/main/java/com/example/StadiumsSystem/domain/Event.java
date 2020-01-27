package com.example.StadiumsSystem.domain;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.Period;
import java.time.ZonedDateTime;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private EventType eventType;

    @Column(name = "EVENT_NAME")
    private String eventName;

    @Column(name = "EVENT_DATE")
    private ZonedDateTime dateOfEvent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stadium_id")
    private Stadium stadiumOfEvent;

    @Column(name = "EVENT_PREPARATION_PERIOD")
    private Period periodWhenStadiumIsBusy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id")
    private Manager eventManager;

    public Event() {
    }

    @Autowired
    public Event(EventType eventType, String eventName, ZonedDateTime dateOfEvent, Stadium stadiumOfEvent, Period periodWhenStadiumIsBusy, Manager eventManager) {
        this.eventType = eventType;
        this.eventName = eventName;
        this.dateOfEvent = dateOfEvent;
        this.stadiumOfEvent = stadiumOfEvent;
        this.periodWhenStadiumIsBusy = periodWhenStadiumIsBusy;
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

    public Period getPeriodWhenStadiumIsBusy() {
        return periodWhenStadiumIsBusy;
    }

    public void setPeriodWhenStadiumIsBusy(Period periodWhenStadiumIsBusy) {
        this.periodWhenStadiumIsBusy = periodWhenStadiumIsBusy;
    }

    public Manager getEventManager() {
        return eventManager;
    }

    public void setEventManager(Manager eventManager) {
        this.eventManager = eventManager;
    }
}