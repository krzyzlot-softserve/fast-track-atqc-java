package org.kzlot.employee;

public class Employee {

    private String name;
    private String surname;
    private int baseSalary;
    private int experience;

    public Employee(String name, String surname, int baseSalary, int experience) {
        this.name = name;
        this.surname = surname;
        this.baseSalary = baseSalary;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }


    public double countedSalary() {
        if (experience > 2 && experience <= 5) {
            return baseSalary + 200;
        }
        else if (experience > 5) {
           return baseSalary * 1.2 + 500;
        }
        else {
            return baseSalary;
        }
    }
}
