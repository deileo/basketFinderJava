package com.deileo.basketFinderJava.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="courts")
public class Court {
    public static final String TYPE_PRIVATE = "private";
    public static final String TYPE_PUBLIC = "public";

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

    @Column(nullable = false, length = 7)
    private String type;
//
    @OneToMany(mappedBy = "court")
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
}
