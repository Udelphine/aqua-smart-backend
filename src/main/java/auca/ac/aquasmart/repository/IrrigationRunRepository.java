package auca.ac.aquasmart.repository;

import auca.ac.aquasmart.model.IrrigationRun;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IrrigationRunRepository extends JpaRepository<IrrigationRun, UUID> {
}