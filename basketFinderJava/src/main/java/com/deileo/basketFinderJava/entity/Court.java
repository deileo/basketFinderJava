package com.deileo.basketFinderJava.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 7)
    private CourtType type;

    @OneToMany(mappedBy = "court", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JsonBackReference
    private List<Event> events;

    @OneToMany(mappedBy = "court", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Comment> comments;

    public Court() {
        events = new ArrayList<Event>();
        comments = new ArrayList<Comment>();
    }

    public Court(CourtType type) {
        events = new ArrayList<Event>();
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
