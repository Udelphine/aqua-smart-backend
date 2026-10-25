package auca.ac.aquasmart.repository;

import auca.ac.aquasmart.model.IrrigationValve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IrrigationValveRepository extends JpaRepository<IrrigationValve, UUID> {
}