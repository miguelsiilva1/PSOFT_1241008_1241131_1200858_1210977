package com.marslps.AISafeFMS.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
public class RouteHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Route route;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime registered_at;

    protected RouteHistory() {}

    public RouteHistory(Route route, String description) {
        if (route == null) {
            throw new IllegalArgumentException("Route is required.");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("History description is required.");
        }
        this.route = route;
        this.description = description;
        this.registered_at = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof RouteHistory route_history)) {return false;}
        return Objects.equals(this.id, route_history.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
