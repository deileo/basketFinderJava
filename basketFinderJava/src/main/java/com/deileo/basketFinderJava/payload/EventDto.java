package com.deileo.basketFinderJava.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventDto {

    private Integer id;

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private Integer neededPlayers = 0;

    private String description;

    @PositiveOrZero
    private Double price = 0.0;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull
    private String startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private String endTime;

    @JoinColumn(nullable = false)
    @NotNull
    private CourtDto court;

    @JoinColumn(nullable = false)
    private BaseUserDto createdBy;

    private Integer joinedPlayers = 0;

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

    public Integer getNeededPlayers() {
        return neededPlayers;
    }

    public void setNeededPlayers(Integer neededPlayers) {
        this.neededPlayers = neededPlayers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CourtDto getCourt() {
        return court;
    }

    public void setCourt(CourtDto court) {
        this.court = court;
    }

    public BaseUserDto getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BaseUserDto createdBy) {
        this.createdBy = createdBy;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getJoinedPlayers() {
        return joinedPlayers;
    }

    public void setJoinedPlayers(Integer joinedPlayers) {
        this.joinedPlayers = joinedPlayers;
    }

    public LocalDateTime convertStartTimeToDateTimeObject() throws ParseException {
        return LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public LocalDateTime convertEndTimeToDateTimeObject() throws ParseException {
        return LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @JsonProperty("court")
    private void unpackNested(Integer court) {
        this.court = new CourtDto();
        this.court.setId(court);
    }
}
