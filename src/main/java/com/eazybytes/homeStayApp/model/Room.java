package com.eazybytes.homeStayApp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Room {

    @Id
    private int roomNo;

    private String floor;

    private String desc;

    @ManyToMany(mappedBy = "rooms", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<Booking> bookings;
}
