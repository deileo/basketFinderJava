package com.deileo.basketFinderJava.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;

public class CommentDto {

    private Integer id;

    @NotNull
    private String comment;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private String createdAt;

    @JoinColumn(nullable = false)
    private BaseUserDto createdBy;

    @JoinColumn(nullable = false)
    private EventDto event;

    @JoinColumn(nullable = false)
    private CourtDto court;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public BaseUserDto getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BaseUserDto createdBy) {
        this.createdBy = createdBy;
    }

    public EventDto getEvent() {
        return event;
    }

    public void setEvent(EventDto event) {
        this.event = event;
    }

    public CourtDto getCourt() {
        return court;
    }

    public void setCourt(CourtDto court) {
        this.court = court;
    }

    @JsonProperty("event")
    private void unpackNestedEvent(Integer event) {
        if (event != null) {
            this.event = new EventDto();
            this.event.setId(event);
        }
    }

    @JsonProperty("court")
    private void unpackNestedCourt(Integer court) {
        if (court != null) {
            this.court = new CourtDto();
            this.court.setId(court);
        }
    }
}
