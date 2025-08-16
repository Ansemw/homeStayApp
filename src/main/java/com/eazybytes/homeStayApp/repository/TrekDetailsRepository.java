package com.eazybytes.homeStayApp.repository;

import com.eazybytes.homeStayApp.model.Booking;
import com.eazybytes.homeStayApp.model.Trek;
import com.eazybytes.homeStayApp.model.TrekDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TrekDetailsRepository extends JpaRepository<TrekDetails, Integer> {

    public List<TrekDetails> findByTrek(Trek trek);

    boolean existsByTrekAndBooking(Trek trek, Booking booking);

    @Transactional
    void deleteByTrek(Trek trek);

}
