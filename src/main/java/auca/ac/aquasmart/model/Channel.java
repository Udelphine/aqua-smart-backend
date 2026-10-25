package auca.ac.aquasmart.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "channel")
public class Channel {

    @Id
    @GeneratedValue
    private UUID id;

    private int channelNumber;

    private String name;

    public Channel() {
    }

    public Channel(int channelNumber, String name) {
        this.channelNumber = channelNumber;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(int channelNumber) {
        this.channelNumber = channelNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}