package org.kzlot.employee;

public class Designer extends Employee {

    private double effFac;

    public Designer(String name, String surname, int baseSalary, int experience, double effFac) {
        super(name, surname, baseSalary, experience);
        this.effFac = countEffFac(effFac);
    }

    public double getEffFac() {
        return effFac;
    }

    private double countEffFac(double effFacVar) {
        if (effFacVar >= 0 && effFacVar <= 1) {
            return effFacVar;
        } else if (effFacVar < 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public double countedSalary() {
        return super.countedSalary() * effFac;
    }

}
