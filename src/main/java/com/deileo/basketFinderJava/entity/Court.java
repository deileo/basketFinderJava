package com.deileo.basketFinderJava.entity;

import javax.persistence.*;

@Entity
@Table(name="courts")
public class Court {

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
    private Float lat;

    @Column(scale = 6, nullable = false)
    private Float lng;

    @Column(nullable = false)
    private Boolean isEnabled = false;

    @Column(nullable = false)
    private Boolean isNew = false;

    @Column()
    private Integer renovationYear;

    @Column()
    private String condition;

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

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
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

    public Integer getRenovationYear() {
        return renovationYear;
    }

    public void setRenovationYear(Integer renovationYear) {
        this.renovationYear = renovationYear;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
