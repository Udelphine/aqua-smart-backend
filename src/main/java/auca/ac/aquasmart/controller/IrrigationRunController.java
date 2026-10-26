package auca.ac.aquasmart.controller;

import auca.ac.aquasmart.model.IrrigationRun;
import auca.ac.aquasmart.model.RunStatus;
import auca.ac.aquasmart.service.IrrigationRunService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
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

        if (message.equals(
                "Irrigation run date cannot be in the past")) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(message);
        }

        if (message.equals(
                "Valve already has an irrigation run on that date")) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(message);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(message);
    }

    @GetMapping("/all")
    public List<IrrigationRun> getAll() {

        return irrigationRunService.getAll();
    }

    @GetMapping("/by-status")
    public List<IrrigationRun> findByStatus(
            @RequestParam RunStatus status) {

        return irrigationRunService.findByStatus(
                status
        );
    }

    @GetMapping("/between")
    public List<IrrigationRun> findBetween(
            @RequestParam String start,
            @RequestParam String end) {

        LocalDate startDate =
                LocalDate.parse(start);

        LocalDate endDate =
                LocalDate.parse(end);

        return irrigationRunService.findBetween(
                startDate,
                endDate
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> changeStatus(
            @PathVariable UUID id,
            @RequestParam RunStatus status) {

        String message =
                irrigationRunService.changeStatus(
                        id,
                        status
                );

        if (message.equals(
                "The irrigation run with that id does not exist")) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(message);
        }

        if (message.startsWith(
                "Cannot change status")) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(message);
        }

        return ResponseEntity.ok(
                message
        );
    }
    @GetMapping("/stats/by-status")
public List<Object[]> getStatsByStatus() {

    return irrigationRunService
            .getStatsByStatus();
}
@PatchMapping("/cancel-day")
public ResponseEntity<String> cancelDay(
        @RequestParam UUID irrigationValveId,
        @RequestParam String date) {

    LocalDate runDate =
            LocalDate.parse(date);

    String message =
            irrigationRunService.cancelDay(
                    irrigationValveId,
                    runDate
            );

    return ResponseEntity.ok(
            message
    );
}
@GetMapping("/page")
public Page<IrrigationRun> getPage(
        @RequestParam int page,
        @RequestParam int size,
        @RequestParam String sort) {

    String[] sortParts =
            sort.split(",");

    String field =
            sortParts[0];

    String direction =
            sortParts[1];

    Sort sorting;

    if (direction.equalsIgnoreCase("desc")) {

        sorting =
                Sort.by(field).descending();

    } else {

        sorting =
                Sort.by(field).ascending();
    }

    Pageable pageable =
            PageRequest.of(
                    page,
                    size,
                    sorting
            );

    return irrigationRunService
            .getPage(
                    pageable
            );
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

        return ResponseEntity.ok(
                message
        );
    }

    @DeleteMapping("/delete/{id}")
public ResponseEntity<String> delete(
        @PathVariable UUID id) {

    String message =
            irrigationRunService.delete(
                    id
            );

    if (message.equals(
            "The irrigation run with that id does not exist")) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(message);
    }

    if (message.equals(
            "That irrigation run has soil readings, delete them first")) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(message);
    }

    return ResponseEntity.ok(
            message
    );
}
}