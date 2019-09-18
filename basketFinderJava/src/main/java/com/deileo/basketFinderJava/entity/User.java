package com.deileo.basketFinderJava.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    @JsonIgnore
    @Column
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column
    private AuthProvider provider;

    @Column
    private String providerId;

    @OneToMany(mappedBy = "createdBy")
    private List<Event> createdEvents;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "event_participants",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> joinedEvents;

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

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public List<Event> getCreatedEvents() {
        return createdEvents;
    }

    public void addCreatedEvent(Event event) {
        if (!createdEvents.contains(event)) {
            event.setCreatedBy(this);
            createdEvents.add(event);
        }
    }

    public void removeEvent(Event event) {
        createdEvents.remove(event);
    }

    public List<Event> getJoinedEvents() {
        return joinedEvents;
    }

    public void addJoinedEvent(Event event) {
        if (!this.joinedEvents.contains(event)) {
            this.joinedEvents.add(event);
        }
    }

    public void removeJoinedEvent(Event event) {
        this.joinedEvents.remove(event);
    }

    public void setJoinedEvents(List<Event> joinedEvents) {
        this.joinedEvents = joinedEvents;
    }
}