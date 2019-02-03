
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
@Table(name = "courseMember")
public class CourseMember extends GenericEntity implements Serializable{
    @ManyToOne
    @JoinColumn(name = "student")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "course")
    private Course course;

    public CourseMember(long id, Course course, Student student) {
      this.id = id;
      this.course = course;
      this.student = student;
    }
    
}
