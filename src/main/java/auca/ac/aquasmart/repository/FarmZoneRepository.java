package auca.ac.aquasmart.repository;

import auca.ac.aquasmart.model.FarmZone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FarmZoneRepository extends JpaRepository<FarmZone, UUID> {

    List<FarmZone> findByCropTypeIgnoreCaseOrderByZoneNameAsc(String cropType);
}