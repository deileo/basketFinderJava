package com.deileo.basketFinderJava.payload;

import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;

public class UserDto extends BaseUserDto {

    @JoinColumn
    private List<EventDto> joinedEvents = new ArrayList<>();

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
