package org.kzlot.department;

import org.kzlot.employee.Employee;
import org.kzlot.employee.Manager;

import java.util.List;

public class Department {

    private final List<Manager> managers;

    public Department(List<Manager> managers) {
        this.managers = managers;
    }

    public void giveSalary() {
        for (Manager manager : managers) {
            System.out.println(manager.getName() + " " + manager.getSurname() + " received " + manager.countedSalary() + " dollars.");
            for (Employee employee: manager.getTeam()) {
                System.out.println(employee.getName() + " " + employee.getSurname() + " received " + employee.countedSalary() + " dollars.");
            }
        }
    }
}
