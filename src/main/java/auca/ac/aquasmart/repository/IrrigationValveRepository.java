package auca.ac.aquasmart.repository;

import auca.ac.aquasmart.model.Channel;
import auca.ac.aquasmart.model.IrrigationValve;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface IrrigationValveRepository extends JpaRepository<IrrigationValve, UUID> {

    @Query("SELECT DISTINCT iv FROM IrrigationValve iv JOIN iv.waterSources ws " +
           "WHERE LOWER(ws.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<IrrigationValve> findByWaterSourceName(
            @Param("name") String name
    );

    boolean existsByChannel(
            Channel channel
    );
}