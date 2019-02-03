package edu.glyndwr.RegisterSystem.backend.data.entities.implementations;

import edu.glyndwr.RegisterSystem.backend.data.entities.superclasses.GenericEntity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
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
@Table(name = "coursedate")
public class CourseDate  extends GenericEntity implements Serializable{

    private LocalDate courseDay;
    @ManyToOne
    @JoinColumn(name = "course")
    private Course course;

    public CourseDate(long id, Course course, LocalDate courseDay) {
        this.id = id;
        this.course = course;
        this.courseDay = courseDay;
    }

}

