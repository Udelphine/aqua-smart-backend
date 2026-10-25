package auca.ac.aquasmart.controller;

import auca.ac.aquasmart.model.Channel;
import auca.ac.aquasmart.service.ChannelService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/channels")
public class ChannelController {

    private final ChannelService channelService;

    public ChannelController(
            ChannelService channelService) {

        this.channelService =
                channelService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(
            @RequestBody Channel channel) {

        String message =
                channelService.save(channel);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(message);
    }

    @GetMapping("/all")
    public List<Channel> getAll() {
        return channelService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
            @PathVariable UUID id) {

        Optional<Channel> channel =
                channelService.getById(id);

        if (channel.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The channel with that id does not exist");
        }

        return ResponseEntity.ok(
                channel.get()
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable UUID id,
            @RequestBody Channel channel) {

        String message =
                channelService.update(
                        id,
                        channel
                );

        if (message.equals(
                "The channel with that id does not exist")) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(message);
        }

        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(
            @PathVariable UUID id) {

        String message =
                channelService.delete(id);

        if (message.equals(
                "The channel with that id does not exist")) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(message);
        }

        return ResponseEntity.ok(message);
    }
}