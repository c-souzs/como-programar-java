package course.exampleInterface.model.entites;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Contract {
    private int number;
    private LocalDate date;
    private double totalValue;

    private List<Installment> installments;

    public Contract(int number, LocalDate date, double totalValue) {
        this.number = number;
        this.date = date;
        this.totalValue = totalValue;
        this.installments = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public List<Installment> getInstallments() {
        return installments;
    }
}
