package com.example.StadiumsSystem.domain;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "sectors")
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    @Column(name = "SECTOR_NAME")
    private String sectorName;

    @Column(name = "SECTOR_NUMBER_OF_SEATS")
    private int numberOfSeats;

    public Sector() {
    }

    @Autowired
    public Sector(Stadium stadium, String sectorName, int numberOfSeats) {
        this.stadium = stadium;
        this.sectorName = sectorName;
        this.numberOfSeats = numberOfSeats;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}