/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.glyndwr.RegisterSystem.backend.data.repositories;

import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Course;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
    List<Course> findByDescriptionContaining(String description);
    Course findByCode(String code);
    List<Course> findByNameContaining(String name);
}
