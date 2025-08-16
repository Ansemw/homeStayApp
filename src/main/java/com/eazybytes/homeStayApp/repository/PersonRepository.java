package com.eazybytes.homeStayApp.repository;

import com.eazybytes.homeStayApp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    public Person getByEmail(String email);
}
