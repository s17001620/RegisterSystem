package edu.glyndwr.RegisterSystem.backend.data.entities.implementations;

import edu.glyndwr.RegisterSystem.backend.data.entities.superclasses.Person;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
@Table(name = "student")
public class Student extends Person implements Serializable {

    String studentID;

    @OneToMany(mappedBy = "attendingStudent")
    Set<Attendence> datesAttended = new HashSet<Attendence>();

    @OneToMany(mappedBy = "student")
    Set<CourseMember> courses = new HashSet<CourseMember>();

    public Student() {
        super();
    }

    public Student(Long id, String firstName, String lastName, String street, String zipCode, String city, String country, String studentID) {
        super(id, firstName, lastName, street, zipCode, city, country);
        this.studentID = studentID;
    }
       public Student(Long id, String firstName, String lastName, String street, String zipCode, String city, String country) {
        super(id, firstName, lastName, street, zipCode, city, country);
    }
    
       @Override
       public String toString(){
           return this.lastName+", "+this.firstName+" "+this.studentID;
       }
    
}
