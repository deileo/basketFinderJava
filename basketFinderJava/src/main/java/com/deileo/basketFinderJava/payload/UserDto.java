package com.deileo.basketFinderJava.payload;

import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;

public class UserDto {

    private Integer id;

    private String name;

    private String email;

    private String imageUrl;

    @JoinColumn
    private List<EventDto> joinedEvents = new ArrayList<>();

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<EventDto> getJoinedEvents() {
        return joinedEvents;
    }

    public void addJoinedEvent(EventDto event) {
        if (!this.joinedEvents.contains(event)) {
            this.joinedEvents.add(event);
        }
    }

    public void setJoinedEvents(List<EventDto> joinedEvents) {
        this.joinedEvents = joinedEvents;
    }
}
