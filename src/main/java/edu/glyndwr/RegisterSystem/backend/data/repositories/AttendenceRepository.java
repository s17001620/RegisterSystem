/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.glyndwr.RegisterSystem.backend.data.repositories;

import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Attendence;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseDate;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseMember;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Repository
public interface AttendenceRepository extends JpaRepository<Attendence, Long>{;
    List<Attendence> findByCourseDate(CourseDate courseDate);
    List<Attendence> findByCourseMember(CourseMember courseMember); 
}
