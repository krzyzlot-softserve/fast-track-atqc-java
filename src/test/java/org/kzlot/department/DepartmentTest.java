package org.kzlot.department;

import org.kzlot.employee.Designer;
import org.kzlot.employee.Developer;
import org.kzlot.employee.Employee;
import org.kzlot.employee.Manager;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class DepartmentTest {

    @Test
    public void testCountEffFac() {

        //Given
        Designer designerUnderTest1 = new Designer("Alibaba", "Gunther", 8000, 3, -10);

        //When
       double actualResult = designerUnderTest1.getEffFac();

        //Then
        assertEquals(actualResult, 0);
    }

    @Test
    public void testCountedSalary() {

        //Given
        Designer designerUnderTest2 = new Designer ("Kasia", "Kowalska", 20000, 11, 2);
        Developer developerUnderTest1 = new Developer ("Bartek", "Grygu", 5000, 3);
        Developer developerUnderTest2 = new Developer ("Mama", "Milka", 2000, 2);

        List<Employee> teamUnderTest1 = new ArrayList<>();
        teamUnderTest1.add(designerUnderTest2);
        teamUnderTest1.add(developerUnderTest1);
        teamUnderTest1.add(developerUnderTest2);

        Manager managerUnderTest1 = new Manager("Carol", "Freash", 10000, 1, teamUnderTest1);

        //When
        double actualResult = managerUnderTest1.countedSalary();
        double actualResult2 = designerUnderTest2.countedSalary();
        double actualResult3 = developerUnderTest1.countedSalary();
        double actualResult4 = developerUnderTest2.countedSalary();

        //Then
        assertEquals(actualResult, 11000);
        assertEquals(actualResult2, 24500);
        assertEquals(actualResult3, 5200);
        assertEquals(actualResult4, 2000);


    }

}
