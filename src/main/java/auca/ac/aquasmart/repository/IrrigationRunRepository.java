package auca.ac.aquasmart.repository;

import auca.ac.aquasmart.model.IrrigationRun;
import auca.ac.aquasmart.model.RunStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IrrigationRunRepository extends JpaRepository<IrrigationRun, UUID> {

    List<IrrigationRun> findByStatusOrderByRunDateAsc(RunStatus status);

    List<IrrigationRun> findByRunDateBetweenOrderByRunDateAsc(
            LocalDate start,
            LocalDate end
    );
}