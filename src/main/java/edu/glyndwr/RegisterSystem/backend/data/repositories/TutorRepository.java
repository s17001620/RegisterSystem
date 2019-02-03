/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.glyndwr.RegisterSystem.backend.data.repositories;

import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Tutor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long>{
    List<Tutor> 	findByLastNameAndFirstName(String firstName, String lastName);
    List<Tutor> 	findByLastNameOrFirstName(String firstName, String lastName);
    List<Tutor> 	findByFirstNameStartingWith(String lastName);
    List<Tutor> 	findByLastNameStartingWith(String lastName);
}
