package auca.ac.aquasmart.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "farm_zone")
public class FarmZone {

    @Id
    @GeneratedValue
    private UUID id;

    private String zoneName;

    private String cropType;

    @Column(unique = true)
    private String zoneCode;

    private double optimalMoisture;

    public FarmZone() {
    }

    public FarmZone(
            String zoneName,
            String cropType,
            String zoneCode,
            double optimalMoisture) {

        this.zoneName = zoneName;
        this.cropType = cropType;
        this.zoneCode = zoneCode;
        this.optimalMoisture = optimalMoisture;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public double getOptimalMoisture() {
        return optimalMoisture;
    }

    public void setOptimalMoisture(double optimalMoisture) {
        this.optimalMoisture = optimalMoisture;
    }
}