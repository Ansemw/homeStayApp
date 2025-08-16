package com.eazybytes.homeStayApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "trek_details")

public class TrekDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @ManyToOne
    @JoinColumn(name = "trek_id", nullable = false)
    private Trek trek;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    private int participants;

    public TrekDetails(Trek trek, Booking booking, int participants) {
        this.trek = trek;
        this.booking = booking;
        this.participants = participants;
    }

}
