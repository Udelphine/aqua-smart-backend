package auca.ac.aquasmart.repository;

import auca.ac.aquasmart.model.FarmZone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FarmZoneRepository extends JpaRepository<FarmZone, UUID> {
}