package course.entites;

import course.entites.enums.WorkerLevel;

import java.util.ArrayList;

public class Worker {
    private String name;
    private WorkerLevel level;
    private double baseSalary;
    private Department department;
    private ArrayList<HourContract> contracts;

    // Recebo o departamento por parametro pois ele existe indepedente de um trabalhador - Agregacao.
    // Ja os contratos eh instanciado no construtor pois ele so faz sentido de existir um trabalhador - Composicao.
    public Worker(String name, WorkerLevel level, double baseSalary, Department department) {
        this.name = name;
        this.level = level;
        this.baseSalary = baseSalary;

        this.department = department;

        this.contracts = new ArrayList<HourContract>();
    }

    public void addContract(HourContract contract) {
        contracts.add(contract);
    }

    public void removeContract(HourContract contract) {
        contracts.remove(contract);
    }

    public double income(int month, int year) {
        double incomeValue = 0.0;

        for (HourContract hourContract: contracts) {
            if(hourContract.getDate().getMonth() == month && hourContract.getDate().getYear() == year) incomeValue+=hourContract.totalValue();
        }

        return  incomeValue + baseSalary;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("O funcionario: ").append(name).append(" ");
        sb.append("tem um nivel ").append(level).append(" ");
        sb.append("sendo seu salario base de ").append(baseSalary).append(". \n");
        sb.append("Seus contratos de trabalho sao: \n");
        for (HourContract hc: contracts) {
            sb.append(hc).append("\n");
        }

        return  sb.toString();
    }
}
