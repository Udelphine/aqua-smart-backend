package auca.ac.aquasmart.service;

import auca.ac.aquasmart.model.WaterSource;
import auca.ac.aquasmart.repository.WaterSourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WaterSourceService {

    private final WaterSourceRepository waterSourceRepository;

    public WaterSourceService(WaterSourceRepository waterSourceRepository) {
        this.waterSourceRepository = waterSourceRepository;
    }

    public String save(WaterSource waterSource) {
        waterSource.setId(null);
        waterSourceRepository.save(waterSource);
        return "Water source saved successfully";
    }

    public List<WaterSource> getAll() {
        return waterSourceRepository.findAll();
    }

    public Optional<WaterSource> getById(UUID id) {
        return waterSourceRepository.findById(id);
    }

    public String update(UUID id, WaterSource details) {
        Optional<WaterSource> waterSourceData =
                waterSourceRepository.findById(id);

        if (waterSourceData.isEmpty()) {
            return "The water source with that id does not exist";
        }

        WaterSource waterSource =
                waterSourceData.get();

        waterSource.setName(details.getName());

        waterSourceRepository.save(waterSource);

        return "Water source updated successfully";
    }

    public String delete(UUID id) {
        Optional<WaterSource> waterSourceData =
                waterSourceRepository.findById(id);

        if (waterSourceData.isEmpty()) {
            return "The water source with that id does not exist";
        }

        waterSourceRepository.delete(
                waterSourceData.get()
        );

        return "Water source deleted successfully";
    }
    public List<WaterSource> getUnusedWaterSources() {
    return waterSourceRepository.findUnusedWaterSources();
}
}