package course.exampleInterface.model.services;

import course.exampleInterface.model.entites.Contract;
import course.exampleInterface.model.entites.Installment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ContractService {
    private OnlinePaymentService paymentService;

    public ContractService(OnlinePaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void processContract(Contract contract, int months) {
        double valueInitialInstallment = contract.getTotalValue() / months;

        for (int i = 0; i < months; i++) {
            double calcInterest = paymentService.interest(valueInitialInstallment, i + 1);
            double calcPaymentFee = paymentService.paymentFee(valueInitialInstallment + calcInterest);

            LocalDate dueDate = contract.getDate().plusMonths(i + 1);
            Installment installment = new Installment(dueDate, valueInitialInstallment + calcInterest + calcPaymentFee);

            contract.getInstallments().add(installment);
        }

    }
}
