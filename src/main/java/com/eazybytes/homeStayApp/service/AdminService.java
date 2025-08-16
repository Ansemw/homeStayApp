package com.eazybytes.homeStayApp.service;

import com.eazybytes.homeStayApp.constants.ProjectConstants;
import com.eazybytes.homeStayApp.model.Booking;
import com.eazybytes.homeStayApp.model.Trek;
import com.eazybytes.homeStayApp.model.TrekDetails;
import com.eazybytes.homeStayApp.repository.BookingRepository;
import com.eazybytes.homeStayApp.repository.TrekDetailsRepository;
import com.eazybytes.homeStayApp.repository.TrekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    TrekRepository trekRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    TrekDetailsRepository trekDetailsRepository;
    public boolean activateTrek(int trekId){

        boolean isUpdated=false;
        Optional<Trek> trek = trekRepository.findById(trekId);
        if(trek.isPresent()){
            trek.get().setStatus(ProjectConstants.STATUS_ACTIVE);
            trekRepository.save(trek.get());
            isUpdated=true;
        }
        return isUpdated;
    }

    public boolean deactivateTrek(int trekId){

        boolean isUpdated=false;
        Optional<Trek> trekNew = trekRepository.findById(trekId);
        if(trekNew.isPresent()){
            Trek trek = trekNew.get();
            trekDetailsRepository.deleteByTrek(trek);
            trek.setStatus(ProjectConstants.STATUS_INACTIVE);
            trekRepository.save(trek);
            isUpdated=true;
        }
        return isUpdated;
    }

    public void registerTrekBooking(int trekId, int bookingId, int participants){
        Trek trek = trekRepository.findById(trekId).get();
        Booking booking = bookingRepository.findById(bookingId).get();

        if(trekDetailsRepository.existsByTrekAndBooking(trek, booking))
            throw new IllegalStateException("This booking is already registered for this Trek");

        TrekDetails trekDetails = new TrekDetails(trek, booking, participants);
        trekDetailsRepository.save(trekDetails);
    }
}
