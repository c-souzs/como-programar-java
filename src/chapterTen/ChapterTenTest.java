package chapterTen;

import chapterTen.entites.*;

public class ChapterTenTest {
    public static void main(String[] args) {
        SalariedEmployeePoly salariedEmployeePoly = new SalariedEmployeePoly("Caio", "Souza", "(11) 1111-1111", 800.00);
        HourlyEmployeePoly hourlyEmployeePoly = new HourlyEmployeePoly("Ana", "Luiza", "(220 22222-2222)", 16.75, 40);
        CommissionEmployeePoly commissionEmployeePoly = new CommissionEmployeePoly("Maria", "Camila", "(33) 33333-3333", 10000, .6);
        BasePlusCommissionEmployeePoly basePlusCommissionEmployeePoly = new BasePlusCommissionEmployeePoly("Amanda", "Fernandes", "(44) 44444-4444", 5000, .4, 300);

        EmployeePoly[] employeesPoly = new EmployeePoly[4];

        employeesPoly[0] = salariedEmployeePoly;
        employeesPoly[1] = hourlyEmployeePoly;
        employeesPoly[2] = commissionEmployeePoly;
        employeesPoly[3] = basePlusCommissionEmployeePoly;

        for (EmployeePoly employeePoly: employeesPoly) {
            System.out.println(employeePoly);

            if(employeePoly instanceof BasePlusCommissionEmployeePoly employeePlusPoly) { // Downcasting

                employeePlusPoly.setBaseSalary(1.10 * employeePlusPoly.getBaseSalary());

                System.out.printf("new base salary with 10%% increase is: $%,.2f%n", employeePlusPoly.getBaseSalary());
            }
        }
    }
}
