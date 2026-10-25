package auca.ac.aquasmart.repository;

import auca.ac.aquasmart.model.IrrigationRun;
import auca.ac.aquasmart.model.SoilReading;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SoilReadingRepository extends JpaRepository<SoilReading, UUID> {

    @Query("SELECT sr FROM SoilReading sr " +
           "WHERE sr.irrigationRun.farmZone.zoneCode = :zoneCode " +
           "ORDER BY sr.readingDate DESC")
    List<SoilReading> findByFarmZoneCode(
            @Param("zoneCode") String zoneCode
    );

    boolean existsByIrrigationRun(
            IrrigationRun irrigationRun
    );
}