package com.deileo.basketFinderJava.entity;

import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;

@Entity
@Table(name="comments")
public class Comment extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn()
    private Event event;

    @ManyToOne
    @JoinColumn()
    private Court court;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}
