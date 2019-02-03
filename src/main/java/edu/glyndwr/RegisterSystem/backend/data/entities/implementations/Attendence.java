
package edu.glyndwr.RegisterSystem.backend.data.entities.implementations;

import edu.glyndwr.RegisterSystem.backend.data.entities.superclasses.GenericEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "attendence")
public class Attendence extends GenericEntity implements Serializable{

    Boolean attended;
    @ManyToOne
    @JoinColumn(name = "course")
    private CourseDate courseDate;
    @ManyToOne
    @JoinColumn(name = "student")
    private Student attendingStudent;
    
    
    
}
