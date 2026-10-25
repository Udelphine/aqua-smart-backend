package auca.ac.aquasmart.service;

import auca.ac.aquasmart.model.Channel;
import auca.ac.aquasmart.repository.ChannelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChannelService {

    private final ChannelRepository channelRepository;

    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    public String save(Channel channel) {
        channel.setId(null);
        channelRepository.save(channel);
        return "Channel saved successfully";
    }

    public List<Channel> getAll() {
        return channelRepository.findAll();
    }

    public Optional<Channel> getById(UUID id) {
        return channelRepository.findById(id);
    }

    public String update(UUID id, Channel details) {
        Optional<Channel> channelData =
                channelRepository.findById(id);

        if (channelData.isEmpty()) {
            return "The channel with that id does not exist";
        }

        Channel channel =
                channelData.get();

        channel.setChannelNumber(
                details.getChannelNumber()
        );

        channel.setName(
                details.getName()
        );

        channelRepository.save(channel);

        return "Channel updated successfully";
    }

    public String delete(UUID id) {
        Optional<Channel> channelData =
                channelRepository.findById(id);

        if (channelData.isEmpty()) {
            return "The channel with that id does not exist";
        }

        channelRepository.delete(
                channelData.get()
        );

        return "Channel deleted successfully";
    }
}