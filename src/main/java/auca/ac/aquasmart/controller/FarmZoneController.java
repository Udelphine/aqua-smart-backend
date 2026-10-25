package auca.ac.aquasmart.controller;

import auca.ac.aquasmart.model.FarmZone;
import auca.ac.aquasmart.service.FarmZoneService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/farm-zones")
public class FarmZoneController {

    private final FarmZoneService farmZoneService;

    public FarmZoneController(
            FarmZoneService farmZoneService) {

        this.farmZoneService =
                farmZoneService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(
            @RequestBody FarmZone farmZone) {

        String message =
                farmZoneService.save(farmZone);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(message);
    }

    @GetMapping("/all")
    public List<FarmZone> getAll() {
        return farmZoneService.getAll();
    }
    
    @GetMapping("/by-crop-type")
public List<FarmZone> findByCropType(
        @RequestParam String cropType) {

    return farmZoneService.findByCropType(
            cropType
    );
}

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @PathVariable UUID id) {

        Optional<FarmZone> farmZone =
                farmZoneService.getById(id);

        if (farmZone.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The farm zone with that id does not exist");
        }

        return ResponseEntity.ok(
                farmZone.get()
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable UUID id,
            @RequestBody FarmZone farmZone) {

        String message =
                farmZoneService.update(
                        id,
                        farmZone
                );

        if (message.equals(
                "The farm zone with that id does not exist")) {

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
                farmZoneService.delete(id);

        if (message.equals(
                "The farm zone with that id does not exist")) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(message);
        }

        return ResponseEntity.ok(message);
    }
}