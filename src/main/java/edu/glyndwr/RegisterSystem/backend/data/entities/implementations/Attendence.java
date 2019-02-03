
package edu.glyndwr.RegisterSystem.backend.data.entities.implementations;

import edu.glyndwr.RegisterSystem.backend.data.entities.superclasses.GenericEntity;
import java.io.Serializable;
import javax.persistence.Entity;
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
    @JoinColumn(name = "courseDate")
    private CourseDate courseDate;
    @ManyToOne
    @JoinColumn(name = "courseMember")
    private CourseMember courseMember;

    public Attendence(){
        
    }
    
    public Attendence(long id, CourseDate courseDate, CourseMember courseMember) {
        this.id = id;
        this.courseDate = courseDate;
        this.courseMember = courseMember;
    }
    
    
    
}
