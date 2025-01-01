package com.devinfusion.eventia.events.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventDTO {
    private String eventId;
    private String name;
    private String description;
    private LocalDateTime eventDate;
    private String venue;
    private Double price;
    private Integer totalSeats;
    private Integer availableSeats;
    private String organizerUid;
}
