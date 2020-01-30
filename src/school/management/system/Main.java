package school.management.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{
    public static final String INSERT_TEACHER_SQL_QUERY  = "INSERT INTO TEACHERS(teacherId, name, Salary, school) VALUES(?,?,?,?)";
    public static final String SELECT_TEACHERS_SQL_QUERY = "SELECT * FROM teachers";

    public static void main(String[] args) throws SQLException
    {

        final String welcomeMessage = "Welcome to ITB System Portal";
        final String optionsMessage = new StringBuilder()
                .append("For 'Teacher Administration', please enter the Number: 1\n")
                .append("For 'Student Administration' please the number: 2,\n")
                .append("For 'Financial Administration', please press the number: 3\n")
                .append("To Exit', please press the number: 4").toString();
        int numberEntered;
        int inputChoice = -1;

        System.out.println(welcomeMessage);

        while (inputChoice == -1)
        {
            try
            {
                System.out.println(optionsMessage);
                Scanner input = new Scanner(System.in);
                numberEntered = input.nextInt();
                inputChoice = selectOption(numberEntered, 4);
            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid entry, please enter a valid option!\n");
            }
        }
        switch (inputChoice)
        {
            case 1:
                System.out.println("--------Teacher Administration--------");
                teacherAdmin();
                break;
            case 2:
                System.out.println("--------Student Administration--------");
                break;
            case 3:
                System.out.println("--------Financial Administration--------");
                break;
            case 4:
                System.exit(0);
        }

    }

    private static void teacherAdmin() throws SQLException
    {
        int inputChoiceTeacher = -1;
        final String teachersMessage = new StringBuilder()
                .append("To add a new teacher, please enter the Number: 1\n")
                .append("To get a list off all existing teachers, please enter the Number: 2,\n")
                .append("To Exit', please press the number: 3").toString();
        int numberEntered;
        int maxSize = 0;
        double teachersalary;
        String teacherName;

        while (inputChoiceTeacher == -1)
        {
            try
            {
                System.out.println(teachersMessage);
                Scanner input = new Scanner(System.in);
                numberEntered = input.nextInt();
                inputChoiceTeacher = selectOption(numberEntered, maxSize=2);
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
                teachersalary = input.nextDouble();
                Teacher teacher = new Teacher(teacherName, teachersalary, 10);
                insertNewTeacher(teacher);
                break;
            case 2:
                System.out.println("--------List All Teachers--------");
                queryAllTeachers();
                break;
            case 3:
                System.exit(0);
        }

    }

    private static void queryAllTeachers()
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
            ps = con.prepareStatement(SELECT_TEACHERS_SQL_QUERY);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                System.out.print(rs.getInt(1));
                System.out.print(": ");
                System.out.println(rs.getString(2));
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
            ps.setInt( 4, T.getSchool());

            ps.execute();
            System.out.println( "insertPerson => " + ps.toString() );
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
