package auca.ac.aquasmart.repository;

import auca.ac.aquasmart.model.WaterSource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface WaterSourceRepository extends JpaRepository<WaterSource, UUID> {

    @Query("SELECT ws FROM WaterSource ws " +
           "WHERE ws.irrigationValves IS EMPTY")
    List<WaterSource> findUnusedWaterSources();
}