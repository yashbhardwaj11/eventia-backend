package com.devinfusion.eventia.events.service.impl;

import com.devinfusion.eventia.events.dto.EventDTO;
import com.devinfusion.eventia.events.entity.Event;
import com.devinfusion.eventia.events.exceptions.EventNotFound;
import com.devinfusion.eventia.events.exceptions.NotAuthorizedException;
import com.devinfusion.eventia.events.mapper.EventMapper;
import com.devinfusion.eventia.events.repository.EventRepository;
import com.devinfusion.eventia.users.entity.User;
import com.devinfusion.eventia.users.exceptions.UserNotFound;
import com.devinfusion.eventia.users.repository.UserRepository;
import com.devinfusion.eventia.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public EventDTO createEvent(EventDTO eventDTO, String organizerUid) {
        User organizer = userRepository.findById(organizerUid).orElseThrow(() -> new UserNotFound("User with id " + organizerUid + " not found"));
        eventDTO.setEventId(UUID.randomUUID().toString());
        Event event = EventMapper.toEntity(eventDTO, organizer);
        Event savedEvent = eventRepository.save(event);
        return EventMapper.toDTO(savedEvent);
    }

    @Override
    public EventDTO getEventById(String eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFound("Event with ID " + eventId + " not found"));
        return EventMapper.toDTO(event);
    }

    @Override
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                     .map(EventMapper::toDTO)
                     .collect(Collectors.toList());
    }

    @Override
    public EventDTO updateEvent(String eventId, EventDTO eventDTO, String userId) {
        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFound("Event with ID " + eventId + " not found"));

        // Check if the userId matches the organizerUid
        if (!existingEvent.getOrganizer().getUid().equals(userId)) {
            throw new NotAuthorizedException("You are not authorized to update this event");
        }

        if (eventDTO.getName() != null && !eventDTO.getName().isEmpty()) {
            existingEvent.setName(eventDTO.getName());
        }
        if (eventDTO.getDescription() != null && !eventDTO.getDescription().isEmpty()) {
            existingEvent.setDescription(eventDTO.getDescription());
        }
        if (eventDTO.getEventDate() != null) {
            existingEvent.setEventDate(eventDTO.getEventDate());
        }
        if (eventDTO.getVenue() != null && !eventDTO.getVenue().isEmpty()) {
            existingEvent.setVenue(eventDTO.getVenue());
        }
        if (eventDTO.getPrice() != null && eventDTO.getPrice() >= 0) {
            existingEvent.setPrice(eventDTO.getPrice());
        }

        if (eventDTO.getTotalSeats() != null && eventDTO.getTotalSeats() >= 0) {
            existingEvent.setTotalSeats(eventDTO.getTotalSeats());
        }
        if (eventDTO.getAvailableSeats() != null && eventDTO.getAvailableSeats() >= 0) {
            existingEvent.setAvailableSeats(eventDTO.getAvailableSeats());
        }
        Event updatedEvent = eventRepository.save(existingEvent);

        return EventMapper.toDTO(updatedEvent);
    }

    @Override
    public void deleteEvent(String eventId, String userId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new EventNotFound("Event not found"));

        // Check if the userId matches the organizerUid
        if (!event.getOrganizer().getUid().equals(userId)) {
            throw new NotAuthorizedException("You are not authorized to delete this event");
        }

        eventRepository.delete(event);
    }
}
