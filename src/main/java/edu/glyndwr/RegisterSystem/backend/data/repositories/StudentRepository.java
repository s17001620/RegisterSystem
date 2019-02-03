
package edu.glyndwr.RegisterSystem.backend.data.repositories;

import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    List<Student> 	findByLastNameAndFirstName(String firstName, String lastName);
    List<Student> 	findByLastNameOrFirstName(String firstName, String lastName);
    List<Student> 	findByFirstNameStartingWith(String lastName);
    List<Student> 	findByLastNameStartingWith(String lastName);
}
