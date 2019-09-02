package com.deileo.basketFinderJava.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="courts")
@Where(clause="deleted_at IS NULL")
public class Court {
    public static final String TYPE_PRIVATE = "private";
    public static final String TYPE_PUBLIC = "public";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false)
    @NotBlank
    private String address;

    @Column(nullable = false)
    @NotBlank
    private String location;

    @Column()
    private String description;

    @Column(scale = 6, nullable = false)
    @NotNull
    @Range(min = -90, max=90)
    private Double lat;

    @Column(scale = 6, nullable = false)
    @NotNull
    @Range(min = -180, max=180)
    private Double lng;

    @Column(nullable = false)
    private Boolean isEnabled = false;

    @Column(nullable = false)
    private Boolean isNew = false;

    @Column(nullable = false, length = 7)
    @NotBlank
    private String type;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column()
    private LocalDateTime updatedAt;

    @Column()
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "court", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JsonBackReference
    private List<Event> events;

    public Court() {
        this.events = new ArrayList<Event>();
    }

    public Court(String type) {
        this.events = new ArrayList<Event>();
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public String getType() {
        return type;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        if (!this.events.contains(event)) {
            event.setCourt(this);
            this.events.add(event);
        }
    }

    public void removeEvent(Event event) {
        this.events.remove(event);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    @PreRemove
    public void onPreRemove() {
        deletedAt = LocalDateTime.now();
    }

    @PrePersist
    public void onPrePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
