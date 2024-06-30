package chapterEleven;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ChapterElevenTest {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        example01();

        sc.close();
    }

    public static void example02() {
        try {
            System.out.print("Number 01: ");
            int number1 = sc.nextInt();
            System.out.print("Number 02: ");
            int number2 = sc.nextInt();

            System.out.println("Result operation: " + number1 / number2);
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("You must enter integers.");
            inputMismatchException.printStackTrace();
        } catch (ArithmeticException arithmeticException) {
            System.out.println("Zero is an invalid denominator");
            arithmeticException.printStackTrace();
        } finally {
            sc.close();
        }
    }

    private static void example01() {
        example02();
    }
}
