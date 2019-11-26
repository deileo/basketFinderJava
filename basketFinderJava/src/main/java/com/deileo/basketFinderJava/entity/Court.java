package com.deileo.basketFinderJava.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="courts")
@SQLDelete(sql = "UPDATE courts SET deleted_at = NOW() WHERE id = ?")
@Where(clause="deleted_at IS NULL")
public class Court extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String location;

    @Column()
    private String description;

    @Column(scale = 6, nullable = false)
    private Double lat;

    @Column(scale = 6, nullable = false)
    private Double lng;

    @Column(nullable = false)
    private Boolean isEnabled = false;

    @Column(nullable = false)
    private Boolean isNew = false;

    @Enumerated(EnumType.STRING)
    private CourtType type;

    @OneToMany(mappedBy = "court", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JsonBackReference
    private List<Event> events;

    @OneToMany(mappedBy = "court", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Comment> comments;

    public Court() {
        events = new ArrayList<>();
        comments = new ArrayList<>();
    }

    public Court(CourtType type) {
        events = new ArrayList<>();
        this.type = type;
    }

    private Court(Builder builder) {
        events = new ArrayList<>();
        comments = new ArrayList<>();

        name = builder.name;
        address = builder.address;
        location = builder.location;
        description = builder.description;
        lat = builder.lat;
        lng = builder.lng;
        isEnabled = builder.isEnabled;
        isNew = builder.isNew;
        type = builder.type;
    }

    public static class Builder {
        private String name;

        private String address;

        private String location;

        private String description;

        private Double lat;

        private Double lng;

        private Boolean isEnabled = false;

        private Boolean isNew = false;

        private final CourtType type;

        public Builder(CourtType type) {
            this.type = type;
        }

        public Builder setName(String name) {
            this.name = name;

            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;

            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;

            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;

            return this;
        }

        public Builder setLat(Double lat) {
            this.lat = lat;

            return this;
        }

        public Builder setLng(Double lng) {
            this.lng = lng;

            return this;
        }

        public Builder enable() {
            isEnabled = true;

            return this;
        }

        public Builder disable() {
            isEnabled = false;

            return this;
        }

        public Builder setNew(Boolean isNew) {
            this.isNew = isNew;

            return this;
        }

        public Court build() {
            return new Court(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public CourtType getType() {
        return type;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        if (!events.contains(event)) {
            events.add(event);
            event.setCourt(this);
        }
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        if (!comments.contains(comment)) {
            comments.add(comment);
            comment.setCourt(this);
        }
    }
}
