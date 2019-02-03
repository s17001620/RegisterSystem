
package edu.glyndwr.RegisterSystem.backend.data.services;

import edu.glyndwr.RegisterSystem.backend.data.repositories.AttendenceRepository;
import edu.glyndwr.RegisterSystem.backend.data.repositories.CourseDateRepository;
import edu.glyndwr.RegisterSystem.backend.data.repositories.CourseMemberRepository;
import edu.glyndwr.RegisterSystem.backend.data.repositories.CourseRepository;
import edu.glyndwr.RegisterSystem.backend.data.repositories.StudentRepository;
import edu.glyndwr.RegisterSystem.backend.data.repositories.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alexander Bruckbauer s17001620
 */
@Service
public class RegistryService {
@Autowired
private AttendenceRepository attendenceRepo;
        
@Autowired
private CourseDateRepository courseDateRepo;
        
@Autowired
private CourseMemberRepository courseMemberRepo;
        
@Autowired
private CourseRepository courseRepo;
       
@Autowired
private StudentRepository StudentRepo;

@Autowired
private TutorRepository Tutorrepo;

// #TODO implement Database interaction.


}
