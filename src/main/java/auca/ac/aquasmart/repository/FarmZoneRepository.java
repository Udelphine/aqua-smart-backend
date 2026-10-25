package auca.ac.aquasmart.repository;

import auca.ac.aquasmart.model.FarmZone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FarmZoneRepository extends JpaRepository<FarmZone, UUID> {

    List<FarmZone> findByCropTypeIgnoreCaseOrderByZoneNameAsc(
            String cropType
    );

    @Query("SELECT DISTINCT ir.farmZone FROM IrrigationRun ir " +
           "WHERE ir.irrigationValve.id = :irrigationValveId")
    List<FarmZone> findFarmZonesOfIrrigationValve(
            @Param("irrigationValveId") UUID irrigationValveId
    );
}