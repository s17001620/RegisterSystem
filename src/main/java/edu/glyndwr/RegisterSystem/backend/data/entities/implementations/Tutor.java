package edu.glyndwr.RegisterSystem.backend.data.entities.implementations;

import edu.glyndwr.RegisterSystem.backend.data.entities.superclasses.Person;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tutor")
public class Tutor extends Person implements Serializable {

    private String expertise;

    public Tutor(){
        super();
    }
    
    public Tutor(Long id, String firstName, String lastName, String street, String zipCode, String city, String country) {
        super(id, firstName, lastName, street, zipCode, city, country);
    }

}
