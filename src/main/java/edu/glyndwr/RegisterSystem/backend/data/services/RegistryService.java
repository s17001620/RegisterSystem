package edu.glyndwr.RegisterSystem.backend.data.services;

import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Attendence;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Course;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseDate;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.CourseMember;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Student;
import edu.glyndwr.RegisterSystem.backend.data.entities.implementations.Tutor;
import edu.glyndwr.RegisterSystem.backend.data.repositories.AttendenceRepository;
import edu.glyndwr.RegisterSystem.backend.data.repositories.CourseDateRepository;
import edu.glyndwr.RegisterSystem.backend.data.repositories.CourseMemberRepository;
import edu.glyndwr.RegisterSystem.backend.data.repositories.CourseRepository;
import edu.glyndwr.RegisterSystem.backend.data.repositories.StudentRepository;
import edu.glyndwr.RegisterSystem.backend.data.repositories.TutorRepository;
import java.util.List;
import java.util.Optional;
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
    private StudentRepository studentRepo;

    @Autowired
    private TutorRepository tutorrepo;

// #TODO implement Database interaction.
    public List<Student> findAllStudents() {
        return studentRepo.findAll();
    }

    public Student findStudentById(Long id) {
        Optional<Student> entity = studentRepo.findById(id);
        if (entity.isPresent()) {
            return entity.get();
        } else {
            return null;
        }
    }

    public void saveStudent(Student entity) {
        studentRepo.save(entity);
    }

    public void saveAllStudents(List<Student> entities) {
        studentRepo.saveAll(entities);
    }

    public void deleteStudent(Student entity) {
        studentRepo.delete(entity);
    }

    public void deleteAllStudents(List<Student> entities) {
        studentRepo.deleteAll(entities);
    }

    public Course findCourseById(Long id) {
        Optional<Course> entity = courseRepo.findById(id);
        if (entity.isPresent()) {
            return entity.get();
        } else {
            return null;
        }
    }

    public void saveCourse(Course entity) {
        courseRepo.save(entity);
    }

    public void saveAllCourses(List<Course> entities) {
        courseRepo.saveAll(entities);
    }

    public void deleteCourse(Course entity) {
        courseRepo.delete(entity);
    }

    public void deleteAllCourses(List<Course> entities) {
        courseRepo.deleteAll(entities);
    }

    public Attendence findAttendenceById(Long id) {
        Optional<Attendence> entity = attendenceRepo.findById(id);
        if (entity.isPresent()) {
            return entity.get();
        } else {
            return null;
        }
    }

    public void saveAttendence(Attendence entity) {
        attendenceRepo.save(entity);
    }

    public void saveAllAttendences(List<Attendence> entities) {
        attendenceRepo.saveAll(entities);
    }

    public void deleteAttendence(Attendence entity) {
        attendenceRepo.delete(entity);
    }

    public void deleteAllAttendences(List<Attendence> entities) {
        attendenceRepo.deleteAll(entities);
    }

    public CourseDate findCourseDateById(Long id) {
        Optional<CourseDate> entity = courseDateRepo.findById(id);
        if (entity.isPresent()) {
            return entity.get();
        } else {
            return null;
        }
    }

    public void saveCourseDate(CourseDate entity) {
        courseDateRepo.save(entity);
    }

    public void saveAllCourseDates(List<CourseDate> entities) {
        courseDateRepo.saveAll(entities);
    }

    public void deleteCourseDate(CourseDate entity) {
        courseDateRepo.delete(entity);
    }

    public void deleteAllCourseDates(List<CourseDate> entities) {
        courseDateRepo.deleteAll(entities);
    }

    public CourseMember findCourseMemberById(Long id) {
        Optional<CourseMember> entity = courseMemberRepo.findById(id);
        if (entity.isPresent()) {
            return entity.get();
        } else {
            return null;
        }
    }

    public void saveCourseMember(CourseMember entity) {
        courseMemberRepo.save(entity);
    }

    public void saveAllCourseMembers(List<CourseMember> entities) {
        courseMemberRepo.saveAll(entities);
    }

    public void deleteCourseMember(CourseMember entity) {
        courseMemberRepo.delete(entity);
    }

    public void deleteAllCourseMembers(List<CourseMember> entities) {
        courseMemberRepo.deleteAll(entities);
    }

    public Tutor findTutorById(Long id) {
        Optional<Tutor> entity = tutorrepo.findById(id);
        if (entity.isPresent()) {
            return entity.get();
        } else {
            return null;
        }
    }

    public void saveTutor(Tutor entity) {
        tutorrepo.save(entity);
    }

    public void saveAllTutors(List<Tutor> entities) {
        tutorrepo.saveAll(entities);
    }

    public void deleteTutor(Tutor entity) {
        tutorrepo.delete(entity);
    }

    public void deleteAllTutors(List<Tutor> entities) {
        tutorrepo.deleteAll(entities);
    }

    public List<Attendence> findAttendenceByCourseDate(CourseDate courseDate) {
        return attendenceRepo.findByCourseDate(courseDate);
    }

    public List<Attendence> findAttendenceByCourseMember(CourseMember courseMember) {
        return attendenceRepo.findByCourseMember(courseMember);
    }

    public <List> CourseDate findCourseDateByCourse(Course course) {
        return courseDateRepo.findByCourse(course);
    }

    public List<CourseMember> findCourseMemberByStudent(Student student) {
        return courseMemberRepo.findByStudent(student);
    }

    public List<CourseMember> findCourseMemberByCourse(Course course) {
        return courseMemberRepo.findByCourse(course);
    }

    public List<Course> findCourseByDescriptionContaining(String description) {
        return courseRepo.findByDescriptionContaining(description);
    }

    public Course findCourseByCode(String code) {
        return courseRepo.findByCode(code);
    }

    public List<Course> findCourseByNameContaining(String name) {
        return courseRepo.findByNameContaining(name);
    }

    public List<Student> findStudentByLastNameAndFirstName(String firstName, String lastName) {
        return studentRepo.findByLastNameAndFirstName(firstName, lastName);
    }

    public List<Student> findStudentByLastNameOrFirstName(String firstName, String lastName) {
        return studentRepo.findByLastNameOrFirstName(firstName, lastName);
    }

    public List<Student> findStudentByFirstNameStartingWith(String firstName) {
        return studentRepo.findByFirstNameStartingWith(firstName);
    }

    public List<Student> findStudentByLastNameStartingWith(String lastName) {
        return studentRepo.findByLastNameStartingWith(lastName);
    }

    public List<Tutor> findTutorByLastNameAndFirstName(String firstName, String lastName) {
        return tutorrepo.findByLastNameAndFirstName(firstName, lastName);
    }

    public List<Tutor> findTutorByLastNameOrFirstName(String firstName, String lastName) {
        return tutorrepo.findByLastNameOrFirstName(firstName, lastName);
    }

    public List<Tutor> findTutorByFirstNameStartingWith(String firstName) {
        return tutorrepo.findByFirstNameStartingWith(firstName);
    }

    public List<Tutor> findTutorByLastNameStartingWith(String lastName) {
        return tutorrepo.findByLastNameStartingWith(lastName);
    }
}
