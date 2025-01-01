package com.devinfusion.eventia.events.service;

import com.devinfusion.eventia.events.dto.EventDTO;
import java.util.List;

public interface EventService {
    EventDTO createEvent(EventDTO eventDTO, String organizerUid);
    EventDTO getEventById(String eventId);
    List<EventDTO> getAllEvents();
    EventDTO updateEvent(String eventId, EventDTO eventDTO,String userId);
    void deleteEvent(String eventId, String userId);
}
