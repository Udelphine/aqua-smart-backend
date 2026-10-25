package auca.ac.aquasmart.service;

import auca.ac.aquasmart.model.FarmZone;
import auca.ac.aquasmart.model.IrrigationRun;
import auca.ac.aquasmart.model.IrrigationValve;
import auca.ac.aquasmart.repository.FarmZoneRepository;
import auca.ac.aquasmart.repository.IrrigationRunRepository;
import auca.ac.aquasmart.repository.IrrigationValveRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IrrigationRunService {

    private final IrrigationRunRepository irrigationRunRepository;
    private final FarmZoneRepository farmZoneRepository;
    private final IrrigationValveRepository irrigationValveRepository;

    public IrrigationRunService(
            IrrigationRunRepository irrigationRunRepository,
            FarmZoneRepository farmZoneRepository,
            IrrigationValveRepository irrigationValveRepository) {

        this.irrigationRunRepository =
                irrigationRunRepository;

        this.farmZoneRepository =
                farmZoneRepository;

        this.irrigationValveRepository =
                irrigationValveRepository;
    }

    public String save(IrrigationRun irrigationRun) {

        irrigationRun.setId(null);

        if (irrigationRun.getFarmZone() != null) {

            UUID farmZoneId =
                    irrigationRun.getFarmZone().getId();

            Optional<FarmZone> farmZoneData =
                    farmZoneRepository.findById(farmZoneId);

            if (farmZoneData.isPresent()) {
                irrigationRun.setFarmZone(
                        farmZoneData.get()
                );
            }
        }

        if (irrigationRun.getIrrigationValve() != null) {

            UUID irrigationValveId =
                    irrigationRun.getIrrigationValve().getId();

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

        irrigationRunRepository.save(
                irrigationRun
        );

        return "Irrigation run saved successfully";
    }

    public List<IrrigationRun> getAll() {
        return irrigationRunRepository.findAll();
    }

    public Optional<IrrigationRun> getById(UUID id) {
        return irrigationRunRepository.findById(id);
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

        irrigationRun.setStatus(
                details.getStatus()
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
                            details.getIrrigationValve().getId()
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

    public String delete(UUID id) {

        Optional<IrrigationRun> irrigationRunData =
                irrigationRunRepository.findById(id);

        if (irrigationRunData.isEmpty()) {
            return "The irrigation run with that id does not exist";
        }

        irrigationRunRepository.delete(
                irrigationRunData.get()
        );

        return "Irrigation run deleted successfully";
    }
}