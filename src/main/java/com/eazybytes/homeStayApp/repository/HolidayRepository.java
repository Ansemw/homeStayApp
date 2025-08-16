package com.eazybytes.homeStayApp.repository;

import com.eazybytes.homeStayApp.model.Holiday;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends CrudRepository<Holiday, String> {

/*CrudRepository<Holiday, String>
Holiday shows that this particular repository interface will deal with the table represented by the entity class Holiday.

String shows that while dealing with the concerned table, the datatype of the primary key(field with @Id annotation) is of
datatype String.
  */
}
