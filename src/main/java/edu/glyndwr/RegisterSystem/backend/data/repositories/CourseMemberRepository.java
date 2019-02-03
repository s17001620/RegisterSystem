/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.glyndwr.RegisterSystem.backend.data.repositories;

import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Course;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseMember;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Repository
public interface CourseMemberRepository extends JpaRepository<CourseMember, Long>{
    List <CourseMember> findByStudent(Student student);
    List <CourseMember> findByCourse(Course course); 
}
