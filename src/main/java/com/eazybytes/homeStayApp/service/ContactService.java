package com.eazybytes.homeStayApp.service;

import com.eazybytes.homeStayApp.config.TestSchoolProps;
import com.eazybytes.homeStayApp.model.Contact;
import com.eazybytes.homeStayApp.repository.ContactRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

@Slf4j
@Service
@Getter
@Setter
//@RequestScope
//@SessionScope
@ApplicationScope

public class ContactService {
@Autowired
private ContactRepository contactRepository;

@Autowired
TestSchoolProps testSchoolProps;

public ContactService(){

    System.out.println("ContactService bean initialised");
}
    public boolean saveMessageDetails(Contact contact){
        boolean isSaved=false;
        log.info(contact.toString());
        contact.setStatus("Open");
       /* contact.setCreatedBy("Anonymous");
        contact.setCreatedAt(LocalDateTime.now());
        int result=contactRepository.saveContactMessage(contact);*/
        Contact savedContact= contactRepository.save(contact);
        if(savedContact!=null && savedContact.getContactId()>0) isSaved= true;

        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus(){

    List<Contact> contactMessages= contactRepository.findByStatus("Open");
    return contactMessages;
  }

  public boolean updateMsgStatus(int contactId){

      boolean isUpdated=false;

     /* Optional<Contact> contact = contactRepository.findById(contactId);
      contact.ifPresent(contact1 -> {contact1.setStatus("Closed");
                                     /*contact1.setUpdatedBy(updatedBy);
                                     contact1.setUpdatedAt(LocalDateTime.now());});*/

      int rows= contactRepository.updateStatusById("Closed", contactId);
      //int rows= contactRepository.updateMsgStatus("Closed", contactId);
      if(rows>0) isUpdated= true;

      return isUpdated;
  }

 public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir){

    int pageSize = testSchoolProps.getPageSize();
    log.error("Page size right now = {}",pageSize);
    if(testSchoolProps.getContact()!=null&&testSchoolProps.getContact().get("pageSize")!=null)
        pageSize=Integer.parseInt(testSchoolProps.getContact().get("pageSize"));
    Sort sort=sortDir.equals("asc")? Sort.by(sortField).ascending(): Sort.by(sortField).descending();
    Pageable pageable= PageRequest.of(pageNum-1, pageSize, sort);
    Page<Contact> page=contactRepository.findByStatusWithQuery("Open",pageable);
     //Page<Contact> page=contactRepository.findOpenMsgs("Open",pageable);
     //Page<Contact> page=contactRepository.findOpenMsgs(pageable);
    return page;
 }

}
