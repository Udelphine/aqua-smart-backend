package auca.ac.aquasmart.controller;

import auca.ac.aquasmart.model.WaterSource;
import auca.ac.aquasmart.service.WaterSourceService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/water-sources")
public class WaterSourceController {

    private final WaterSourceService waterSourceService;

    public WaterSourceController(
            WaterSourceService waterSourceService) {

        this.waterSourceService =
                waterSourceService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(
            @RequestBody WaterSource waterSource) {

        String message =
                waterSourceService.save(waterSource);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(message);
    }

    @GetMapping("/all")
    public List<WaterSource> getAll() {
        return waterSourceService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @PathVariable UUID id) {

        Optional<WaterSource> waterSource =
                waterSourceService.getById(id);

        if (waterSource.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The water source with that id does not exist");
        }

        return ResponseEntity.ok(
                waterSource.get()
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable UUID id,
            @RequestBody WaterSource waterSource) {

        String message =
                waterSourceService.update(
                        id,
                        waterSource
                );

        if (message.equals(
                "The water source with that id does not exist")) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(message);
        }

        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(
            @PathVariable UUID id) {

        String message =
                waterSourceService.delete(id);

        if (message.equals(
                "The water source with that id does not exist")) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(message);
        }

        return ResponseEntity.ok(message);
    }
}