package school.management.system;
/**
 *  Created by Liam on 29/01/2020
 *  This class is responsible for keeping track of the teachers
 *  id, name, salary
 */
public class Teacher
{
    private int id;
    private  String name;
    private int salary;
    private int salaryEarned;

    /**
     *  To create a new teacher object by initializing
     *  @param id uniquely identify the student
     *  @param name name of the student
     *  @param salary salary of the student
     */
    public Teacher(int id, String name, int salary){
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.salaryEarned = 0;
    }

    /**
     *
     * @return the teachers id
     */
    public int getId()
    {
        return id;
    }

    /**
     *
     * @return the teachers name
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     * @return the teachers salary
     */
    public int getSalary()
    {
        return salary;
    }

    /**
     *
     * @param salary - Set the salary
     */
    public void setSalary(int salary){
        this.salary = salary;
    }

    /**
     * Adds to the salary earned.
     * Removes from total momeny earned to the school
     * @param salary The amount paid in salary
     */
    public void receiveSalary(int salary){
        salaryEarned += salary;
        School.updateTotalMonetSpent(salary);
    }
}
