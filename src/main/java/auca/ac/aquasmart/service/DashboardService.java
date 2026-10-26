package auca.ac.aquasmart.service;

import auca.ac.aquasmart.model.RunStatus;

import auca.ac.aquasmart.repository.ChannelRepository;
import auca.ac.aquasmart.repository.FarmZoneRepository;
import auca.ac.aquasmart.repository.IrrigationRunRepository;
import auca.ac.aquasmart.repository.IrrigationValveRepository;
import auca.ac.aquasmart.repository.SoilReadingRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardService {

    private final FarmZoneRepository farmZoneRepository;
    private final IrrigationValveRepository irrigationValveRepository;
    private final ChannelRepository channelRepository;
    private final IrrigationRunRepository irrigationRunRepository;
    private final SoilReadingRepository soilReadingRepository;

    public DashboardService(
            FarmZoneRepository farmZoneRepository,
            IrrigationValveRepository irrigationValveRepository,
            ChannelRepository channelRepository,
            IrrigationRunRepository irrigationRunRepository,
            SoilReadingRepository soilReadingRepository) {

        this.farmZoneRepository =
                farmZoneRepository;

        this.irrigationValveRepository =
                irrigationValveRepository;

        this.channelRepository =
                channelRepository;

        this.irrigationRunRepository =
                irrigationRunRepository;

        this.soilReadingRepository =
                soilReadingRepository;
    }

    public Map<String, Long> getSummary() {

        Map<String, Long> summary =
                new LinkedHashMap<>();

        summary.put(
                "farmZones",
                farmZoneRepository.count()
        );

        summary.put(
                "valves",
                irrigationValveRepository.count()
        );

        summary.put(
                "freeChannels",
                (long) channelRepository
                        .findFreeChannels()
                        .size()
        );

        summary.put(
                "runsToday",
                irrigationRunRepository
                        .countByRunDate(
                                LocalDate.now()
                        )
        );

        summary.put(
                "queued",
                irrigationRunRepository
                        .countByStatus(
                                RunStatus.QUEUED
                        )
        );

        summary.put(
                "soilReadings",
                soilReadingRepository.count()
        );

        return summary;
    }
}