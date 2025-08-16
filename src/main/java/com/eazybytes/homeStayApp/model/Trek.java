package com.eazybytes.homeStayApp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@Entity
@Table(name = "trek")
public class Trek extends BaseEntity {

    @Id
    private int trekId;
    private String trekName;
    private String trekDifficulty;
    private String trekDistance;
    private String trekImage;
    private String status;


   @OneToMany(mappedBy = "trek", cascade = CascadeType.ALL)
   private Set<TrekDetails> bookingTreks;


}
