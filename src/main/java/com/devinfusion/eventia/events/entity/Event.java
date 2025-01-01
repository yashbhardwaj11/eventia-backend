package com.devinfusion.eventia.events.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.devinfusion.eventia.users.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Index;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(
    name = "eventia_events",
    indexes = {
        @Index(name = "idx_event_date", columnList = "event_date"),
        @Index(name = "idx_organizer_id", columnList = "organizer_id")
    }
)
public class Event {
    @Id
    String eventId;
    String name;
    String description;
    LocalDateTime eventDate;
    String venue;
    double price;
    int totalSeats;
    int availableSeats;

    @ManyToOne
    @JoinColumn(name = "organizer_id", referencedColumnName = "uid")
    private User organizer;

    @CreationTimestamp
    LocalDateTime createdAt;
    
    @UpdateTimestamp
    LocalDateTime updatedAt;
}
