package auca.ac.aquasmart.controller;

import auca.ac.aquasmart.model.IrrigationValve;
import auca.ac.aquasmart.service.IrrigationValveService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/irrigation-valves")
public class IrrigationValveController {

    private final IrrigationValveService irrigationValveService;

    public IrrigationValveController(
            IrrigationValveService irrigationValveService) {

        this.irrigationValveService =
                irrigationValveService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(
            @RequestBody IrrigationValve irrigationValve) {

        String message =
                irrigationValveService.save(
                        irrigationValve
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(message);
    }

    @GetMapping("/all")
    public List<IrrigationValve> getAll() {

        return irrigationValveService.getAll();
    }

    @GetMapping("/by-water-source")
    public List<IrrigationValve> findByWaterSourceName(
            @RequestParam String name) {

        return irrigationValveService
                .findByWaterSourceName(name);
    }

    @PutMapping("/{irrigationValveId}/water-sources/{waterSourceId}")
    public ResponseEntity<String> addWaterSource(
            @PathVariable UUID irrigationValveId,
            @PathVariable UUID waterSourceId) {

        String message =
                irrigationValveService.addWaterSource(
                        irrigationValveId,
                        waterSourceId
                );

        if (message.equals(
                "The irrigation valve with that id does not exist")) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(message);
        }

        if (message.equals(
                "That water source does not exist")) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(message);
        }

        if (message.equals(
                "The irrigation valve already has that water source")) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(message);
        }

        return ResponseEntity.ok(
                message
        );
    }

    @PatchMapping("/{irrigationValveId}/channel")
    public ResponseEntity<String> changeChannel(
            @PathVariable UUID irrigationValveId,
            @RequestParam int channelNumber) {

        String message =
                irrigationValveService.changeChannel(
                        irrigationValveId,
                        channelNumber
                );

        if (message.equals(
                "The irrigation valve with that id does not exist")) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(message);
        }

        if (message.equals(
                "The channel with that channelNumber does not exist")) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(message);
        }

        if (message.equals(
                "That channel is taken by another irrigation valve")) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(message);
        }

        return ResponseEntity.ok(
                message
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @PathVariable UUID id) {

        Optional<IrrigationValve> irrigationValve =
                irrigationValveService.getById(id);

        if (irrigationValve.isEmpty()) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The irrigation valve with that id does not exist");
        }

        return ResponseEntity.ok(
                irrigationValve.get()
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable UUID id,
            @RequestBody IrrigationValve irrigationValve) {

        String message =
                irrigationValveService.update(
                        id,
                        irrigationValve
                );

        if (message.equals(
                "The irrigation valve with that id does not exist")) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(message);
        }

        return ResponseEntity.ok(
                message
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(
            @PathVariable UUID id) {

        String message =
                irrigationValveService.delete(
                        id
                );

        if (message.equals(
                "The irrigation valve with that id does not exist")) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(message);
        }

        return ResponseEntity.ok(
                message
        );
    }
}