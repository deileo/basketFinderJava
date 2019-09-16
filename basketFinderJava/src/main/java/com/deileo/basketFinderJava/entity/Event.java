package com.deileo.basketFinderJava.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="events")
@SQLDelete(sql = "UPDATE courts SET deleted_at = NOW() WHERE id = ?")
@Where(clause="deleted_at IS NULL")
public class Event extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer neededPlayers = 0;

    @Column()
    private String description;

    @Column(scale = 2)
    private Double price = 0.0;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column()
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Court court;

    @ManyToMany(mappedBy = "joinedEvents", cascade = CascadeType.ALL)
    private List<User> participants;

    public Integer getId() {
        return id;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void addParticipant(User participant) {
        if (!this.participants.contains(participant)) {
            this.participants.add(participant);
            participant.addJoinedEvent(this);
        }
    }

    public void removeParticipant(User participant) {
        this.participants.remove(participant);
        participant.removeJoinedEvent(this);
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }
}
