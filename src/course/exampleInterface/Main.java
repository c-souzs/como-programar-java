package course.exampleInterface;

import course.exampleInterface.model.entites.CarRental;
import course.exampleInterface.model.entites.Contract;
import course.exampleInterface.model.entites.Installment;
import course.exampleInterface.model.entites.Vehicle;
import course.exampleInterface.model.services.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmtDateAndTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // exampleCarRental(sc, fmtDateAndTime);
        // exampleContract(sc, fmtDate);
        // exampleComparable();
        exampleMethodDefault(sc);
        sc.close();
    }

    public static void exampleMethodDefault(Scanner sc) {
        double amount;
        int months;

        System.out.println("Amount: ");
        amount = sc.nextDouble();
        System.out.println("Months: ");
        months = sc.nextInt();

        InterestService interestService = new BrazilInterestService(2.0);
        double payment = interestService.payment(amount, months);

        System.out.printf("Payment after %d months: %.2f", months, payment);
    }

    public static void exampleComparable() {
        List<Vehicle> vehicles = new ArrayList<>();

        vehicles.add(new Vehicle("HB20", 25456.88));
        vehicles.add(new Vehicle("Civic", 30123.44));
        vehicles.add(new Vehicle("Tesla", 15999.11));
        vehicles.add(new Vehicle("Asta", 26456.88));

        for (Vehicle vehicle: vehicles) {
            System.out.printf("Car model: %s %n Price car: %.2f %n", vehicle.getModel(), vehicle.getPrice());
        }
        System.out.println("-------------------------------------");
        Collections.sort(vehicles);

        for (Vehicle vehicle: vehicles) {
            System.out.printf("Car model: %s %n Price car: %.2f %n", vehicle.getModel(), vehicle.getPrice());
        }
    }

    public static void exampleContract(Scanner sc, DateTimeFormatter fmt) throws ParseException {
        int number, amountInstallment;
        double totalValue;
        LocalDate date;

        System.out.println("Entre com os dados do contrato: ");
        System.out.print("Numero: ");
        number = sc.nextInt();
        System.out.print("Data (dd/MM/yyyy): ");
        date =  LocalDate.parse(sc.next(), fmt);
        System.out.print("Valor do contrato: ");
        totalValue = sc.nextDouble();

        Contract contract = new Contract(number, date, totalValue);

        System.out.print("Entre com o numero de parcelas: ");
        amountInstallment = sc.nextInt();

        ContractService contractService = new ContractService(new PaypalService());
        contractService.processContract(contract, amountInstallment);

        System.out.println("PARCELAS: ");

        for (Installment installment: contract.getInstallments()) {
            System.out.printf("%s - %.2f %n", fmt.format(installment.getDueDate()), installment.getAmount());
        }
    }

    public static void exampleCarRental(Scanner sc, DateTimeFormatter fmt) {
        String carModel;
        LocalDateTime start, finish;
        double priceCar, pricePerDay, pricePerHour;

        System.out.println("Entre com os dados do aluguel");
        System.out.print("Modelo do carro: ");
        carModel = sc.nextLine();
        System.out.print("Preco do carro: ");
        priceCar = sc.nextDouble();
        System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
        start = LocalDateTime.parse(sc.nextLine(), fmt);
        System.out.print("Retorno (dd/MM/yyyy hh:mm): ");
        finish = LocalDateTime.parse(sc.nextLine(), fmt);

        CarRental carRental = new CarRental(start, finish, new Vehicle(carModel, priceCar));

        System.out.print("Entre com o preco por hora:");
        pricePerHour = sc.nextDouble();
        System.out.print("Entre com o preco por dia: ");
        pricePerDay = sc.nextDouble();

        RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());
        rentalService.processInvoice(carRental);

        System.out.println("FATURA");
        System.out.printf("Pagamento basico: R$ %.2f %n", carRental.getInvoice().getBasicPayment());
        System.out.printf("Imposto: R$ %.2f %n", carRental.getInvoice().getTax());
        System.out.printf("Pagamento total: R$ %.2f %n", carRental.getInvoice().getTotalPayment());
    }
}
