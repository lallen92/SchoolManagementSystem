package school.management.system;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Teacher lizzy = new Teacher(1, "Lizzy", 500);
        Teacher mellisa = new Teacher(2, "Mellisa", 700);
        Teacher tom = new Teacher(3, "tom", 600);

        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(lizzy);
        teacherList.add(mellisa);
        teacherList.add(tom);

        Student sarah = new Student(1, "Sarah", 4);
        Student liam = new Student(1, "Liam", 12);
        Student john = new Student(1, "John", 5);

        List<Student> studentList = new ArrayList<>();
        studentList.add(sarah);
        studentList.add(liam);
        studentList.add(john);

        School itb = new School(teacherList, studentList);
        liam.payFees(5000);
        sarah.payFees(6000);
        System.out.println("ITB has earned: $" + itb.getTotalMoneyEarned());
        System.out.println("-----MAKING SCHOOL PAY TEACHER SALARY----- ");

        lizzy.receiveSalary(lizzy.getSalary());
        System.out.println("ITB has spent for salary to " + lizzy.getName()
        +" and now has $" + itb.getTotalMoneyEarned());

        tom.receiveSalary(tom.getSalary());
        System.out.println("ITB has spent for salary to " + tom.getName()
                +" and now has $" + itb.getTotalMoneyEarned());

    }
}
