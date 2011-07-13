
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pensino.utils.db;

import java.util.Set;
import br.com.pensino.utils.db.EmployeeDAO.By;
import java.util.Date;
import br.com.pensino.domain.model.Course;
import br.com.pensino.domain.model.Discipline;
import br.com.pensino.domain.model.Employee;
import br.com.pensino.domain.model.ExpedientTimeTable;
import br.com.pensino.domain.model.Grid;
import br.com.pensino.domain.model.Lesson;
import br.com.pensino.domain.model.Student;
import br.com.pensino.domain.model.TimeTable;
import java.util.TreeSet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emiliowl
 */
public class LessonDAOTest {

    private LessonDAO lessonDAO = new LessonDAO();
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private StudentDAO studentDAO = new StudentDAO();
    private Employee professor = employeeDAO.find(By.DOCUMENT, "37374688857").get(0);
    private Discipline discipline = new Discipline("java", "OO programming language", 10);
    private Course course = new Course("ingles", "go go go", Course.Category.BIMESTRAL, 1);
    private Grid grid = new Grid(course, discipline);
    private TimeTable timeTable;
    Set<Student> enrolledStudents = new TreeSet<Student>();
    private Student emilio = new Student("Emilio", "Resende", "37374688857", "071111");
    private Student paulo = new Student("Paulo", "Schiavon", "33166403809", "071112");
    private ExpedientTimeTable expedientTimeTable;
    

    @Before
    public void start() {
        enrolledStudents.add(emilio);
        enrolledStudents.add(paulo);
        timeTable = new TimeTable(professor, grid, enrolledStudents);
        expedientTimeTable = new ExpedientTimeTable(timeTable);
    }
    
    @Test
    public void shouldSaveLessonToDB() {
        employeeDAO.destroy();
        enrolledStudents.clear();
        Student student = studentDAO.find(1);
        studentDAO.destroy();
        enrolledStudents.add(student);
        timeTable = new TimeTable(professor, grid, enrolledStudents);
        expedientTimeTable = new ExpedientTimeTable(timeTable);
        
        Lesson lesson = new Lesson(expedientTimeTable, new Date(), new Date(), new Date());
        lessonDAO.save(lesson);
        /*
        Lesson loadedLesson = lessonDAO.find(1);
        loadedLesson.start();
        System.out.println(loadedLesson.getAbsent().toString());
        */
        //assertTrue(lessonDAO.save(lesson));
    }

    @Test
    public void shouldRetrieveLessonFromDB() {
        Lesson lesson = lessonDAO.find(1);
        assertNotNull(lesson);
        assertTrue(lesson.getProfessor().equals(professor));
    }

    @Test
    public void shouldRetrieveCurrentLessons() {
        assertTrue(lessonDAO.findCurrentLessons().size() > 0);
    }

    @Test
    public void shouldInitializeLesson() {
        Lesson lesson = lessonDAO.find(1);
        lesson.start();
        lessonDAO.save(lesson);
        assertTrue(lesson.isStarted());
    }

    @Test
    public void shouldRetrieveCurrentLessonsByProfessor() {
        Lesson lesson = lessonDAO.findCurrentLesson(professor);
        assertTrue(lesson.getProfessor().equals(professor));
    }
}