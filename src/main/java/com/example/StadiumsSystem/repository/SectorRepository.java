package com.example.StadiumsSystem.repository;

import com.example.StadiumsSystem.domain.Sector;
import com.example.StadiumsSystem.domain.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectorRepository extends JpaRepository<Sector, Integer> {
    List<Sector> findAllByStadium(Stadium stadium);
}
