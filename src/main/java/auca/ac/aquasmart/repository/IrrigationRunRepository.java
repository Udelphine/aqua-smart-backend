package auca.ac.aquasmart.repository;

import auca.ac.aquasmart.model.IrrigationRun;
import auca.ac.aquasmart.model.RunStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IrrigationRunRepository extends JpaRepository<IrrigationRun, UUID> {

    List<IrrigationRun> findByStatusOrderByRunDateAsc(
            RunStatus status
    );

    List<IrrigationRun> findByRunDateBetweenOrderByRunDateAsc(
            LocalDate start,
            LocalDate end
    );

    boolean existsByIrrigationValveIdAndRunDateAndStatusNot(
            UUID irrigationValveId,
            LocalDate runDate,
            RunStatus status
    );

    @Query("SELECT ir.status, COUNT(ir) FROM IrrigationRun ir " +
           "GROUP BY ir.status")
    List<Object[]> countIrrigationRunsByStatus();

    @Modifying
    @Transactional
    @Query("UPDATE IrrigationRun ir " +
           "SET ir.status = auca.ac.aquasmart.model.RunStatus.ABORTED " +
           "WHERE ir.irrigationValve.id = :irrigationValveId " +
           "AND ir.runDate = :date " +
           "AND ir.status <> auca.ac.aquasmart.model.RunStatus.FINISHED")
    int cancelRunsForDay(
            UUID irrigationValveId,
            LocalDate date
    );
}