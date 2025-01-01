package com.devinfusion.eventia.events.controller;

import com.devinfusion.eventia.events.dto.EventDTO;
import com.devinfusion.eventia.dto.SuccessDTO;
import com.devinfusion.eventia.events.exceptions.EventNotFound;
import com.devinfusion.eventia.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events/")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<SuccessDTO<EventDTO>> createEvent(@RequestBody EventDTO eventDTO, @RequestParam(name = "organizerUid") String organizerUid) {
        EventDTO createdEvent = eventService.createEvent(eventDTO, organizerUid);
        SuccessDTO<EventDTO> successResponse = new SuccessDTO<>(HttpStatus.CREATED.value(), "Event created successfully", createdEvent);
        return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<SuccessDTO<EventDTO>> getEventById(@PathVariable String eventId) {
        EventDTO event = eventService.getEventById(eventId);
        if (event == null) {
            throw new EventNotFound("Event with ID " + eventId + " not found");
        }
        SuccessDTO<EventDTO> successResponse = new SuccessDTO<>(HttpStatus.OK.value(), "Event fetched successfully", event);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<SuccessDTO<List<EventDTO>>> getAllEvents() {
        List<EventDTO> events = eventService.getAllEvents();
        SuccessDTO<List<EventDTO>> successResponse = new SuccessDTO<>(HttpStatus.OK.value(), "Events fetched successfully", events);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<SuccessDTO<EventDTO>> updateEvent(@PathVariable String eventId, @RequestBody EventDTO eventDTO, @RequestParam(name = "userId") String userId) {
        EventDTO updatedEvent = eventService.updateEvent(eventId, eventDTO, userId);
        if (updatedEvent == null) {
            throw new EventNotFound("Event with ID " + eventId + " not found for update");
        }
        SuccessDTO<EventDTO> successResponse = new SuccessDTO<>(HttpStatus.OK.value(), "Event updated successfully", updatedEvent);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<SuccessDTO<Void>> deleteEvent(@PathVariable String eventId, @RequestParam(name = "userId") String userId) {
        eventService.deleteEvent(eventId, userId);
        SuccessDTO<Void> successResponse = new SuccessDTO<>(HttpStatus.NO_CONTENT.value(), "Event deleted successfully", null);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
}
