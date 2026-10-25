package auca.ac.aquasmart.controller;

import auca.ac.aquasmart.model.SoilReading;
import auca.ac.aquasmart.service.SoilReadingService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/soil-readings")
public class SoilReadingController {

    private final SoilReadingService soilReadingService;

    public SoilReadingController(
            SoilReadingService soilReadingService) {

        this.soilReadingService =
                soilReadingService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(
            @RequestBody SoilReading soilReading,
            @RequestParam UUID irrigationRunId) {

        String message =
                soilReadingService.save(
                        soilReading,
                        irrigationRunId
                );

        if (message.equals(
                "The irrigation run with that id does not exist")) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(message);
        }

        if (message.equals(
                "Soil readings are only for FINISHED irrigation runs")) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(message);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(message);
    }

    @GetMapping("/farm-zone/{zoneCode}")
    public List<SoilReading> findByFarmZoneCode(
            @PathVariable String zoneCode) {

        return soilReadingService
                .findByFarmZoneCode(
                        zoneCode
                );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(
            @PathVariable UUID id) {

        String message =
                soilReadingService.delete(
                        id
                );

        if (message.equals(
                "The soil reading with that id does not exist")) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(message);
        }

        return ResponseEntity.ok(
                message
        );
    }
}