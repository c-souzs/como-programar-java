package chapterSeven;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ChapterSevenTest {
    public static void main(String[] args) {
        // usingArrayAsCounter();
        // usingMatrix();
        // usingPackageArrays();
        usingLists();

        for (String itemArg: args) {
            System.out.println(itemArg);
        }
    }

    public static void usingLists() {
        ArrayList<String> names = new ArrayList<String>();

        names.add("Caio Souza");
        names.add("Ana Luiza");
        names.add("Alysson");
        names.add("Bruna");
    }

    public static void usingPackageArrays() {
        double[] doubleArray = { 8.4, 9.3, 0.2, 7.9, 3.4 };
        Arrays.sort(doubleArray); // classifica doubleArray em ordem crescente
        System.out.printf("%ndoubleArray: ");

        for (double value: doubleArray) System.out.printf("%.1f ", value);

        int[] filledIntArray = new int[10];
        Arrays.fill(filledIntArray, 7); // preenche o array de 10 elementos com 7s
        for (int value: filledIntArray) System.out.printf("%d ", value);


        int[] intArray = { 1, 2, 3, 4, 5, 6 };
        int[] intArrayCopy = new int[intArray.length];
        System.arraycopy(intArray, 0, intArrayCopy, 0, intArray.length); // copia array intArray em array intArrayCopy
        for (int value: intArray) System.out.printf("%d ", value);
        for (int value: intArrayCopy) System.out.printf("%d ", value);

        boolean b = Arrays.equals(intArray, intArrayCopy); // verifica a igualdade de intArray e intArrayCopy
        System.out.printf("%n%nintArray %s intArrayCopy%n", (b ? "==" : "!="));

        b = Arrays.equals(intArray, filledIntArray); // verifica a igualdade de intArray e filledIntArray
        System.out.printf("intArray %s filledIntArray%n", (b ? "==" : "!="));

        int location = Arrays.binarySearch(intArray, 5); // pesquisa o valor 5 em intArray
        if(location >= 0) System.out.printf("Found 5 at element %d in intArray%n", location);
        else System.out.println("\"5 not found in intArray");
    }

    public static void usingMatrix() {
        int[][] example1 = new int[2][2]; // Cria uma matrix com 2 linhas e 2 colunas
        int[][] example2 = new int[2][]; // Cria uma matrix com 2 linhas
        example2[0] = new int[5]; // Acessa a primeira linha da matrix e cria 5 colunas
        example2[1] = new int[3]; // Acessa a segunda linhda da matrix e cria 3 colunas

        for (int row = 0; row < example2.length; row++) { // Pega a quantidade de linhas
            int[] col = example2[row]; // Acessa a coluna

            System.out.printf("Linha %d ", row);
            for (int column = 0; column < col.length; column++) {
                System.out.printf("[%d][%d] | ", row, column);
            }
            System.out.println();
        }

        Scanner sc = new Scanner(System.in);
        int readRowCourse, columnGrades;

        System.out.print("Entre com a quantidade de cursos: ");
        readRowCourse = sc.nextInt();

        int[][] courseGrades = new int[readRowCourse][];

        for (int i = 0; i < courseGrades.length; i++) {
            System.out.printf("Entre com a quantidade de notas do curso %d: ", i + 1);
            columnGrades = sc.nextInt();

            courseGrades[i] = new int[columnGrades];
        }

        for (int i = 0; i < courseGrades.length; i++) {
            int[] grades = courseGrades[i];
            System.out.printf("Insira as notas do curso %d %n", i + 1);

            for (int j = 0; j < grades.length; j++) {
                System.out.printf("Entre com a %d nota: ", j + 1);
                grades[j] = sc.nextInt();
            }
        }

        for (int i = 0; i < courseGrades.length; i++) {
            int[] grades = courseGrades[i];
            System.out.printf("Notas do curso %d: ", i + 1);

            for (int grade : grades) {
                System.out.printf("%d | ", grade);
            }
            System.out.println();
        }

        sc.close();
    }

    public static void usingArrayAsCounter() {
        int[] grades = {3, 7, 8, 5, 3, 2, 8, 7, 9, 10, 4, 6, 2, 0, 7, 9, 0, 10, 10, 10, 5, 6};
        int[] countGrades = new int[11];

        for (int i = 0; i < grades.length; i++) {
            int grade = grades[i];
            ++countGrades[grade];
        }

        for (int i = 0; i < countGrades.length; i++) {
            System.out.printf("%d aluno(s) tiraram nota %d%n", countGrades[i], i);
        }
    }
}
