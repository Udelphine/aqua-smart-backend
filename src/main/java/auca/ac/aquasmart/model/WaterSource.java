package auca.ac.aquasmart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "water_source")
public class WaterSource {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "waterSources")
    @JsonIgnore
    private List<IrrigationValve> irrigationValves = new ArrayList<>();

    public WaterSource() {
    }

    public WaterSource(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IrrigationValve> getIrrigationValves() {
        return irrigationValves;
    }

    public void setIrrigationValves(List<IrrigationValve> irrigationValves) {
        this.irrigationValves = irrigationValves;
    }
}