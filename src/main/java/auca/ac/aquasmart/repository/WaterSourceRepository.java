package auca.ac.aquasmart.repository;

import auca.ac.aquasmart.model.WaterSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WaterSourceRepository extends JpaRepository<WaterSource, UUID> {
}
