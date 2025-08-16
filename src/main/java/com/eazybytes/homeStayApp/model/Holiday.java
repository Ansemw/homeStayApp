package com.eazybytes.homeStayApp.model;

import jakarta.persistence.*;
import lombok.Data;

//@AllArgsConstructor
//@Getter
@Data // has features of the previous annotations in one and then some more
@Entity
@Table(name = "holidays")
public class Holiday extends BaseEntity {

    @Id
    private  String day;

    private  String reason;

    @Enumerated(EnumType.STRING)// Shows that the variable is of type enum and needs to be stored as a string or a varchar in table.
    // conversion happens during runtime.
    private  Type type;

    public enum Type {
        FESTIVAL, MAINTENANCE;
    }

}
