package chapterEight;

public class DateE {
    private int month, day, year;

    private static final int[] daysPerMonth = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    public DateE(int month, int day, int year) {
        // verifica se mês está no intervalo
        if(month <= 0 || month > 12) {
            throw new IllegalArgumentException("month (" + month + ") must be 1-12");
        }

        // verifica se day está no intervalo para month
        if(day <=0 || day > daysPerMonth[month] && !(month == 2 && day == 29)) {
            throw new IllegalArgumentException("day (" + day + ") out-of-range for the specified month and year");
        }

        // verifique no ano bissexto se o mês é 2 e o dia é 29
        if ((month == 2 && day == 29 && !(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)))) {
            throw new IllegalArgumentException("day (" + day + ") out-of-range for the specified month and year");
        }

        this.day = day;
        this.month = month;
        this.year = year;

        System.out.printf("Date object constructor for date %s%n", this);
    }

    public String toString() {
        return String.format("%d/%d/%d", month, day, year);
    }
}
