package auca.ac.aquasmart.service;

import auca.ac.aquasmart.model.IrrigationRun;
import auca.ac.aquasmart.model.RunStatus;
import auca.ac.aquasmart.model.SoilReading;

import auca.ac.aquasmart.repository.IrrigationRunRepository;
import auca.ac.aquasmart.repository.SoilReadingRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SoilReadingService {

    private final SoilReadingRepository soilReadingRepository;
    private final IrrigationRunRepository irrigationRunRepository;

    public SoilReadingService(
            SoilReadingRepository soilReadingRepository,
            IrrigationRunRepository irrigationRunRepository) {

        this.soilReadingRepository =
                soilReadingRepository;

        this.irrigationRunRepository =
                irrigationRunRepository;
    }

    public String save(
            SoilReading soilReading,
            UUID irrigationRunId) {

        Optional<IrrigationRun> irrigationRunData =
                irrigationRunRepository.findById(
                        irrigationRunId
                );

        if (irrigationRunData.isEmpty()) {
            return "The irrigation run with that id does not exist";
        }

        IrrigationRun irrigationRun =
                irrigationRunData.get();

        if (irrigationRun.getStatus()
                != RunStatus.FINISHED) {

            return "Soil readings are only for FINISHED irrigation runs";
        }

        soilReading.setId(null);

        soilReading.setReadingDate(
                LocalDate.now()
        );

        soilReading.setIrrigationRun(
                irrigationRun
        );

        soilReadingRepository.save(
                soilReading
        );

        return "Soil reading saved successfully";
    }

    public List<SoilReading> findByFarmZoneCode(
            String zoneCode) {

        return soilReadingRepository
                .findByFarmZoneCode(
                        zoneCode
                );
    }

    public String delete(UUID id) {

        Optional<SoilReading> soilReadingData =
                soilReadingRepository.findById(
                        id
                );

        if (soilReadingData.isEmpty()) {
            return "The soil reading with that id does not exist";
        }

        soilReadingRepository.delete(
                soilReadingData.get()
        );

        return "Soil reading deleted successfully";
    }
}