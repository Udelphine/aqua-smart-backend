package auca.ac.aquasmart.service;

import auca.ac.aquasmart.model.Channel;
import auca.ac.aquasmart.model.IrrigationValve;
import auca.ac.aquasmart.model.WaterSource;

import auca.ac.aquasmart.repository.ChannelRepository;
import auca.ac.aquasmart.repository.IrrigationValveRepository;
import auca.ac.aquasmart.repository.WaterSourceRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IrrigationValveService {

    private final IrrigationValveRepository irrigationValveRepository;
    private final ChannelRepository channelRepository;
    private final WaterSourceRepository waterSourceRepository;

    public IrrigationValveService(
            IrrigationValveRepository irrigationValveRepository,
            ChannelRepository channelRepository,
            WaterSourceRepository waterSourceRepository) {

        this.irrigationValveRepository =
                irrigationValveRepository;

        this.channelRepository =
                channelRepository;

        this.waterSourceRepository =
                waterSourceRepository;
    }

    public String save(
            IrrigationValve irrigationValve) {

        irrigationValve.setId(null);

        if (irrigationValve.getChannel() != null) {

            UUID channelId =
                    irrigationValve
                            .getChannel()
                            .getId();

            Optional<Channel> channelData =
                    channelRepository.findById(
                            channelId
                    );

            if (channelData.isPresent()) {
                irrigationValve.setChannel(
                        channelData.get()
                );
            }
        }

        irrigationValveRepository.save(
                irrigationValve
        );

        return "Irrigation valve saved successfully";
    }

    public List<IrrigationValve> getAll() {

        return irrigationValveRepository.findAll();
    }

    public Optional<IrrigationValve> getById(
            UUID id) {

        return irrigationValveRepository.findById(
                id
        );
    }

    public String update(
            UUID id,
            IrrigationValve details) {

        Optional<IrrigationValve> irrigationValveData =
                irrigationValveRepository.findById(id);

        if (irrigationValveData.isEmpty()) {
            return "The irrigation valve with that id does not exist";
        }

        IrrigationValve irrigationValve =
                irrigationValveData.get();

        irrigationValve.setLabel(
                details.getLabel()
        );

        irrigationValve.setModel(
                details.getModel()
        );

        irrigationValve.setFlowRate(
                details.getFlowRate()
        );

        if (details.getChannel() != null) {

            Optional<Channel> channelData =
                    channelRepository.findById(
                            details
                                    .getChannel()
                                    .getId()
                    );

            if (channelData.isPresent()) {
                irrigationValve.setChannel(
                        channelData.get()
                );
            }
        }

        irrigationValveRepository.save(
                irrigationValve
        );

        return "Irrigation valve updated successfully";
    }

    public String delete(UUID id) {

        Optional<IrrigationValve> irrigationValveData =
                irrigationValveRepository.findById(id);

        if (irrigationValveData.isEmpty()) {
            return "The irrigation valve with that id does not exist";
        }

        irrigationValveRepository.delete(
                irrigationValveData.get()
        );

        return "Irrigation valve deleted successfully";
    }

    public List<IrrigationValve> findByWaterSourceName(
            String name) {

        return irrigationValveRepository
                .findByWaterSourceName(name);
    }

    public String addWaterSource(
            UUID irrigationValveId,
            UUID waterSourceId) {

        Optional<IrrigationValve> irrigationValveData =
                irrigationValveRepository.findById(
                        irrigationValveId
                );

        if (irrigationValveData.isEmpty()) {
            return "The irrigation valve with that id does not exist";
        }

        Optional<WaterSource> waterSourceData =
                waterSourceRepository.findById(
                        waterSourceId
                );

        if (waterSourceData.isEmpty()) {
            return "That water source does not exist";
        }

        IrrigationValve irrigationValve =
                irrigationValveData.get();

        WaterSource waterSource =
                waterSourceData.get();

        for (WaterSource existingWaterSource :
                irrigationValve.getWaterSources()) {

            if (existingWaterSource
                    .getId()
                    .equals(waterSourceId)) {

                return "The irrigation valve already has that water source";
            }
        }

        irrigationValve
                .getWaterSources()
                .add(waterSource);

        irrigationValveRepository.save(
                irrigationValve
        );

        return "Water source added to the irrigation valve";
    }

    public String changeChannel(
            UUID irrigationValveId,
            int channelNumber) {

        Optional<IrrigationValve> irrigationValveData =
                irrigationValveRepository.findById(
                        irrigationValveId
                );

        if (irrigationValveData.isEmpty()) {
            return "The irrigation valve with that id does not exist";
        }

        Optional<Channel> channelData =
                channelRepository.findByChannelNumber(
                        channelNumber
                );

        if (channelData.isEmpty()) {
            return "The channel with that channelNumber does not exist";
        }

        Channel channel =
                channelData.get();

        IrrigationValve irrigationValve =
                irrigationValveData.get();

        if (irrigationValve.getChannel() != null
                && irrigationValve
                        .getChannel()
                        .getId()
                        .equals(channel.getId())) {

            return "Channel changed successfully";
        }

        boolean channelTaken =
                irrigationValveRepository
                        .existsByChannel(
                                channel
                        );

        if (channelTaken) {
            return "That channel is taken by another irrigation valve";
        }

        irrigationValve.setChannel(
                channel
        );

        irrigationValveRepository.save(
                irrigationValve
        );

        return "Channel changed successfully";
    }
}