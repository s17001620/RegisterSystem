
package edu.glyndwr.RegisterSystem.backend.data.entities.superclasses;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Person extends GenericEntity{
   
    protected String firstName;
    protected String lastName;
    protected String street;
    protected String zipCode;
    protected String city;
    protected String country;

public Person(){
    super();
}

public Person(Long id,String firstName,String lastName,String street, String zipCode, String city,String country ){
   super();
   this.id = id;
   this.firstName = firstName;
   this.lastName = lastName;
   this.street = street;
   this.zipCode = zipCode;
   this.city = city;
   this.country = country;
}
}
