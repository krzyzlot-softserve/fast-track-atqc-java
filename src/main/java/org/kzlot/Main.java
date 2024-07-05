package org.kzlot;

import org.kzlot.department.Department;
import org.kzlot.employee.Designer;
import org.kzlot.employee.Developer;
import org.kzlot.employee.Employee;
import org.kzlot.employee.Manager;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Designer designer1 = new Designer ("Chris", "Denson", 6000, 4, 1.5);
        Designer designer2 = new Designer ("Felix", "Lamand", 3000, 10, 0);
        Designer designer3 = new Designer ("Paul", "Minger", 9000, 1, 0.5);

        Designer designer4 = new Designer ("Oleg", "Poifigo", 1000, 0, -1);
        Designer designer5 = new Designer ("Taras", "Blekiss", 20000, 11, 2);

        Developer developer1 = new Developer ("Xavier", "Posalde", 5000, 3);
        Developer developer2 = new Developer ("Jake", "Quyilse", 2000, 2);
        Developer developer3 = new Developer ("Ola", "Iwankowicz", 12000, 5);

        Developer developer4 = new Developer ("Gregory", "Foods", 8000, 3);

        List<Employee> team = new ArrayList<>();
        team.add(designer1);
        team.add(designer2);
        team.add(designer3);
        team.add(developer1);
        team.add(developer2);
        team.add(developer3);

        List<Employee> team2 = new ArrayList<>();
        team.add(designer4);
        team.add(designer5);
        team.add(developer4);

        Manager manager1 = new Manager("Ivan", "Vilson", 15000, 2, team);
        Manager manager2 = new Manager("Anna", "Rubens", 500, 2, team2);

        List<Manager> managers = new ArrayList<>();
        managers.add(manager1);
        managers.add(manager2);

        Department department = new Department(managers);
        department.giveSalary();


    }
}
