package school.management.system;

import java.util.List;

/**
 *  Created by Liam on 29/01/2020
 *  Many teachers, many students
 *  Implements teachers and students using an arrayList
 */
public class School
{
    private List<Teacher> teachers;
    private List<Student> students;
    private static int totalMoneyEarned;
    private static int totalMoneySpent;

    /**
     * new school object is created
     * @param teachers list of teachers in the school
     * @param students list of students in the school
     */
    public School(List<Teacher> teachers, List<Student> students)
    {
        this.teachers = teachers;
        this.students = students;
        totalMoneyEarned = 0;
        totalMoneySpent = 0;
    }

    /**
     *
     * @return list of teachers in the school
     */
    public List<Teacher> getTeachers()
    {
        return teachers;
    }

    /**
     * Adds a teacher to the school
     * @param teacher The teacher to be added
     */
    public void addTeachers(Teacher teacher)
    {
        teachers.add(teacher);
    }

    /**
     *
     * @return the list of students in the school
     */
    public List<Student> getStudents()
    {
        return students;
    }

    /**
     * Adds a student to the school
     * @param student The student to be added
     */
    public void addStudents(Student student)
    {
        students.add(student);
    }

    /**
     *
     * @return the total money in the school
     */
    public int getTotalMoneyEarned()
    {
        return totalMoneyEarned;
    }

    /**
     * Adds the total money in the school
     * @param moneyEarned the money that is supposed to be added
     */
    public static void updateTotalMoneyEarned(double moneyEarned)
    {
        totalMoneyEarned += moneyEarned;
    }

    /**
     *
     * @return the total money spent by the school
     */
    public int getTotalMonetSpent()
    {

        return totalMoneySpent;
    }

    /**
     * Update the the moment that the school has spent
     * @param moneySpent the teachers salary
     */
    public static void updateTotalMonetSpent(int moneySpent)
    {
        totalMoneyEarned -= moneySpent;
    }
}
