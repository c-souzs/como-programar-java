package course.generics;

import course.generics.entites.Employee;
import course.generics.entites.LogEntry;
import course.generics.entites.Product;
import course.generics.services.CalculationService;
import course.generics.services.PrintService;
import course.generics.services.ProductPriceService;
import course.generics.utils.PriceUpdate;
import course.generics.utils.ProductPredicate;
import course.generics.utils.UpperCaseName;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.Instant;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // example1(sc);
        // example2(sc);
        // printList(Arrays.asList(5, 2, 10, 13));
        // printList(Arrays.asList("Caio", "Ana Luiza", "Hulk"));
        // hashCodeAndEquals();
        // exampleSet();
        // exampleHashSetFile(sc);
        //exampleHashSet(sc);
        // exampleMap();
        // exampleComparator();
        // examplePredicate();
        // exampleConsumer();
        // exampleFnMap();
        // exampleCreateFnParam();
        // exampleStream();
        // exampleStream2(sc);
        exampleStream3(sc);
        sc.close();
    }

    public static void exampleStream3(Scanner sc) {
        Set<Employee> employees = new HashSet<>();

        int amountEmployees;
        String name, email;
        double salary;

        System.out.print("Enter the amount employee: ");
        amountEmployees = sc.nextInt();

        for (int i = 0; i < amountEmployees; i++) {
            System.out.print("Enter the name: ");
            name = sc.nextLine();
            sc.nextLine();
            System.out.println("Enter the email: ");
            email = sc.next();
            sc.nextLine();
            System.out.println("Enter the salary (R%): ");
            salary = sc.nextDouble();
            sc.nextLine();

            employees.add(new Employee(name, email, salary));
        }

        double enterValue;

        do {
            System.out.print("Enter the salary to filtered employees (Enter the 0.0 to outside): ");
            enterValue = sc.nextDouble();
            double consult = enterValue;

            List<Employee> filtered = employees
                    .stream()
                    .filter(employee -> employee.getSalary() > consult)
                    .toList();

            filtered.forEach(System.out::println);
        } while (enterValue != 0.0);

        double sum = employees
                .stream()
                .filter(employee -> employee.getName().charAt(0) == 'M')
                .map(Employee::getSalary)
                .reduce(0.0, (e1, e2) -> e1 + e2);

        System.out.printf("Sum of salary of people whose name starts with 'M': %.2f", sum);
    }

    public static void exampleStream2(Scanner sc) {
        int amount;

        do {
            System.out.print("Enter the amount products (> 0): ");
            amount = sc.nextInt();
            sc.nextLine();
        } while (amount == 0);

        List<Product> products = new ArrayList<>();
        String nameProduct;
        double priceProduct;

        for (int i = 0; i < amount; i++) {
            System.out.print("Enter the name: ");
            nameProduct = sc.nextLine();
            System.out.print("Enter the price (R$): ");
            priceProduct = sc.nextDouble();
            sc.nextLine();
            products.add(new Product(nameProduct, priceProduct));
        }

        double sum = products
                .stream()
                .map(Product::getPrice)
                .reduce(0.0, (x, y) -> x + y);
        double avg = sum / amount;

        System.out.printf("Average price (R$): %.2f %n", avg);

        List<String> name = products
                .stream()
                .filter(product -> product.getPrice() < avg)
                .map(Product::getName)
                .sorted((s1, s2) -> -s1.toLowerCase().compareTo(s2.toLowerCase()))
                .toList();

        name.forEach(System.out::println);
    }

    public static void exampleStream() {
        List<Integer> list = Arrays.asList(3, 4, 5, 10, 7);
        Stream<Integer> st1 = list.stream();
        System.out.println(Arrays.toString(st1.toArray()));

        Stream<String> st2 = Stream.of("Maria", "Alex", "Bob");
        System.out.println(Arrays.toString(st2.toArray()));

        Stream<Integer> st3 = Stream.iterate(0, x -> x + 2);
        System.out.println(Arrays.toString(st3.limit(10).toArray()));

        Stream<Long> st4 = Stream.iterate(new long[]{ 0L, 1L }, p->new long[]{ p[1], p[0]+p[1] }).map(p -> p[0]);
        System.out.println(Arrays.toString(st4.limit(10).toArray()));
    }

    public static void exampleCreateFnParam() {
        List<Product> list = new ArrayList<>();
        list.add(new Product("Tv", 900.00));
        list.add(new Product("Mouse", 50.00));
        list.add(new Product("Tablet", 350.50));
        list.add(new Product("HD Case", 80.90));

        double sum = ProductPriceService.filteredSum(list, product -> product.getName().toLowerCase().charAt(0) == 't');

        System.out.println("Sum: " + sum);

    }

    public static void exampleFnMap() {
        List<Product> list = new ArrayList<>();
        list.add(new Product("Tv", 900.00));
        list.add(new Product("Mouse", 50.00));
        list.add(new Product("Tablet", 350.50));
        list.add(new Product("HD Case", 80.90));

        // List<String> names = list.stream().map(new UpperCaseName()).toList();
        // List<String> names = list.stream().map(Product::staticUpperCaseName).toList();
        //List<String> names = list.stream().map(Product::noStaticUpperCaseName).toList();

        // Function<Product, String> function = product -> product.getName().toUpperCase();
        // List<String> names = list.stream().map(function).toList();

        List<String> names = list.stream().map(product -> product.getName().toUpperCase()).toList();

        names.forEach(System.out::println);
    }

    public static void exampleConsumer() {
        List<Product> list = new ArrayList<>();
        list.add(new Product("Tv", 900.00));
        list.add(new Product("Mouse", 50.00));
        list.add(new Product("Tablet", 350.50));
        list.add(new Product("HD Case", 80.90));

        // list.forEach(new PriceUpdate());
        // list.forEach(Product::staticPriceUpdate);
        // list.forEach(Product::noStaticPriceUpdate);

        // Consumer<Product> consumer = product -> {
        //    product.setPrice(product.getPrice() * 1.1);
        // };
        // list.forEach(consumer);

        list.forEach(product -> product.setPrice(product.getPrice() * 1.1));

        list.forEach(System.out::println);
    }

    public static void examplePredicate() {
        List<Product> list = new ArrayList<>();
        list.add(new Product("Tv", 900.00));
        list.add(new Product("Mouse", 50.00));
        list.add(new Product("Tablet", 350.50));
        list.add(new Product("HD Case", 80.90));

        // list.removeIf(new ProductPredicate());
        // list.removeIf(Product::staticProductPredicate);
        // list.removeIf(Product::noStaticProductPredicate);

        // Predicate<Product> pred = product -> product.getPrice() >= 100;
        // list.removeIf(pred);

        list.removeIf(product -> product.getPrice() >= 100);

        for (Product product: list) {
            System.out.println(product);
        }
    }

    public static void exampleComparator() {
        List<LogEntry> entries = new ArrayList<>();

        entries.add(new LogEntry("Caio Souza", new Date()));
        entries.add(new LogEntry("Ana Luiza", new Date(2024, 06, 29, 16, 36)));
        entries.add(new LogEntry("Bruna Cristina", new Date(2024, 06, 30, 11, 55)));

        /*
        - Classe anonima
        Comparator<LogEntry> comparator = new Comparator<LogEntry>() {
            @Override
            public int compare(LogEntry e1, LogEntry e2) {
                return e1.getUserName().compareTo(e2.getUserName());
            }
        };
        */

        // Lambda

        /*
        - Funcao anonima
        Comparator<LogEntry> comparator = (e1, e2) -> {
            return e1.getUserName().compareTo(e2.getUserName());
        };*/

        Comparator<LogEntry> comparator = (e1, e2) -> e1.getUserName().compareTo(e2.getUserName());

        entries.sort(comparator);

        for (LogEntry entry: entries) {
            System.out.println(entry);
        }
    }

    public static void exampleMap() {
        Map<Integer, Product> stock = new HashMap<>();

        Product p1 = new Product("Iphone XR", 3499.77);
        Product p2 = new Product("Iphone 14", 7383.14);
        Product p3 = new Product("Ipad U", 1744.37);
        Product p4 = new Product("Ipad U", 1744.37);
        Product p5 = new Product("Iphone 14", 7383.14);

        stock.put(p1.getId(), p1);
        stock.put(p2.getId(), p2);
        stock.put(p3.getId(), p3);
        stock.put(p4.getId(), p4);
        stock.put(p5.getId(), p5);

        System.out.println("----- Using keySet() -----");

        for (Integer key: stock.keySet()){
            System.out.println(stock.get(key));
        }

        System.out.println("----- Using values() -----");

        for (Product product: stock.values()) {
            System.out.println(product);
        }

        System.out.println(stock.containsKey(5)); // Utiliza o hashcode e o quals da propria classe Integer
    }

    public static void exampleHashSet(Scanner sc) {
        Set<Integer> students = new HashSet<>();

        for (int i = 0; i < 3; i++) {
            System.out.printf("How many students for course %d? ", i + 1);
            int amountStudents = sc.nextInt();

            for (int j = 0; j < amountStudents; j++) {
                System.out.printf("Enter the code student %d: ", j + 1);
                int code = sc.nextInt();
                students.add(code);
            }
            System.out.println("-------------------------------------");
        }

        System.out.printf("Total students: %d", students.size());
    }

    public static void exampleHashSetFile(Scanner sc) {
        System.out.println("Entre file full path: ");
        String path = sc.next();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            Set<LogEntry> setEntrys = new HashSet<>();

            String line = br.readLine();

            while (line != null) {
                String[] inputs = line.split(" ");
                String userName = inputs[0];
                Date moment = Date.from(Instant.parse(inputs[1]));

                setEntrys.add(new LogEntry(userName, moment));

                line = br.readLine();
            }

            System.out.println("Total users: " + setEntrys.size());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static void exampleSet() {
        Set<String> hashSet = new HashSet<>();

        hashSet.add("Microondas");
        hashSet.add("Tv");
        hashSet.add("Maquina de lavar");
        hashSet.add("Computador");
        hashSet.add("Tablet");

        for (String value: hashSet) {
            System.out.println(value);
        }

        System.out.println("------------------------------------------");

        Set<String> treeSet = new TreeSet<>();

        treeSet.add("Microondas");
        treeSet.add("Tv");
        treeSet.add("Maquina de lavar");
        treeSet.add("Computador");
        treeSet.add("Tablet");

        for (String value: treeSet) {
            System.out.println(value);
        }

        System.out.println("------------------------------------------");

        Set<String> linkedHashSet = new LinkedHashSet<>();

        linkedHashSet.add("Microondas");
        linkedHashSet.add("Tv");
        linkedHashSet.add("Maquina de lavar");
        linkedHashSet.add("Computador");
        linkedHashSet.add("Tablet");

        for (String value: linkedHashSet) {
            System.out.println(value);
        }
    }

    public static void hashCodeAndEquals() {
        Product iphone = new Product("Iphone XR", 3499.55);
        Product iphone2 = new Product("Iphone XR", 3499.55);
        Product notebookNitro = new Product("Acer Nitro 4", 4299.33);

        String name = "Name";
        String nome = "Name";
        String initialName = new String("Name");
        String initialNome = new String("Name");

        /*System.out.println(iphone.hashCode());
        System.out.println(notebookNitro.hashCode());
        System.out.println(iphone.equals(notebookNitro));*/
        /*System.out.println(iphone.hashCode());
        System.out.println(iphone2.hashCode());
        System.out.println(iphone.equals(iphone2));*/
        System.out.println(name == nome); // Tratamento especial para valores literais
        System.out.println(initialName == initialNome); // Aqui nao tem esse tratamento, pois sao objetos instanciados
    }

    public static void example2(Scanner sc) {
        List<Product> products = new ArrayList<>();

        int amount;
        double price;
        String name;

        System.out.print("Enter the amount of products: ");
        amount = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < amount; i++) {
            System.out.print("Enter the name of product: ");
            name = sc.nextLine();
            System.out.print("Enter the price of product: ");
            price = sc.nextDouble();
            sc.nextLine();

            products.add(new Product(name, price));
        }

        printList(products);

        Product max = CalculationService.max(products);
        System.out.println("Max: " + max);
    }

    public static void printList(List<?> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }

    public static void example1(Scanner sc) {
        System.out.print("How many values? ");
        int amount = sc.nextInt();

        PrintService<Integer> ps = new PrintService<>();

        for (int i = 0; i < amount; i++) {
            System.out.print("Value: ");
            ps.addValue(sc.nextInt());
        }

        ps.print();
    }
}
