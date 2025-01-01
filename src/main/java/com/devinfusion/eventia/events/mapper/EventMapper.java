package com.devinfusion.eventia.events.mapper;

import com.devinfusion.eventia.events.dto.EventDTO;
import com.devinfusion.eventia.events.entity.Event;
import com.devinfusion.eventia.users.entity.User;

public class EventMapper {

    public static EventDTO toDTO(Event event) {
        if (event == null) {
            return null;
        }
        return new EventDTO(
            event.getEventId(),
            event.getName(),
            event.getDescription(),
            event.getEventDate(),
            event.getVenue(),
            event.getPrice(),
            event.getTotalSeats(),
            event.getAvailableSeats(),
            event.getOrganizer() != null ? event.getOrganizer().getUid() : null
        );
    }

    public static Event toEntity(EventDTO eventDTO, User organizer) {
        if (eventDTO == null) {
            return null;
        }
        Event event = new Event();
        event.setEventId(eventDTO.getEventId());
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setEventDate(eventDTO.getEventDate());
        event.setVenue(eventDTO.getVenue());
        event.setPrice(eventDTO.getPrice());
        event.setTotalSeats(eventDTO.getTotalSeats());
        event.setAvailableSeats(eventDTO.getAvailableSeats());
        event.setOrganizer(organizer);
        return event;
    }
}
