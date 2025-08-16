package com.eazybytes.homeStayApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Booking extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "booking_room",
            joinColumns ={ @JoinColumn(name = "booking_id", referencedColumnName = "bookingId")},
            inverseJoinColumns = @JoinColumn(name = "room_no", referencedColumnName = "roomNo"))
    private Set<Room> rooms=new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "person_id", referencedColumnName = "personId", nullable = true)
    private Person person;

    @Column(name = "name")
    @NotBlank(message = "Name can't be blank")
    private String guestName;

    @Column(name = "mobile_number")
    @NotBlank(message = "Mobile number cannot be blank")
    private String guestMobileNum;

    private String status;

    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    private String guestEmail;

    @NotNull(message = "Start date cannot be blank")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @NotNull(message = "End date cannot be blank")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)  // This says: I am NOT the owner
    private Set<TrekDetails> bookingTreks;

}
