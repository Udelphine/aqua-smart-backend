package auca.ac.aquasmart.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "irrigation_run")
public class IrrigationRun {

    @Id
    @GeneratedValue
    private UUID id;

    private LocalDate runDate;

    @Enumerated(EnumType.STRING)
    private RunStatus status;

    private double waterVolume;

    private double moistureBefore;

    @ManyToOne
    @JoinColumn(name = "farm_zone_id")
    private FarmZone farmZone;

    @ManyToOne
    @JoinColumn(name = "irrigation_valve_id")
    private IrrigationValve irrigationValve;

    public IrrigationRun() {
    }

    public IrrigationRun(
            LocalDate runDate,
            RunStatus status,
            double waterVolume,
            double moistureBefore,
            FarmZone farmZone,
            IrrigationValve irrigationValve) {

        this.runDate = runDate;
        this.status = status;
        this.waterVolume = waterVolume;
        this.moistureBefore = moistureBefore;
        this.farmZone = farmZone;
        this.irrigationValve = irrigationValve;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getRunDate() {
        return runDate;
    }

    public void setRunDate(LocalDate runDate) {
        this.runDate = runDate;
    }

    public RunStatus getStatus() {
        return status;
    }

    public void setStatus(RunStatus status) {
        this.status = status;
    }

    public double getWaterVolume() {
        return waterVolume;
    }

    public void setWaterVolume(double waterVolume) {
        this.waterVolume = waterVolume;
    }

    public double getMoistureBefore() {
        return moistureBefore;
    }

    public void setMoistureBefore(double moistureBefore) {
        this.moistureBefore = moistureBefore;
    }

    public FarmZone getFarmZone() {
        return farmZone;
    }

    public void setFarmZone(FarmZone farmZone) {
        this.farmZone = farmZone;
    }

    public IrrigationValve getIrrigationValve() {
        return irrigationValve;
    }

    public void setIrrigationValve(IrrigationValve irrigationValve) {
        this.irrigationValve = irrigationValve;
    }
}