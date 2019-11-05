package com.deileo.basketFinderJava.payload;

public class ParticipantDto extends BaseUserDto {

    private Integer eventId;

    private String eventName;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
