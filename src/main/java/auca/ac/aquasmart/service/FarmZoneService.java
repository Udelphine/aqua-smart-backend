package auca.ac.aquasmart.service;

import auca.ac.aquasmart.model.FarmZone;
import auca.ac.aquasmart.repository.FarmZoneRepository;
import auca.ac.aquasmart.repository.IrrigationValveRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FarmZoneService {

    private final FarmZoneRepository farmZoneRepository;
    private final IrrigationValveRepository irrigationValveRepository;

    public FarmZoneService(
            FarmZoneRepository farmZoneRepository,
            IrrigationValveRepository irrigationValveRepository) {

        this.farmZoneRepository =
                farmZoneRepository;

        this.irrigationValveRepository =
                irrigationValveRepository;
    }

    public String save(FarmZone farmZone) {

        farmZone.setId(null);

        farmZoneRepository.save(
                farmZone
        );

        return "Farm zone saved successfully";
    }

    public List<FarmZone> getAll() {

        return farmZoneRepository.findAll();
    }

    public Optional<FarmZone> getById(UUID id) {

        return farmZoneRepository.findById(id);
    }

    public String update(
            UUID id,
            FarmZone details) {

        Optional<FarmZone> farmZoneData =
                farmZoneRepository.findById(id);

        if (farmZoneData.isEmpty()) {
            return "The farm zone with that id does not exist";
        }

        FarmZone farmZone =
                farmZoneData.get();

        farmZone.setZoneName(
                details.getZoneName()
        );

        farmZone.setCropType(
                details.getCropType()
        );

        farmZone.setZoneCode(
                details.getZoneCode()
        );

        farmZone.setOptimalMoisture(
                details.getOptimalMoisture()
        );

        farmZoneRepository.save(
                farmZone
        );

        return "Farm zone updated successfully";
    }

    public String delete(UUID id) {

        Optional<FarmZone> farmZoneData =
                farmZoneRepository.findById(id);

        if (farmZoneData.isEmpty()) {
            return "The farm zone with that id does not exist";
        }

        farmZoneRepository.delete(
                farmZoneData.get()
        );

        return "Farm zone deleted successfully";
    }

    public List<FarmZone> findByCropType(
            String cropType) {

        return farmZoneRepository
                .findByCropTypeIgnoreCaseOrderByZoneNameAsc(
                        cropType
                );
    }

    public boolean irrigationValveExists(
            UUID irrigationValveId) {

        return irrigationValveRepository
                .findById(irrigationValveId)
                .isPresent();
    }

    public List<FarmZone> findFarmZonesOfIrrigationValve(
            UUID irrigationValveId) {

        return farmZoneRepository
                .findFarmZonesOfIrrigationValve(
                        irrigationValveId
                );
    }
}