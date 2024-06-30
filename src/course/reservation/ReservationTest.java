package course.reservation;

import course.reservation.model.entites.Reservation;
import course.reservation.model.exceptions.DomainException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ReservationTest {
    private static Scanner sc = new Scanner(System.in);
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        try {
            System.out.print("Room number: ");
            int numberRoom = sc.nextInt();
            System.out.print("Check-in date (dd/MM/yyyy): ");
            Date checkInDate = sdf.parse(sc.next());
            sc.nextLine();
            System.out.print("Check-out date (dd/MM/yyyy): ");
            Date checkOutDate = sdf.parse(sc.next());
            sc.nextLine();

            Reservation rv = new Reservation(numberRoom, checkInDate, checkOutDate);
            System.out.println(rv);

            System.out.println("----------- Update reservation -----------");
            System.out.println("Entre data to update the reservation: ");
            System.out.print("Check-in date (dd/MM/yyyy): ");
            checkInDate = sdf.parse(sc.next());
            sc.nextLine();
            System.out.print("Check-out date (dd/MM/yyyy): ");
            checkOutDate = sdf.parse(sc.next());
            sc.nextLine();

            rv.updateDates(checkInDate, checkOutDate);
            System.out.println(rv);
        } catch (ParseException e) {
            System.out.println("Invalid date format.");
        } catch (DomainException e){
            System.out.println("Error in reservation: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Unexpected error.");
        }

        sc.close();
    }
}
