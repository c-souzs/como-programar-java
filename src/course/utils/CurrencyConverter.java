package course.utils;

public class CurrencyConverter {
    public static double IOF = 1.06;
    public static double VALUE_ONE_DOLAR = 3.10;
    public static double dolarToReal(double amountDolar) {
        return (amountDolar * IOF) * VALUE_ONE_DOLAR;
    }
}
