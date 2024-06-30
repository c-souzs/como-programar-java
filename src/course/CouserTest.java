package course;

import course.entites.*;
import course.entites.enums.WorkerLevel;
import course.utils.CurrencyConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CouserTest {
    public static void main(String[] args) {
        // withoutOO();
        // usingOO();
        // exampleProduct();
        // exampleConverterCurrency();
        // exampleAccount();
        // exampleArray();
        // exampleArray2();
        // exampleArray3();
        // usingLists();
        // exerciseList();
        //exampleComposition1();
    }

    public static void exampleComposition1() {
        Scanner sc = new Scanner(System.in);

        String readName, readLevel, readNameDepartment;
        double readBaseSalaray;

        System.out.println("----- Cadastrar trabalhador -----");
        System.out.print("Nome do trabalhador: ");
        readName = sc.nextLine();
        System.out.println();
        System.out.print("Salario base do trabalhador: ");
        readBaseSalaray = sc.nextDouble();
        System.out.println();
        System.out.print("Categoria do trabalhador (Junior, Pleno, Senior)");
        readLevel = sc.next();
        System.out.println();
        System.out.print("Nome do departamento do trabalhador: ");
        readNameDepartment = sc.nextLine();
        System.out.println();

        WorkerLevel convertReadLevel = WorkerLevel.valueOf(readLevel.equals("Junior") ? "JUNIOR" : readLevel.equals("Pleno") ? "MID_LEVEL" : "SENIOR");

        Worker worker = new Worker(readName, convertReadLevel, readBaseSalaray, new Department(readNameDepartment));
        int amountContracts;

        System.out.println("----- Adicionar contratos ao trabalhador -----");
        System.out.print("Nome do trabalhador: ");
        amountContracts = sc.nextInt();
        System.out.println();

        double valuePerHour;
        int amountHours;

        for (int i = 0; i <= amountContracts; i++) {
            System.out.print("Salario por hora (R$):");
            valuePerHour = sc.nextDouble();
            System.out.println();
            System.out.print("Quantidade de horas trabalhada: ");
            amountHours = sc.nextInt();
            System.out.println();

            Date date = new Date();

            HourContract hourContract = new HourContract(date, valuePerHour, amountHours);
            worker.addContract(hourContract);
        }

        int readMonth, readYear;
        System.out.println("----- Consultar salario por mes/ano -----");
        System.out.print("Qual o mes? ");
        System.out.println();
        readMonth = sc.nextInt();
        System.out.println("Qual o ano?");
        System.out.println();
        readYear = sc.nextInt();

        double incomePerDate = worker.income(readMonth, readYear);
        System.out.printf("O salario em %d/%d foi de R$ %.2f %n", readMonth, readYear, incomePerDate);
        sc.close();
    }

    public static void exerciseList() {
        Scanner sc = new Scanner(System.in);

        System.out.println("----- Cadastro de funcionarios -----");

        System.out.print("Quantos funcarios deseja cadastrar? ");
        int amountEmployee = sc.nextInt();
        sc.nextLine();

        ArrayList<Employee> employees = new ArrayList<>();

        String readName;
        double readSalary;
        int readId;

        for (int i = 0; i < amountEmployee; i++) {
            System.out.print("Entre com o nome do funcionario: ");
            readName = sc.nextLine();
            System.out.print("Entre com o id do funcionario: ");
            readId = sc.nextInt();
            System.out.print("Entre com o salario (R$) do funcionario: ");
            readSalary = sc.nextDouble();

            sc.nextLine();

            employees.add(new Employee(readId, readName, readSalary));
        }

        for (Employee employee: employees) {
            System.out.println(employee);
        }

        System.out.println("Entre com o id do funcionario que deseja aumentar o salario: ");
        int readIdUp = sc.nextInt();

        Employee employeeUp = employees.stream().filter(e -> e.getId() == readIdUp).findFirst().orElse(null);

        if(employeeUp != null) {
            System.out.print("Entre com a porcentagem (%) que deseja aumentar: ");
            int valueUp = sc.nextInt();

            employeeUp.upSalary(valueUp);
        } else {
            System.out.println("Funcionario nao encontrado.");
        }

        for (Employee employee: employees) {
            System.out.println(employee);
        }

        sc.close();
    }

    public static void usingLists() {
       List<String> namesList = new ArrayList<>();

       namesList.add("Caio Souza");
       namesList.add("Ana Luiza");
       namesList.add("Bruna");
       namesList.add("Aldo");
       namesList.add("Maria");
       namesList.add("Michelly");

       namesList.add(2, "Alysson");
       namesList.add(3, "Mitermaya");

       for (String name: namesList) {
           System.out.println(name);
       }
       System.out.println("-----------------------------------");

       namesList.removeIf(x -> x.charAt(0) == 'M');

       for (String name: namesList) {
           System.out.println(name);
       }
       System.out.println("-----------------------------------");

       System.out.println("Index of Aldo: " + namesList.indexOf("Aldo"));
       System.out.println("Index of Mitermaya: " + namesList.indexOf("Mitermaya"));

       System.out.println("-----------------------------------");

       List<String> result = namesList.stream().filter(x -> x.charAt(0) == 'A').toList();
       for (String name: result) {
           System.out.println(name);
       }

       System.out.println("-----------------------------------");

       String lastName = result.get(result.size() - 1);
       System.out.println("Ultimo nome da lista eh: " + lastName);
    }

    public static void exampleArray3() {
        Person[] rooms = new Person[10];

        Scanner sc = new Scanner(System.in);

        System.out.println("----- Cadastro de quartos -----");
        System.out.print("Quantos quartos deseja cadastrar? ");
        int amount = sc.nextInt();
        sc.nextLine();

        String readName, readEmail;
        double readHeight;
        int readAge, readNumberRoom;

        for (int i = 0; i < amount; i++) {
            System.out.print("Entre com o nome da pessoa: ");
            readName = sc.nextLine();
            System.out.print("Entre com o email da pessoa: ");
            readEmail = sc.nextLine();
            System.out.print("Entre com a altura da pessoa (cm): ");
            readHeight = sc.nextDouble();
            System.out.print("Entre com a idade da pessoa: ");
            readAge = sc.nextInt();

            System.out.print("Qual quarto deseja ocupar? (1 - 10): ");
            readNumberRoom = sc.nextInt();

            sc.nextLine();

            if(readNumberRoom > 0 && readNumberRoom <= 10) {
                if(rooms[readNumberRoom - 1] != null) System.out.println("Quarto ja esta ocupado!");
                else rooms[readNumberRoom - 1] = new Person(readName, readAge, readHeight, readEmail);
            } else {
                System.out.println("Numero de quarto incorreto.");
            }
        }

        for (int i = 0; i < rooms.length; i++) {
            Person personRoom = rooms[i];

            if(personRoom != null) {
                System.out.printf("# %d ", i + 1);
                System.out.println(personRoom);
            }
        }

        sc.close();
    }

    public static void exampleArray2() {
        Scanner sc = new Scanner(System.in);

        System.out.println("----- Cadastro de pessoas -----");
        System.out.print("Quantos pessoas deseja cadastrar? ");
        int amount = sc.nextInt();
        sc.nextLine();

        Person[] persons = new Person[amount];

        String readName;
        double readHeight;
        int readAge;

        double sumHeights = 0.0;
        int amountPersonsLessThan16 = 0;

        for (int i = 0; i < persons.length; i++) {
            System.out.print("Entre com o nome da pessoa: ");
            readName = sc.nextLine();
            System.out.print("Entre com a altura da pessoa (cm): ");
            readHeight = sc.nextDouble();
            System.out.print("Entre com a idade da pessoa: ");
            readAge = sc.nextInt();

            sc.nextLine();

            persons[i] = new Person(readName, readAge, readHeight);

            sumHeights+=readHeight;
            if(readAge < 16) amountPersonsLessThan16++;
        }

        System.out.printf("Media da altura das %d pessoas e %.2f %n", persons.length, sumHeights / persons.length);
        System.out.printf("%.2f % tem idade menor que 16 anos", ((double)amountPersonsLessThan16 / persons.length) * 100);

        sc.close();
    }

    public static void exampleArray() {
        Scanner sc = new Scanner(System.in);

        System.out.println("----- Cadastro de produtos -----");
        System.out.print("Quantos produtos deseja cadastrar? ");
        int amount = sc.nextInt();
        sc.nextLine();

        Product[] products = new Product[amount];

        String readName;
        double readPrice;
        int readAmountStock;

        for (int i = 0; i < products.length; i++) {
            System.out.print("Entre com o nome do produto: ");
            readName = sc.nextLine();
            System.out.print("Entre com o preco do produto (R$): ");
            readPrice = sc.nextDouble();
            System.out.print("Entre com a quantidade em estoque desse produto: ");
            readAmountStock = sc.nextInt();

            sc.nextLine();

            products[i] = new Product(readName, readPrice, readAmountStock);
        }

        System.out.println("----- Exibindo nota fiscal -----");

        double sum = 0.0;

        for (Product productItem: products) {
            System.out.println(productItem);
            sum+=productItem.totalValueInStock();
        }

        System.out.printf("Valor total gasto: R$ %.2f", sum);

        sc.close();
    }

    public static void exampleAccount() {
        Scanner sc = new Scanner(System.in);
        String readName;
        boolean hasInitialBalance;

        Account myAccount1 = new Account("Conta 1");
        Account myAccount2 = new Account("Conta 2");
        Account myAccount3 = new Account("Conta 3");

        Account myAccount;

        System.out.print("Entre com o nome da conta: ");
        readName = sc.nextLine();

        System.out.println("Deseja fazer um deposito inicial? (s/n)");
        hasInitialBalance = sc.next().equals("s");

        if(hasInitialBalance) {
            System.out.print("Entre com o valor do deposito inicial: ");
            double initialBalance = sc.nextDouble();
            myAccount = new Account(readName, initialBalance);
        } else {
            myAccount = new Account(readName);
        }

        System.out.println(myAccount);

        myAccount.deposit(200);

        System.out.println(myAccount);

        myAccount.withdraw(200);

        System.out.println(myAccount);

        sc.close();
    }

    public static void exampleConverterCurrency() {
        Scanner sc = new Scanner(System.in);
        double readValue;

        System.out.print("Quantos dolares voce deseja comprar? ");
        readValue = sc.nextDouble();
        double valueReal = CurrencyConverter.dolarToReal(readValue);

        System.out.printf("Com a cotacao do dolar em R$ %.2f, voce precisa de R$ %.2f para obter $ %.2f dolares.%n", CurrencyConverter.VALUE_ONE_DOLAR, valueReal, readValue);

        sc.close();
    }

    public static void exampleProduct() {
        Scanner sc = new Scanner(System.in);
        String readName;
        double readPrice;
        int readAmountStock;

        System.out.println("----- Cadastro de produto -----");
        System.out.print("-Nome: ");
        readName = sc.next();
        System.out.print("-Preco: ");
        readPrice = sc.nextDouble();
        System.out.print("-Quantidade em estoque: ");
        readAmountStock = sc.nextInt();

        Product product = new Product(readName, readPrice, readAmountStock);

        System.out.println(product);

        product.addStock(5);

        System.out.println(product);

        product.removeStock(3);

        System.out.println(product);

        Product productSecondConstructor = new Product("Iphone XR", 2.400);
        Product productThreeConstructor = new Product();

        sc.close();
    }

    public static void usingOO() {
        Scanner sc = new Scanner(System.in);

        Triangle tX, tY;
        double readA, readB, readC;

        System.out.println("Entre com os dados do triangulo X: ");
        readA = sc.nextDouble();
        readB = sc.nextDouble();
        readC = sc.nextDouble();
        tX = new Triangle(readA, readB, readC);

        System.out.println("Entre com os dados do triangulo Y: ");
        readA = sc.nextDouble();
        readB = sc.nextDouble();
        readC = sc.nextDouble();
        tY = new Triangle(readA, readB, readC);

        System.out.printf("Area do triangulo X: %.4f %n", tX.getArea());
        System.out.printf("Area do triangulo Y: %.4f %n", tY.getArea());

        System.out.println(tX.isLargeArea(tY) ? "X tem uma area maior que Y" : "Y tem uma area maior que X");
        sc.close();
    }

    // Eh static pois nao tem como instanciar um objeto da classe que contem o metodo main.
    public static void withoutOO() {
        double xA, xB, xC, yA, yB, yC;
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com os dados do triangulo X: ");
        xA = sc.nextDouble();
        xB = sc.nextDouble();
        xC = sc.nextDouble();
        System.out.println("Entre com os dados do triangulo Y: ");
        yA = sc.nextDouble();
        yB = sc.nextDouble();
        yC = sc.nextDouble();

        double areaX, areaY, pValue;

        pValue = (xA + xB + xC) / 2.0;
        areaX = Math.sqrt(pValue * (pValue - xA) * (pValue - xB) * (pValue - xC));

        pValue = (yA + yB + yC) / 2.0;
        areaY = Math.sqrt(pValue * (pValue - yA) * (pValue - yB) * (pValue - yC));

        System.out.printf("Area do triangulo X: %.4f %n", areaX);
        System.out.printf("Area do triangulo Y: %.4f %n", areaY);

        System.out.println(areaX > areaY ? "X tem uma area maior que Y" : "Y tem uma area maior que X");

        sc.close();
    }
}
