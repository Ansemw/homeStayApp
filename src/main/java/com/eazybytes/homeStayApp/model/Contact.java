package com.eazybytes.homeStayApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


@Data // has features of the previous annotations in one and then some more
@Entity
@Table(name = "contact_msg")// Matches the table name to the class. Not required in case the table names and class
// names are the same(ignoring case and _symbol)


@NamedQueries({
        @NamedQuery(name = "Contact.findOpenMsgs", query = "SELECT c from Contact c WHERE c.status = 'Open'"),
        @NamedQuery(name = "Contact.updateMsgStatus", query = "UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
})
public class Contact extends BaseEntity{

 @Id
 @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
 @GenericGenerator(name = "native",strategy = "native")
 @JsonIgnore
 @Column(name = "Contact_id")// Matches the column to the variable. Not required in case the column names and variable
 // names are the same(ignoring case and _ symbol)
 private int contactId;

@NotBlank(message = "Name cannot be blank")
@Size(min = 3, message = "Name must have at least 3 characters")
//@JsonProperty("Person_Name")
 private String name;

 @NotBlank(message = "Mobile number cannot be blank")
 @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile number must have 10 digits between 0 and 9")
 private String mobileNum;

 @NotBlank(message = "E-mail cannot be blank")
 @Email(message = "Please provide valid email")
 private String email;

 @NotBlank(message = "Subject cannot be blank")
 @Size(min=5,message = "Subject's name must have 5 characters")
 private String subject;

 @NotBlank(message = "Message cannot be blank")
 @Size(min = 10, message = "Message must have at least 10 characters")
 private String message;

 private String status;

}
