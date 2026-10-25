package auca.ac.aquasmart.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "soil_reading")
public class SoilReading {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String sensorName;

    private String observation;

    private int moisturePercent;

    private LocalDate readingDate;

    @ManyToOne
    @JoinColumn(name = "irrigation_run_id")
    private IrrigationRun irrigationRun;

    public SoilReading() {
    }

    public SoilReading(
            String sensorName,
            String observation,
            int moisturePercent,
            LocalDate readingDate,
            IrrigationRun irrigationRun) {

        this.sensorName = sensorName;
        this.observation = observation;
        this.moisturePercent = moisturePercent;
        this.readingDate = readingDate;
        this.irrigationRun = irrigationRun;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public int getMoisturePercent() {
        return moisturePercent;
    }

    public void setMoisturePercent(int moisturePercent) {
        this.moisturePercent = moisturePercent;
    }

    public LocalDate getReadingDate() {
        return readingDate;
    }

    public void setReadingDate(LocalDate readingDate) {
        this.readingDate = readingDate;
    }

    public IrrigationRun getIrrigationRun() {
        return irrigationRun;
    }

    public void setIrrigationRun(IrrigationRun irrigationRun) {
        this.irrigationRun = irrigationRun;
    }
}