package com.eazybytes.homeStayApp.rest;

import com.eazybytes.homeStayApp.model.Contact;
import com.eazybytes.homeStayApp.model.Response;
import com.eazybytes.homeStayApp.repository.ContactRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/api/contact")
@CrossOrigin(origins = "*")
public class ContactRestController {

    @Autowired
    ContactRepository contactRepository;

    //@ResponseBody
    @GetMapping("/getMessagesByStatus")
    public List<Contact> getMessagesByStatus(@RequestParam(name = "status") String status){

        return contactRepository.findByStatus(status);
    }

    //@ResponseBody
    @GetMapping("/getAllMessagesByStatus")
    public List<Contact> getAllMessagesByStatus(@RequestBody Contact contact){

        if(contact!=null && contact.getStatus()!=null) return contactRepository.findByStatus(contact.getStatus());
        return new ArrayList<>();
    }

    @PostMapping("/saveMessage")
    public ResponseEntity<Response> saveMessage(@RequestHeader("invokedBy") String invokedBy
                                                       , @RequestBody @Valid Contact contact){

        log.info("Request invoked by :{}", invokedBy);
        contactRepository.save(contact);
        Response response=new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Messsage saved successfully");
        return  ResponseEntity.status(HttpStatus.CREATED).header("isMessageSaved","True").body(response);
    }

    @DeleteMapping("/deleteMessage")

    public ResponseEntity<Response> deleteMessage(RequestEntity<Contact> requestEntity){

        HttpHeaders headers=requestEntity.getHeaders();
        headers.forEach((key, value) -> {
            log.info(String.format(
                    "Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
        });
       /* headers.entrySet().stream()
                .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));
*/
        Contact contact=requestEntity.getBody();

        if(contact!=null&&contact.getContactId()>0)  contactRepository.deleteById(contact.getContactId());
        Response response=new Response();
        response.setStatusMsg("deleted message id "+contact.getContactId());
        response.setStatusCode("200");
        return ResponseEntity.status(HttpStatus.OK).header("isDeleted","True").body(response);
    }
    @PatchMapping("/closeMessage")
    public ResponseEntity<Response> closeMessage(@RequestBody Contact contactReq){

        Optional<Contact> contact = contactRepository.findById(contactReq.getContactId());
        Response response=new Response();
        if(contact.isEmpty()){
            response.setStatusCode("400");
            response.setStatusMsg("Invalid contact message id used");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        contact.get().setStatus("Closed");
        contactRepository.save(contact.get());
        response.setStatusCode("200");
        response.setStatusMsg("Contact message closed");
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
