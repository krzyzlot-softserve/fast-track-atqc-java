package org.kzlot.employee;

import java.util.List;

public class Manager extends Employee {

    private final List<Employee> team;

    public Manager(String name, String surname, int baseSalary, int experience, List<Employee> team) {
        super(name, surname, baseSalary, experience);
        this.team = team;
    }

    public List<Employee> getTeam() {
        return team;
    }

    private double bonusForTeamSize() {
        if (team.size() >= 5 && team.size() < 10) {
            return 200;
        }
        else if (team.size() >= 10 ) {
            return 300;
        }
        else {
            return 0;
        }
    }

    private boolean isDevHalfOfTeamCheck() {
        int countedDevs = 0;
        for (Employee employee: team) {
            if (employee instanceof Developer) {
                countedDevs++;
            }
        }
        return countedDevs > (0.5 * team.size());
    }

    private double bonusForDevTeamMembers() {
    if (isDevHalfOfTeamCheck()) {
       return super.countedSalary() * 0.10;
    }
    return 0;
    }

    @Override
    public double countedSalary() {
        return super.countedSalary() + bonusForDevTeamMembers() + bonusForTeamSize();
    }
}
