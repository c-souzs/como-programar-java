package chapterNine;

import chapterNine.entites.BasePlusCommissionEmployee;
import chapterNine.entites.CommissionEmployee;

public class ChapterNineTest {
    public static void main(String[] args) {
        CommissionEmployee caioSouza = new CommissionEmployee("Caio", "Souza", "(37) 99836-3402", 10000, 0.6);

        System.out.println(caioSouza);
        System.out.println("-----------------------------------");

        caioSouza.setGrossSales(5000);
        caioSouza.setCommissionRate(.1);

        System.out.println(caioSouza);
        System.out.println("-----------------------------------");

        BasePlusCommissionEmployee anaLuiza = new BasePlusCommissionEmployee("Ana", "Luiza", "(31) 99864-5231", 7000, 0.3, 2000);
        System.out.println(anaLuiza);
    }
}
