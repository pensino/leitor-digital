/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pensino.domain.model;

import br.com.pensino.domain.model.Employee.Function;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emiliowl
 */
public class ExpedientTimeTableTest {

    public static final String CPF = "37374688857";
    private Employee professor = new Employee("prof", "giraffales", CPF, "006970594", Function.PROFESSOR);
    Course course = new Course("ingles", "go go go", "bla", 1);
    private Discipline discipline = new Discipline("java", "OO programming language", 10);
    Grid grid = new Grid(course, discipline);
    private TimeTable timeTable = new TimeTable(professor, grid);

    @Test
    public void shouldInstantiateNewExpedientTimeTable() {
        assertNotNull(new ExpedientTimeTable(timeTable));
    }
}
