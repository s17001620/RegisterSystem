
package edu.glyndwr.RegisterSystem.backend.data.entities.implementations;

import edu.glyndwr.RegisterSystem.backend.data.entities.superclasses.GenericEntity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "course")
public class Course  extends GenericEntity implements Serializable{

    private String name;
    private String code;
    private String description;
    
    @OneToOne
    private Tutor tutor;
    
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<CourseDate> datesAttended = new HashSet<CourseDate>();
    
    @OneToMany(mappedBy="course")
    private Set<CourseMember> members = new HashSet<CourseMember>();

    public Course(){
        super();
    }
    
    public Course(Long id, String name, String code, String description) {
       super();
       this.id = id;
       this.name = name;
       this.code = code;
       this.description = description;
    }

       @Override
       public String toString(){
           return this.name+", "+this.code;
       }
}
