package com.eazybytes.homeStayApp.repository;

import com.eazybytes.homeStayApp.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    List<Contact> findByStatus(String status);

    //@Query("SELECT c from Contact c where c.status = :status")
    @Query(value = "Select * from contact_msg c where c.status = :status", nativeQuery = true)
    Page<Contact> findByStatusWithQuery(@Param("status") String state, Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Contact c set c.status = ?1 where c.contactId = ?2")
    int updateStatusById(String status, int id);


    Page<Contact> findOpenMsgs(Pageable pageable);

    @Modifying
    @Transactional
    int updateMsgStatus(String status, int contactId);
}

