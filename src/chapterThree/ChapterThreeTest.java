package chapterThree;

public class ChapterThreeTest {
    public static void main(String[] args) {
        Account accountCaio = new Account("Caio Souza", 500);
        Account accountLuiza = new Account("Luiza", -1.5);

        System.out.printf("Caio Souza possui inicialmente R$ %.2f %n", accountCaio.getBalance());
        System.out.printf("Luiza possui inicialmente R$ %.2f %n", accountLuiza.getBalance());

        accountCaio.deposit(200);
        accountLuiza.deposit(700);

        System.out.printf("²Caio Souza possui inicialmente R$ %.2f %n", accountCaio.getBalance());
        System.out.printf("²Luiza possui inicialmente R$ %.2f %n", accountLuiza.getBalance());
    }
}
