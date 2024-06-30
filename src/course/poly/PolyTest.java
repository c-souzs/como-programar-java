package course.poly;

import course.poly.entites.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PolyTest {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // example1();
        example2();

        sc.close();
    }

    private static void example2() {
        System.out.print("Entre the number products: ");
        int amountProducts = sc.nextInt();

        List<Product> products = new ArrayList<>();

        for (int i = 0; i < amountProducts; i++) {
            System.out.printf("Product #%d data: %n", i + 1);
            System.out.print("Common, used, imported (c/u/i): ");
            char typeProduct = sc.next().charAt(0);
            sc.nextLine();
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Price: ");
            double price = sc.nextDouble();

            if(typeProduct == 'i') {
                System.out.print("Customs fee (R$): ");
                double customsFee = sc.nextDouble();

                products.add(new ImportedProduct(name, price, customsFee));
            } else if(typeProduct == 'u') {
                System.out.print("Manufacture date (DD/MM/YYYY): ");
                String date = sc.next();
                sc.nextLine();

                products.add(new UsedProduct(name, price, date));
            } else {
                products.add(new Product(name, price));
            }
        }

        for (Product product: products) {
            System.out.println(product.priceTag());
        }
    }

    private static void example1() {
        System.out.print("Entre the number of employees: ");
        int numberEmployees = sc.nextInt();

        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < numberEmployees; i++) {
            System.out.printf("Employee #%d data: %n", i + 1);
            System.out.print("Outsourced (y/n): ");
            boolean outsourced = sc.next().equals("y");
            sc.nextLine();
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Hours: ");
            int hours = sc.nextInt();
            System.out.print("Value per hour: ");
            double valuePerHour = sc.nextDouble();

            if(outsourced) {
                System.out.print("Additional charge: ");
                double additionalCharge = sc.nextDouble();
                employees.add(new OutsourceEmployee(name, hours, valuePerHour, additionalCharge));
            } else {
                employees.add(new Employee(name, hours, valuePerHour));
            }
        }

        System.out.println("----------------------------------");
        System.out.println("Payments: ");
        for (Employee employee: employees) {
            System.out.println(employee);
        }
    }
}
