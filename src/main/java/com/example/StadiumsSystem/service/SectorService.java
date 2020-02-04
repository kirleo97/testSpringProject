package com.example.StadiumsSystem.service;

import com.example.StadiumsSystem.domain.Sector;
import com.example.StadiumsSystem.domain.Stadium;
import com.example.StadiumsSystem.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorService {
    private final SectorRepository sectorRepository;

    @Autowired
    public SectorService(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    public Sector findById(Integer id) {
        return sectorRepository.getOne(id);
    }

    public List<Sector> findAll() {
        return sectorRepository.findAll();
    }

    public Sector saveSector(Sector sector) {
        return sectorRepository.save(sector);
    }

    public void deleteById(Integer id) { sectorRepository.deleteById(id); }

    public List<Sector> findSectorsByStadium(Stadium stadium) {
        return sectorRepository.findAllByStadium(stadium);
    }
}