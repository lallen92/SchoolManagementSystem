package school.management.system;

/**
 *  Created by Liam on 29/01/2020
 *  This class is responsible for keeping track of student
 *  name, id, grade and fees
 */
public class Student
{
    private int id;
    private String name;
    private int grade;
    private int feesPaid;
    private int feesTotal;

    /**
     *  To create a new student by initializing
     *  Fees for every student is $30,000
     *  Fees paid initially is 0
     *  @param id uniquely identify the student
     *  @param name name of the student
     *  @param grade grade of the student
     */

     public Student(int id, String name, int grade){
         this.feesPaid = 0;
         this.feesTotal = 30000;
         this.id = id;
         this.name = name;
         this.grade = grade;
     }

     // Not going to alter students name, ID

    /**
     * Used to update the students grade
     * @param grade - The new grade of the student.
     */
    public void setGrade(int grade)
    {
        this.grade = grade;
    }

    /**
     * Used to keep updating the fees paid
     * @param fees - The amount of fees paid by the student.
     */
    public void payFees(int fees){
        feesPaid += fees;
        School.updateTotalMoneyEarned(feesPaid);
    }


    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getGrade()
    {
        return grade;
    }

    public int getFeesPaid()
    {
        return feesPaid;
    }

    public int getFeesTotal()
    {
        return feesTotal;
    }

    /**
     *
     * @return the remaining fees
     */
    public int getRemainingFees(){
        return feesTotal - feesPaid;
    }
}
