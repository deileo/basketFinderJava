package com.deileo.basketFinderJava.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name="events")
@SQLDelete(sql = "UPDATE courts SET deleted_at = NOW() WHERE id = ?")
@Where(clause="deleted_at IS NULL")
public class Event extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false)
    @NotNull
    @Positive
    private Integer neededPlayers = 0;

    @Column()
    private String description;

    @Column(scale = 2)
    @PositiveOrZero
    private Double price = 0.0;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull
    @FutureOrPresent
    private LocalDateTime startTime;

    @Column()
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull
    @FutureOrPresent
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull
    private Court court;

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

    @JsonProperty("court")
    private void unpackNested(Integer court) {
        this.court = new Court();
        this.court.setId(court);
    }
}
