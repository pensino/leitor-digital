/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pensino.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emiliowl
 */
public class CoursesTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateCourseWithoutName() {
        new Course(null, "description", "category", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateCourseWithoutCategory() {
        new Course("name", "description", null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateCourseWithoutStep() {
        new Course("name", "description", "category", 0);
    }

    @Test
    public void coursesWithSameNameMustBeEquals() {
        assertTrue(new Course("name", "123", "category", 1).equals(new Course("name", "456", "another category", 2)));
    }
}
