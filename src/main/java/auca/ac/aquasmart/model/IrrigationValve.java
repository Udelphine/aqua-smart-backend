package auca.ac.aquasmart.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "irrigation_valve")
public class IrrigationValve {

    @Id
    @GeneratedValue
    private UUID id;

    private String label;

    private String model;

    private double flowRate;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @ManyToMany
    @JoinTable(
            name = "irrigation_valve_water_source",
            joinColumns = @JoinColumn(name = "irrigation_valve_id"),
            inverseJoinColumns = @JoinColumn(name = "water_source_id")
    )
    private List<WaterSource> waterSources = new ArrayList<>();

    public IrrigationValve() {
    }

    public IrrigationValve(
            String label,
            String model,
            double flowRate,
            Channel channel) {

        this.label = label;
        this.model = model;
        this.flowRate = flowRate;
        this.channel = channel;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getFlowRate() {
        return flowRate;
    }

    public void setFlowRate(double flowRate) {
        this.flowRate = flowRate;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public List<WaterSource> getWaterSources() {
        return waterSources;
    }

    public void setWaterSources(List<WaterSource> waterSources) {
        this.waterSources = waterSources;
    }
}