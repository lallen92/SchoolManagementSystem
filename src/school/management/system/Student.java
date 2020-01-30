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
    private double feesPaid;
    private double feesTotal;

    /**
     *  To create a new student by initializing
     *  Fees for every student is $30,000
     *  Fees paid initially is 0
     *  @param name name of the student
     *  @param grade grade of the student
     */

     public Student(String name, int grade, double feePayment){
         this.feesPaid = feePayment;
         this.feesTotal = 10000;
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
    public void payFees(double fees){
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

    public double getFeesPaid()
    {
        return feesPaid;
    }

    public double getFeesTotal()
    {
        return feesTotal;
    }

    /**
     *
     * @return the remaining fees
     */
    public double getRemainingFees(){
        return feesTotal - feesPaid;
    }
}
