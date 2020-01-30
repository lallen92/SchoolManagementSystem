package school.management.system;
/**
 *  Created by Liam on 29/01/2020
 *  This class is responsible for keeping track of the teachers
 *  id, name, salary
 */
public class Teacher
{
    private int id;
    private  String firstName;
    private double salary;
    private int salaryEarned;
    private final String school;

    /**
     *  To create a new teacher object by initializing
     *  @param firstName name of the student
     * @param salary salary of the student
     */
    public Teacher(String firstName, double salary){
        this.id = 0;
        this.firstName = firstName;
        this.salary = salary;
        this.salaryEarned = 0;
        this.school = "ITB";
    }

    public Integer getId()
    {
        return id;
    }

    public String getName()
    {
        return firstName;
    }


    public double getSalary()
    {
        return salary;
    }

    public String getSchool()
    {
        return school;
    }

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
