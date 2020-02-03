package school.management.system;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{
    public static final String INSERT_TEACHER_SQL_QUERY  = "INSERT INTO TEACHERS(teacherId, name, Salary, school) VALUES(?,?,?,?)";
    public static final String SELECT_TEACHERS_SQL_QUERY = "SELECT * FROM teachers";
    public static final String INSERT_STUDENT_SQL_QUERY  = "INSERT INTO STUDENTS(studentId, Name, grade, fees_paid, fees_total) VALUES(?,?,?,?,?)";
    public static final String SELECT_STUDENTS_SQL_QUERY = "SELECT * FROM students";
    public static final String SELECT_FINANCE_SQL_QUERY  = "SELECT * FROM school";
    public static final String UPDATE_SCHOOL_TOTAL_SPENT = "UPDATE schoolmanagementsystem.school SET total_money_spent = (SELECT sum(salary) FROM schoolmanagementsystem.teachers) where schoolId = 1;";
    public static final String UPDATE_SCHOOL_TOTAL_EARNT = "UPDATE schoolmanagementsystem.school SET total_money_earned = (SELECT sum(fees_paid) FROM schoolmanagementsystem.students) where schoolId = 1;";

    public static void main(String[] args) throws SQLException
    {

        final String welcomeMessage = "Welcome to ITB System Portal";
        final String optionsMessage = new StringBuilder()
                .append("For 'Teacher Administration', please enter the Number: 1\n")
                .append("For 'Student Administration' please the number: 2,\n")
                .append("For 'Financial Administration', please press the number: 3\n")
                .append("To Exit', please press the number: 4").toString();
        int inputChoice;
        int maxSize = 4;

        System.out.println(welcomeMessage);
        inputChoice= getChoice(optionsMessage, maxSize);
        switch (inputChoice)
        {
            case 1:
                System.out.println("--------Teacher Administration--------");
                teacherAdmin();
                break;
            case 2:
                System.out.println("--------Student Administration--------");
                studentAdmin();
                break;
            case 3:
                System.out.println("--------Financial Administration--------");
                queryAll(SELECT_FINANCE_SQL_QUERY);
                break;
            case 4:
                System.exit(0);
        }
    }


    private static void studentAdmin() throws SQLException
    {
        int inputChoiceStudent;
        final String studentsMessage = new StringBuilder()
                .append("To add a new student, please enter the Number: 1\n")
                .append("To get a list off all existing students, please enter the Number: 2,\n")
                .append("To Exit', please press the number: 3").toString();
        int maxSize = 3;
        String studentName;
        int studentGrade;
        double feePayment;

        inputChoiceStudent = getChoice(studentsMessage, maxSize);
        switch (inputChoiceStudent)
        {
            case 1:
                System.out.println("--------Add New Student--------");
                Scanner input = new Scanner(System.in);
                System.out.println("Please enter their name: \n");
                studentName = input.next();
                System.out.println("Please enter their grade: \n");
                input = new Scanner(System.in);
                studentGrade = input.nextInt();
                System.out.println("Please enter a fee payment amount: \n");
                input = new Scanner(System.in);
                feePayment = input.nextInt();
                Student student = new Student(studentName, studentGrade, feePayment);
                insertNewStudent(student);
                break;
            case 2:
                System.out.println("--------List All Students--------");
                queryAll(SELECT_STUDENTS_SQL_QUERY);
                break;
            case 3:
                System.exit(0);
        }
    }

    private static int getChoice(String Message, int maxSize)
    {
        int numberEntered;
        int inputChoice = -1;
        while (inputChoice == -1)
        {
            try
            {
                System.out.println(Message);
                Scanner input = new Scanner(System.in);
                numberEntered = input.nextInt();
                inputChoice= selectOption(numberEntered, maxSize);
            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid entry, please enter a valid option!\n");
            }
        }
        return inputChoice;
    }

    private static void teacherAdmin() throws SQLException
    {
        int inputChoiceTeacher = -1;
        final String teachersMessage = new StringBuilder()
                .append("To add a new teacher, please enter the Number: 1\n")
                .append("To get a list off all existing teachers, please enter the Number: 2,\n")
                .append("To Exit', please press the number: 3").toString();
        int numberEntered;
        int maxSize = 2;
        double teacherSalary;
        String teacherName;

        while (inputChoiceTeacher == -1)
        {
            try
            {
                System.out.println(teachersMessage);
                Scanner input = new Scanner(System.in);
                numberEntered = input.nextInt();
                inputChoiceTeacher = selectOption(numberEntered, maxSize);
            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid entry, please enter a valid option!\n");
            }
        }
        switch (inputChoiceTeacher)
        {
            case 1:
                System.out.println("--------Add New Teacher--------");
                Scanner input = new Scanner(System.in);
                System.out.println("Please enter their name: \n");
                teacherName = input.next();
                System.out.println("Please enter their salary: \n");
                input = new Scanner(System.in);
                teacherSalary = input.nextDouble();
                Teacher teacher = new Teacher(teacherName, teacherSalary);
                insertNewTeacher(teacher);
                break;
            case 2:
                System.out.println("--------List All Teachers--------");
                queryAll(SELECT_TEACHERS_SQL_QUERY);
            case 3:
                System.exit(0);
        }

    }

    private static void queryAll(String selectSqlQuery)
    {
        Connection con;
        PreparedStatement ps;
        try
        {
            con = JdbcMySQLHelper.getConnection();
            if (con == null)
            {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return;
            }
            con.setAutoCommit(false);
            ps = con.prepareStatement(selectSqlQuery);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next())
            {
                for(int i = 1 ; i <= columnsNumber; i++)
                {
                    System.out.print(rs.getString(i) + " "); //Print one element of a row
                }
                System.out.println();
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static void insertNewTeacher(Teacher T) throws SQLException
    {
        Connection con = null;
        PreparedStatement ps = null;
        try
        {
            con = JdbcMySQLHelper.getConnection();
            if ( con == null )
            {
                System.out.println( "Error getting the connection. Please check if the DB server is running" );
                return;
            }
            con.setAutoCommit( false );
            ps = con.prepareStatement(INSERT_TEACHER_SQL_QUERY);
            ps.setInt( 1, T.getId());
            ps.setString( 2, T.getName());
            ps.setDouble( 3, T.getSalary());
            ps.setString( 4, T.getSchool());

            ps.execute();
            System.out.println( "insertPerson => " + ps.toString() );
            con.commit();

            ps = con.prepareStatement(UPDATE_SCHOOL_TOTAL_SPENT);
            ps.execute();
            con.commit();
        }
        catch ( SQLException e )
        {
            if ( con != null )
            {
                con.rollback();
            }
            throw e;
        }
        finally
        {
            JdbcMySQLHelper.closePreparedStatement(ps);
            JdbcMySQLHelper.closeConnection(con);
        }
    }

    private static void insertNewStudent(Student S) throws SQLException
    {
        Connection con = null;
        PreparedStatement ps = null;
        try
        {
            con = JdbcMySQLHelper.getConnection();
            if ( con == null )
            {
                System.out.println( "Error getting the connection. Please check if the DB server is running" );
                return;
            }
            con.setAutoCommit( false );
            ps = con.prepareStatement(INSERT_STUDENT_SQL_QUERY);
            ps.setInt( 1, S.getId());
            ps.setString( 2, S.getName());
            ps.setDouble( 3, S.getGrade());
            ps.setDouble( 4, S.getFeesPaid());
            ps.setDouble( 5, S.getFeesTotal());

            ps.execute();
            System.out.println( "insertStudent => " + ps.toString() );
            con.commit();

            ps = con.prepareStatement(UPDATE_SCHOOL_TOTAL_EARNT);
            ps.execute();
            con.commit();
        }
        catch ( SQLException e )
        {
            if ( con != null )
            {
                con.rollback();
            }
            throw e;
        }
        finally
        {
            JdbcMySQLHelper.closePreparedStatement(ps);
            JdbcMySQLHelper.closeConnection(con);
        }
    }

    public static int selectOption(int numberEntered, int maxSize )
    {
        int validEntry;

        if ((numberEntered >=1) &&( numberEntered <= maxSize))
        {
            validEntry = numberEntered;
        }
        else
        {
            validEntry = -1;
        }
        return validEntry;
    }
}
