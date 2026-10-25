package auca.ac.aquasmart.controller;

import auca.ac.aquasmart.model.IrrigationRun;
import auca.ac.aquasmart.service.IrrigationRunService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/irrigation-runs")
public class IrrigationRunController {

    private final IrrigationRunService irrigationRunService;

    public IrrigationRunController(
            IrrigationRunService irrigationRunService) {

        this.irrigationRunService =
                irrigationRunService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(
            @RequestBody IrrigationRun irrigationRun) {

        String message =
                irrigationRunService.save(
                        irrigationRun
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(message);
    }

    @GetMapping("/all")
    public List<IrrigationRun> getAll() {
        return irrigationRunService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @PathVariable UUID id) {

        Optional<IrrigationRun> irrigationRun =
                irrigationRunService.getById(id);

        if (irrigationRun.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The irrigation run with that id does not exist");
        }

        return ResponseEntity.ok(
                irrigationRun.get()
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable UUID id,
            @RequestBody IrrigationRun irrigationRun) {

        String message =
                irrigationRunService.update(
                        id,
                        irrigationRun
                );

        if (message.equals(
                "The irrigation run with that id does not exist")) {

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
                irrigationRunService.delete(id);

        if (message.equals(
                "The irrigation run with that id does not exist")) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(message);
        }

        return ResponseEntity.ok(message);
    }
}