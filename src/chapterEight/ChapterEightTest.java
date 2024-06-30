package chapterEight;

import java.util.EnumSet;

public class ChapterEightTest {
    public static void main(String[] args) {
        System.out.printf("Employees amount: %d", EmployeeE.count);
        DateE birth = new DateE(10, 12, 2002);
        DateE hire = new DateE(27, 12, 2023);

        EmployeeE employeeE = new EmployeeE("Caio", "Souza", birth, hire);

        DateE birth2 = new DateE(13, 9, 2005);
        DateE hire2 = new DateE(17, 7, 2025);

        EmployeeE employeeE2 = new EmployeeE("Luiza", "Silva", birth, hire);

        System.out.printf("Employees amount: %d", EmployeeE.count);
        System.out.printf("Employees amount by getCount: %d", employeeE.getCount());


        System.out.println("All books:");

        for (BookE bookE: BookE.values()) { // Retorna um array das constantes do enum na ordem em que foi declarada
            System.out.printf("%-10s%-45s%s%n", bookE, bookE.getTitle(),bookE.getCopyrightYear());
        }

        for (BookE bookE : EnumSet.range(BookE.JHTP, BookE.CPPHTP)) { // Filtra um intervalo de valores das constantes
            System.out.printf("%-10s%-45s%s%n", bookE, bookE.getTitle(),bookE.getCopyrightYear());
        }
    }
}
