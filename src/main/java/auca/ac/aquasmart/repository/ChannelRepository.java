package auca.ac.aquasmart.repository;

import auca.ac.aquasmart.model.Channel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ChannelRepository extends JpaRepository<Channel, UUID> {

    @Query("SELECT c FROM Channel c " +
           "WHERE NOT EXISTS (" +
           "SELECT iv FROM IrrigationValve iv WHERE iv.channel = c" +
           ") ORDER BY c.channelNumber")
    List<Channel> findFreeChannels();
}