package auca.ac.aquasmart.service;

import auca.ac.aquasmart.model.FarmZone;
import auca.ac.aquasmart.model.IrrigationRun;
import auca.ac.aquasmart.model.IrrigationValve;
import auca.ac.aquasmart.model.RunStatus;

import auca.ac.aquasmart.repository.FarmZoneRepository;
import auca.ac.aquasmart.repository.IrrigationRunRepository;
import auca.ac.aquasmart.repository.IrrigationValveRepository;
import auca.ac.aquasmart.repository.SoilReadingRepository;

import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IrrigationRunService {

    private final IrrigationRunRepository irrigationRunRepository;
    private final FarmZoneRepository farmZoneRepository;
    private final IrrigationValveRepository irrigationValveRepository;
    private final SoilReadingRepository soilReadingRepository;

    public IrrigationRunService(
        IrrigationRunRepository irrigationRunRepository,
        FarmZoneRepository farmZoneRepository,
        IrrigationValveRepository irrigationValveRepository,
        SoilReadingRepository soilReadingRepository) {

    this.irrigationRunRepository =
            irrigationRunRepository;

    this.farmZoneRepository =
            farmZoneRepository;

    this.irrigationValveRepository =
            irrigationValveRepository;

    this.soilReadingRepository =
            soilReadingRepository;
}

    public String save(IrrigationRun irrigationRun) {

        if (irrigationRun.getRunDate()
                .isBefore(LocalDate.now())) {

            return "Irrigation run date cannot be in the past";
        }

        UUID irrigationValveId =
                irrigationRun
                        .getIrrigationValve()
                        .getId();

        boolean alreadyExists =
                irrigationRunRepository
                        .existsByIrrigationValveIdAndRunDateAndStatusNot(
                                irrigationValveId,
                                irrigationRun.getRunDate(),
                                RunStatus.ABORTED
                        );

        if (alreadyExists) {
            return "Valve already has an irrigation run on that date";
        }

        irrigationRun.setId(null);

        if (irrigationRun.getFarmZone() != null) {

            Optional<FarmZone> farmZoneData =
                    farmZoneRepository.findById(
                            irrigationRun
                                    .getFarmZone()
                                    .getId()
                    );

            if (farmZoneData.isPresent()) {
                irrigationRun.setFarmZone(
                        farmZoneData.get()
                );
            }
        }

        if (irrigationRun.getIrrigationValve() != null) {

            Optional<IrrigationValve> irrigationValveData =
                    irrigationValveRepository.findById(
                            irrigationValveId
                    );

            if (irrigationValveData.isPresent()) {
                irrigationRun.setIrrigationValve(
                        irrigationValveData.get()
                );
            }
        }

        irrigationRun.setStatus(
                RunStatus.QUEUED
        );

        irrigationRunRepository.save(
                irrigationRun
        );

        return "Irrigation run saved successfully";
    }

    public List<IrrigationRun> getAll() {

        return irrigationRunRepository.findAll();
    }

    public Optional<IrrigationRun> getById(
            UUID id) {

        return irrigationRunRepository.findById(
                id
        );
    }

    public String update(
            UUID id,
            IrrigationRun details) {

        Optional<IrrigationRun> irrigationRunData =
                irrigationRunRepository.findById(id);

        if (irrigationRunData.isEmpty()) {
            return "The irrigation run with that id does not exist";
        }

        IrrigationRun irrigationRun =
                irrigationRunData.get();

        irrigationRun.setRunDate(
                details.getRunDate()
        );

        irrigationRun.setWaterVolume(
                details.getWaterVolume()
        );

        irrigationRun.setMoistureBefore(
                details.getMoistureBefore()
        );

        if (details.getFarmZone() != null) {

            Optional<FarmZone> farmZoneData =
                    farmZoneRepository.findById(
                            details.getFarmZone().getId()
                    );

            if (farmZoneData.isPresent()) {
                irrigationRun.setFarmZone(
                        farmZoneData.get()
                );
            }
        }

        if (details.getIrrigationValve() != null) {

            Optional<IrrigationValve> irrigationValveData =
                    irrigationValveRepository.findById(
                            details
                                    .getIrrigationValve()
                                    .getId()
                    );

            if (irrigationValveData.isPresent()) {
                irrigationRun.setIrrigationValve(
                        irrigationValveData.get()
                );
            }
        }

        irrigationRunRepository.save(
                irrigationRun
        );

        return "Irrigation run updated successfully";
    }

    public String changeStatus(
            UUID id,
            RunStatus newStatus) {

        Optional<IrrigationRun> irrigationRunData =
                irrigationRunRepository.findById(id);

        if (irrigationRunData.isEmpty()) {
            return "The irrigation run with that id does not exist";
        }

        IrrigationRun irrigationRun =
                irrigationRunData.get();

        RunStatus currentStatus =
                irrigationRun.getStatus();

        boolean allowed = false;

        if (currentStatus == RunStatus.QUEUED
                && newStatus == RunStatus.RUNNING) {

            allowed = true;
        }

        if (currentStatus == RunStatus.QUEUED
                && newStatus == RunStatus.ABORTED) {

            allowed = true;
        }

        if (currentStatus == RunStatus.RUNNING
                && newStatus == RunStatus.FINISHED) {

            allowed = true;
        }

        if (currentStatus == RunStatus.RUNNING
                && newStatus == RunStatus.ABORTED) {

            allowed = true;
        }

        if (!allowed) {
            return "Cannot change status from "
                    + currentStatus
                    + " to "
                    + newStatus;
        }

        irrigationRun.setStatus(
                newStatus
        );

        irrigationRunRepository.save(
                irrigationRun
        );

        return "Irrigation run status updated successfully";
    }

    public String delete(UUID id) {

    Optional<IrrigationRun> irrigationRunData =
            irrigationRunRepository.findById(id);

    if (irrigationRunData.isEmpty()) {
        return "The irrigation run with that id does not exist";
    }

    IrrigationRun irrigationRun =
            irrigationRunData.get();

    boolean hasSoilReadings =
            soilReadingRepository
                    .existsByIrrigationRun(
                            irrigationRun
                    );

    if (hasSoilReadings) {
        return "That irrigation run has soil readings, delete them first";
    }

    irrigationRunRepository.delete(
            irrigationRun
    );

    return "Irrigation run deleted successfully";
}

    public List<IrrigationRun> findByStatus(
            RunStatus status) {

        return irrigationRunRepository
                .findByStatusOrderByRunDateAsc(
                        status
                );
    }

    public List<IrrigationRun> findBetween(
            LocalDate start,
            LocalDate end) {

        return irrigationRunRepository
                .findByRunDateBetweenOrderByRunDateAsc(
                        start,
                        end
                );
    }
    public List<Object[]> getStatsByStatus() {

    return irrigationRunRepository
            .countIrrigationRunsByStatus();
}
public String cancelDay(
        UUID irrigationValveId,
        LocalDate date) {

    int cancelled =
            irrigationRunRepository
                    .cancelRunsForDay(
                            irrigationValveId,
                            date
                    );

    return cancelled
            + " irrigation runs cancelled";
}
public Page<IrrigationRun> getPage(
        Pageable pageable) {

    return irrigationRunRepository.findAll(
            pageable
    );
}
}