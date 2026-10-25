package auca.ac.aquasmart.service;

import auca.ac.aquasmart.model.Channel;
import auca.ac.aquasmart.model.IrrigationValve;
import auca.ac.aquasmart.repository.ChannelRepository;
import auca.ac.aquasmart.repository.IrrigationValveRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IrrigationValveService {

    private final IrrigationValveRepository irrigationValveRepository;
    private final ChannelRepository channelRepository;

    public IrrigationValveService(
            IrrigationValveRepository irrigationValveRepository,
            ChannelRepository channelRepository) {

        this.irrigationValveRepository =
                irrigationValveRepository;

        this.channelRepository =
                channelRepository;
    }

    public String save(IrrigationValve irrigationValve) {

        irrigationValve.setId(null);

        if (irrigationValve.getChannel() != null) {

            UUID channelId =
                    irrigationValve.getChannel().getId();

            Optional<Channel> channelData =
                    channelRepository.findById(channelId);

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

    public Optional<IrrigationValve> getById(UUID id) {
        return irrigationValveRepository.findById(id);
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

            UUID channelId =
                    details.getChannel().getId();

            Optional<Channel> channelData =
                    channelRepository.findById(channelId);

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
}