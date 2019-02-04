

package edu.glyndwr.RegisterSystem.frontend.model.wrapper;

import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Attendence;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseDate;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
public class CourseDateAttendenceWrapper {
    @Getter
    @Setter
    private CourseDate courseDate;
    @Getter
    @Setter
    private Attendence attendence;
}
